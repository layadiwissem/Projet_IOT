

import java.io.IOException;

public class SmartHome extends Service {

	public SmartHome(String name) throws IOException {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void WriteData(Thing thing) {
		if (thing.existData("sta")) {
			//existdata methode f thing
			super.WriteData(thing);
		}
		
	}

}
