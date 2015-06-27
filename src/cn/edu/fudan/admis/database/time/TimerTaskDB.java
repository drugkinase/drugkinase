package cn.edu.fudan.admis.database.time;

import java.util.TimerTask;

import cn.edu.fudan.admis.database.base.BaseDao;

public class TimerTaskDB extends TimerTask{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		new BaseDao();
		try {
			BaseDao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
