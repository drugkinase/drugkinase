package cn.edu.fudan.admis.database.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.base.Type;
import cn.edu.fudan.admis.database.item.ItemCCI;
import cn.edu.fudan.admis.database.item.ItemCPI;
import cn.edu.fudan.admis.database.item.ItemCellLine;
import cn.edu.fudan.admis.database.item.ItemPPI;
import cn.edu.fudan.admis.database.network.JsonCPI;
import cn.edu.fudan.admis.database.query.SqlQuery;
import cn.edu.fudan.admis.database.query.TopN;
import cn.edu.fudan.admis.database.sensitivity.SenAnalyze;
import cn.edu.fudan.admis.database.transfer.TransList2Str;

public class DataFetch {
	
	public String getAllData(String sql, String id, String type) throws Exception{
		List<ItemCPI> list = new SqlQuery().getAllData_CPI(sql, id);
		//generate Json files
		new JsonCPI().generateJsonCPI(type, id);
		return new TransList2Str().list2StringCPI(list);
	}
	
	public String getAllData(String sql, String id) throws Exception{
		String idType = new Type().getIdType(id);
		if (idType == Base.PROTTYPE) {
			//generate PPI list result and generate data
			List<ItemPPI> list = new SqlQuery().getAllData_PPI(sql, id);
			return new TransList2Str().list2StringPPI(list);
		}
		else {
			//generate CCI list result and generate data
			List<ItemCCI> list = new SqlQuery().getAllData_CCI(sql, id);
			return new TransList2Str().list2StringCCI(list);
		}
	}

	public List<ArrayList<String>> getPPIListDomain(String id, String sortType) throws Exception {
		List<ArrayList<String>> listRet = new ArrayList<ArrayList<String>>();
		TopN topN = new TopN();
		List<ItemPPI> list = topN.getTopPPI(id, Base.CCI_PPI_MAX_NODE, sortType);
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> scores = new ArrayList<String>();
		for (ItemPPI itemPPI : list) {
			labels.add(itemPPI.getProtein2());
			scores.add(itemPPI.getDomain_score());
		}
		listRet.add(labels); listRet.add(scores);
		return listRet;
	}
	
	public List<ArrayList<String>> getPPIListGo(String id, String sortType) throws Exception {
		List<ArrayList<String>> listRet = new ArrayList<ArrayList<String>>();
		TopN topN = new TopN();
		List<ItemPPI> list = topN.getTopPPI(id, Base.CCI_PPI_MAX_NODE, sortType);
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> scores = new ArrayList<String>();
		for (ItemPPI itemPPI : list) {
			labels.add(itemPPI.getProtein2());
			scores.add(itemPPI.getGo_score());
		}
		listRet.add(labels); listRet.add(scores);
		return listRet;
	}
	
	public List<ArrayList<String>> getPPIListSeq(String id, String sortType) throws Exception {
		List<ArrayList<String>> listRet = new ArrayList<ArrayList<String>>();
		TopN topN = new TopN();
		List<ItemPPI> list = topN.getTopPPI(id, Base.CCI_PPI_MAX_NODE, sortType);
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> scores = new ArrayList<String>();
		for (ItemPPI itemPPI : list) {
			labels.add(itemPPI.getProtein2());
			scores.add(itemPPI.getSeq_score());
		}
		listRet.add(labels); listRet.add(scores);
		return listRet;
	}
	
	public List<ArrayList<String>> getCCIListScore(String id, String sortType) throws Exception {
		List<ArrayList<String>> listRet = new ArrayList<ArrayList<String>>();
		TopN topN = new TopN();
		List<ItemCCI> list = topN.getTopCCI(id, Base.CCI_PPI_MAX_NODE, sortType);
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> scores = new ArrayList<String>();
		for (ItemCCI itemCCI : list) {
			labels.add(itemCCI.getChemical2());
			scores.add(itemCCI.getSub_structure());
		}
		listRet.add(labels); listRet.add(scores);
		return listRet;
	}
	
	public String getAssembleString(String assemble) throws Exception {
		List<ItemCellLine> list = new SenAnalyze().getCellLines(assemble);
		Collections.sort(list);
		return new TransList2Str().list2StringAssemble(list);
	}
}
