package cn.edu.fudan.admis.database.query;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.admis.database.base.Type;
import cn.edu.fudan.admis.database.item.ItemCCI;
import cn.edu.fudan.admis.database.item.ItemCPI;
import cn.edu.fudan.admis.database.item.ItemPPI;

public class TopN {
	//return new top CPI list, it can recognize protein id and chemical id
	public List<ItemCPI> getTopCPI(String id, int n, String sort_type) throws Exception {
		List<ItemCPI> topList= new ArrayList<ItemCPI>();
		
		String idType = new Type().getIdType(id);
		String sql = "select * from cpi where " + new Type().getColName(id, idType) + 
				"='" + id + "' order by " + sort_type + " desc limit 0, " + Integer.toString(n);
		topList = new SqlQuery().getAllData_CPI(sql, id);
		return topList;
	}
	
	//return new top CPI list, it can recognize protein id and chemical id
	public List<ItemPPI> getTopPPI(String id, int n, String sort_type) throws Exception {
		List<ItemPPI> topList= new ArrayList<ItemPPI>();
		String sql = "select * from ppi where protein1='" + id + "' order by " + sort_type + " desc limit 0, " + Integer.toString(n);
		topList = new SqlQuery().getAllData_PPI(sql, id);
		return topList;
	}
	
	//return new top CPI list, it can recognize protein id and chemical id
	public List<ItemCCI> getTopCCI(String id, int n, String sort_type) throws Exception {
		List<ItemCCI> topList= new ArrayList<ItemCCI>();
		String sql = "select * from cci where chemical1='" + id + "' order by " + sort_type + " desc limit 0, " + Integer.toString(n);
		topList = new SqlQuery().getAllData_CCI(sql, id);
		return topList;
	}
}
