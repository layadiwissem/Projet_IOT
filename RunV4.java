package test;


public class RunV4 {
	
	public static void main(String[] args) throws Throwable {
		
		/*           Version 4
		 * ************Reponse 4.1 *********
		 * Question1 : Combien de services sont pr�sents sur la plateforme ? De quels types sont-ils ?
		 * Reponse1  : Quatre services ( Type smartHome et QuantifiedSelf)
		 * Question2 : Combien d'objets connect�s sont pr�sents sur plateforme ?
		 * Reponse2  : trois objets
		 * Question3 : Combien d'objets connect�s poss�de chaque utilisateur ?
		 * Reponse3  : l'utilisateur Amaya possede un seul objet et l'utilisateur Maialen possede 2 objets connect�s � la platforme
		 * Question4 : � quels services est abonn� chaque utilisateur ?
		 * Reponse4  : Le deuxi�me utilisateur (Maialen) s'abonne aux services myKWHome et  FridgAlert, 
		 et le premier utilisateur (Amaya) s'abonne au service RUNstats        */
		
		//************Reponse 4.2 *********
	
		
		System.out.println("*****Welcome on IoT central platform*****");
		 
		FileReader F  = new FileReader("simu.txt");
        F.open();
        Platform p = new Platform();
       
        p.loadFromDatabase();
        p.run(F);
        F.close();
        
        System.out.println("\n ********BYE*********");
	}

}
