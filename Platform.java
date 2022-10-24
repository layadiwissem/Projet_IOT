

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class Platform {
	private Map<String, Thing> mapThings;
	private List<Service> arrServices;

		public Platform() {
			this.arrServices = new ArrayList<Service>();
			this.mapThings = new HashMap<String, Thing>();
		}

		public void addThing(Thing thing) {
			mapThings.put(thing.getMacAddress(), thing);}

		public void addService(Service service) {
			arrServices.add(service);}

		public void run (DataReceiver dataReceiver) {
			 String datagram ;
			 while (dataReceiver.ready()) {
				 datagram = dataReceiver.readDatagram() ;
				 if (datagram != null && !datagram.isEmpty()) {
					 String mac = datagram.substring(0, 17) ;
					 Thing theThing = this.mapThings.get(mac) ;
				 if (theThing == null) {
					 System.out.println("cette adresse MAC ne correspond à aucun objet enregistré sur la plateforme: "+mac);
				 }else {
					 theThing.setFromDatagram(datagram.substring(18)) ;
					 theThing.update();
					 theThing.resetData();
				 	}
				 }
			 }
		 }
		public void close() {
			/********* Parcours ArrayList avec les indices *********/
				for (int i=0 ; i < this.arrServices.size() ; i++) {
					Service service = this.arrServices.get(i) ;
			/****** Puis on demande à chaque service de se close**/
					service.close();
				}
			
			/******* Parcours ArrayList avec iterateur************
			Iterator<Service> it = this.arrServices.iterator();
			
			while (it.hasNext()) {
				Service service = it.next() ;
				service.close();
			}
			*******************************************************/

		}
	
		public void loadFromDatabase() throws Throwable {
		
			HashMap<String, Service> mapIds = new HashMap<String, Service>();
			try {
				//Connexion a la bdd en java
				Class.forName("com.mysql.jdbc.Driver");
				try {
					Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/" +"platform_iot", "root","");
					System.out.println("Connexion réussie");
					
		/*Partie 1 pour instancier de nouveaux services à partir de leur nom et de leur type, puis les ajoute à la liste des
			services de la plateforme avec la méthode addService()*/
					
					Statement Statement1 = conn.createStatement();
					ResultSet requeteService = Statement1.executeQuery("SELECT * FROM table_service;");
					
					while (requeteService.next()) {
						
						Service service;
						
						String nameS = requeteService.getString("name");
						String typeS = requeteService.getString("type");
						String idS = requeteService.getString("id");
						
						if (typeS != null) {
							if (typeS.equals("smarthome")) {
								service = new SmartHome(nameS);
							} else if (typeS.equals("quantifiedself")) {
								service = new QuantifiedSelf(nameS);
							} else {
								service = new Service(nameS);
							}
						  } else {
							service = new Service(nameS);}
						
						this.addService(service);
						mapIds.put(idS, service);
					}
		/*Partie 2 pour instancier de nouveaux objets connectés à partir des adresses MAC et des types, puis les ajoute à la liste des objets
              connectés de la plateforme et pour souscrire chaque objet connecté, aux services auxquels son utilisateur est abonné */
					
					Statement Statement2 = conn.createStatement();
					ResultSet requeteThing = Statement2.executeQuery("SELECT * FROM table_thing;");
					
					while (requeteThing.next()) {
						Thing thing;
						String typeT = requeteThing.getString("type");
						String macT = requeteThing.getString("mac");
						String idT = requeteThing.getString("id_user");
						String paramT = requeteThing.getString("param");
						
						if (paramT != null && typeT.equals("thingtempo")) {
							
							 long delai = Long.parseLong(paramT);
							thing = new ThingTempo(macT, idT, delai);
						} else {
							
							thing = new Thing(macT, idT);
						}
						
						    this.addThing(thing);
	
						
						Statement Statment3 = conn.createStatement();
						ResultSet requeteServiceSubscribe = Statment3.executeQuery("SELECT id_service FROM table_subscribe WHERE id_user IN (SELECT id_user FROM table_thing WHERE mac='"
								+ macT + "');");
						while (requeteServiceSubscribe.next()) {
							String idSS = requeteServiceSubscribe.getString("id_service");
							Service ServiceSubscribe = mapIds.get(idSS);
							thing.subscribe(ServiceSubscribe);
						}
					}
	
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
		}
	}