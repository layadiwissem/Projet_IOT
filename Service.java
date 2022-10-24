

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Service {
	private String name;
	protected PrintWriter pw;
	 
	public Service(String name) throws IOException {
		this.name=name;
		 FileWriter fw = new FileWriter ("log_" + this.name + ".txt", true); 
		 BufferedWriter bw = new BufferedWriter (fw);
		 this.pw = new PrintWriter (bw);
	}

	public void WriteData(Thing thing) {
		 Date now = new Date () ;
		 SimpleDateFormat formater = new SimpleDateFormat ("yyyy-MM-dd H:m:s");
		 String d = formater.format (now);
		    pw.println(d + ";" + thing.toString()); 
			pw.flush(); 
	}
	
	 public void close() {
		 pw.flush();
		 pw.close();
	 }
	
}