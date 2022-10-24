

import java.io.IOException;
import java.util.Date;

public class QuantifiedSelf extends Service  {

	public QuantifiedSelf(String name) throws IOException {
		super(name);
	}
	public void WriteData(Thing thing) {
		Date now = new Date() ;
	    long time = now.getTime() ;
		if (thing.existData("geo")) {
			pw.println(time + ";" + thing.toString());
			pw.flush();
		}
		
	}


}
