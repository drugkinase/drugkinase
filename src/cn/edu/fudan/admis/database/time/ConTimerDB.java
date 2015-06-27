package cn.edu.fudan.admis.database.time;

import java.util.Timer;

public class ConTimerDB {
	public void reConnectDB(int time) {
		Timer timer = new Timer();
		timer.schedule(new TimerTaskDB(), 10000, time * 1000);
	}
}
