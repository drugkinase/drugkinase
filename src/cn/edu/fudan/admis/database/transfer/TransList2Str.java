package cn.edu.fudan.admis.database.transfer;

import java.util.List;

import cn.edu.fudan.admis.database.item.ItemCCI;
import cn.edu.fudan.admis.database.item.ItemCPI;
import cn.edu.fudan.admis.database.item.ItemCellLine;
import cn.edu.fudan.admis.database.item.ItemPPI;
import cn.edu.fudan.admis.database.query.SqlQuery;

//transfer list object to string
public class TransList2Str {
	public String list2StringCPI(List<ItemCPI> list) throws Exception{
		StringBuilder ret = new StringBuilder();
		SqlQuery sqlQuery = new SqlQuery();
		for (ItemCPI item : list) {
			ret.append(";");
			ret.append(item.getName() + ',');
			ret.append(sqlQuery.getDetailName(item.getName()) + ',');
			ret.append(item.getDthybrid() + ',');
			ret.append(item.getKbmf() + ',');
			ret.append(item.getMinhash() + ',');
			ret.append(item.getRls() + ',');
			ret.append(item.getSvm() + ',');
			ret.append(item.getDrugbank() + ',');
			ret.append(item.getStitch() + ',');
			ret.append(item.getKiba() + ',');
			ret.append(item.getCombined_score());
		}
		
		return ret.toString();
	}
	
	public String list2StringPPI(List<ItemPPI> list) throws Exception{
		String ret = "";
		SqlQuery sqlQuery = new SqlQuery();
		for (ItemPPI item : list) {
			ret += ";";
			ret += item.getProtein2() + ',';
			ret += sqlQuery.getDetailName(item.getProtein2()) + ',';
			ret += item.getDomain_score() + ',';
			ret += item.getGo_score() + ',';
			ret += item.getSeq_score();
		}
		return ret;
	}
	
	public String list2StringCCI(List<ItemCCI> list) throws Exception{
		String ret = "";
		SqlQuery sqlQuery = new SqlQuery();
		for (ItemCCI item : list) {
			ret += ";";
			ret += item.getChemical2() + ',';
			ret += sqlQuery.getDetailName(item.getChemical2()) + ',';
			ret += item.getSub_structure();
		}
		return ret;
	}
	
	public String list2StringAssemble(List<ItemCellLine> list) throws Exception{
		String ret = "";
		for (ItemCellLine item : list) {
			ret += ";";
			ret += item.getName() + ',';
			ret += String.format("%.4f", item.getScore());
		}
		return ret;
	}
}
