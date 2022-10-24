package test;

import java.io.IOException;

public class RunV1 {
	
	public static void main(String[] args) throws IOException {
		
		String objet1;
		/*           Version 1             */
		//************Reponse 1.2 *********
		
		Thing T1 = new Thing("f0:de:f1:39:7f:17","1");
		System.out.println("*****Welcome on IoT central platform*****");
		T1.setFromDatagram("geo 43.433331 -1.58333;pul 128;bat 90.0");
		objet1= T1.toString();
		System.out.println("la conctintion  est : "+objet1);
		
		//*************Reponse 1.3 *********
		
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
        
        //************reponse 1.5 avant de fair 1.6 *********
        DataReceiver k = new KeyboardInput();
        k.open();
	        String result=  k.readDatagram();
			System.out.println("Result : "+result);
        k.close();
        
        //************reponse 1.6  *********
       
        k.open();
	        Platform p = new Platform();
	        p.addService(s1);
			p.addService(s2);
			p.addService(s3);
			p.addService(s4);
			p.addService(s5);
			p.addThing(T1);
	   p.run(k);
        k.close(); 
        
     
       
        
        
        System.out.println("\n ********BYE*********");
	}

}
