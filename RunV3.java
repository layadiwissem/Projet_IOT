
	package test;

	import java.io.IOException;

	public class RunV3 {
		
		public static void main(String[] args) throws IOException {
			
			Thing T1 = new Thing("f0:de:f1:39:7f:17","1");
			System.out.println("*****Welcome on IoT central platform*****");
			T1.setFromDatagram("geo 43.433331 -1.58333;pul 128;bat 90.0");
			Service s1 = new Service("mon_service");
	        s1.WriteData(T1);
	        s1.close();
	        
	        Service s2 = new Service("s2");
	        Service s3 = new Service("s3");
	        Service s4 = new Service("s4");
	        Service s5 = new Service("s5");
	        T1.subscribe(s2);
	        T1.subscribe(s3);
	        T1.subscribe(s4);
	        T1.subscribe(s5);
	        T1.update(); 
	        Platform p = new Platform();
	        
	        /*************reponse 3.1 / 3.2 *********

	        FileReader F  = new FileReader("simu.txt");
	        SmartHome sh = new SmartHome("myKWHome");
	        QuantifiedSelf qs = new QuantifiedSelf("RUNstats ");
	        T1.subscribe(sh);
	        T1.subscribe(qs);
	        p.addService(sh);
	        p.addService(qs);
	        p.addThing(T1);
	        F.open();
	        p.run(F);
	        F.close();*/
	        
	        //************reponse 3.3 *********
	        ThingTempo T2 = new ThingTempo("f0:de:f1:39:7f:18","1",60);
	        DataReceiver k = new KeyboardInput();
	        p.addThing(T2);
	        T2.subscribe(s2);
	        T2.subscribe(s3);
	        T2.update();
	        k.open();
		    p.run(k);
	        k.close(); 
	        System.out.println("\n ********BYE*********");
		}


}
