package cn.edu.fudan.admis.database.servlet;

import cn.edu.fudan.admis.database.query.SqlQuery;

public class MultiIdMap {
	public String getSingleID(String id) throws Exception {
		String retString = new SqlQuery().getSingleId(id);
		return retString;
	}
}
