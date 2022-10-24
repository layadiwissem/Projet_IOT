

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Thing {
	
	private String userId;
	private String macAddress;
	private Map<String, String> mapData;
	private List<Service> arrServices;
	

	Thing(String mac, String Id){
		this.userId= Id;
		this.macAddress= mac;
	    this.arrServices = new ArrayList<Service>() ; 
	    this.mapData = new HashMap<String, String>() ;
	}
	public String getMacAddress() {
		
		return this.macAddress;
	}
    public String getUserId() {
		
		return this.userId;
	}
    public void putData(String key, String Data) {
    	mapData.put(key, Data);
    	
    }
    public String getData(String key) {
    	 return mapData.get(key);   	
    }
    
    public void setFromDatagram(String datagram) {
		String[] tabExplode = datagram.split(";");
		for (int i = 0; i < tabExplode.length; i++) {
			String key = tabExplode[i].substring(0, 3);
			String dat = tabExplode[i].substring(4);
			this.putData(key, dat); 
		}
	}

	public boolean existData(String key) {
		return mapData.containsKey(key);
	}

	public void resetData() {
		mapData.clear();
	}

	public void subscribe(Service service) {
		arrServices.add(service);
	}

	public String toString() {
		String concatination = macAddress + ";" + userId;
		Iterator<Map.Entry<String, String>> it = this.mapData.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> couple = it.next();
			String dat = (String) couple.getValue();
			concatination = concatination + ";" + dat;
		}
		return concatination;
	}

	public void update() {
		
		for (int i=0 ; i < this.arrServices.size() ; i++) {
			Service service = this.arrServices.get(i) ;
			service.WriteData(this); }
		
		}
	
}
	
	
