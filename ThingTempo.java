

import java.util.Date;

public class ThingTempo extends Thing {
	private long delay;
	private long lastUpdate;

	public ThingTempo(String mac, String id, long delay) {
		// TODO Auto-generated constructor stub
		super(mac, id);
		this.delay = delay;
	}

	public void update() {
		Date now = new Date();
		long time = now.getTime();
		if (time > lastUpdate + (delay * 1000)) {
			super.update();
			this.lastUpdate = time;
		}
	}

}
