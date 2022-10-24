

public class GatewaySimulator {

	public static void main(String args[]) {
		FileReader f = new FileReader("simu.txt");
		SocketClient S = new SocketClient("127.0.0.1", 51291);
		f.open();
		S.open();
		while (f.ready()) {
			if (f.readDatagram() != null) {
				S.writeDatagram(f.readDatagram());
			}
		}
		f.close();
		S.close();
	}
	

}
