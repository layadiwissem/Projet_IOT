

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardInput implements DataReceiver {
	private boolean ok;
	public KeyboardInput() {
		this.ok=false;
	}
	@Override
	public void open() {
		// TODO Auto-generated method stub
		this.ok=true;

	}
	@Override
	public String readDatagram() {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("MAC Address: ");
			String MacAddress=br.readLine();
			if (MacAddress.equals("quit")) {
				this.ok = false;
				return null;
			} else {
				System.out.println("DATAGRAM:");
				String Datagram = br.readLine();
				String result = MacAddress + ";" + Datagram;
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
		}
	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return this.ok;
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		this.ok=false;
	}

}
