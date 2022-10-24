


public class RunV5 {
	
	public static void main(String[] args) throws Throwable {
		
		System.out.println("*****Welcome on IoT central platform*****");
		/*           Version 5              */
		Platform p = new Platform();
		p.loadFromDatabase();
     	SocketServer s = new SocketServer(51291);
	    s.open();
		p.run(s); 
        
        System.out.println("\n ********BYE*********");
	}

}
