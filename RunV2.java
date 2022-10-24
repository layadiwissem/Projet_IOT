package test;

import java.io.IOException;

public class RunV2 {

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
    
        FileReader F  = new FileReader("simu.txt");
        F.open();

        Platform p = new Platform();
        p.addService(s1);
		p.addService(s2);
		p.addService(s3);
		p.addService(s4);
		p.addService(s5);
		p.addThing(T1);
        p.run(F);
        F.close();
        

        System.out.println("\n ********BYE*********");
        
       
}
}
