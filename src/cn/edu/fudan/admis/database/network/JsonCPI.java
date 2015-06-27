package cn.edu.fudan.admis.database.network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.base.Type;
import cn.edu.fudan.admis.database.item.ItemCCI;
import cn.edu.fudan.admis.database.item.ItemCPI;
import cn.edu.fudan.admis.database.item.ItemChem;
import cn.edu.fudan.admis.database.item.ItemPPI;
import cn.edu.fudan.admis.database.item.ItemProt;
import cn.edu.fudan.admis.database.query.SqlQuery;
import cn.edu.fudan.admis.database.query.TopN;
import cn.edu.fudan.admis.database.transfer.Transfer;

public class JsonCPI {
	public void generateJsonCPI(String type, String firstId) throws Exception{
		for(int i = 2; i < Base.CPI_COLUMN.length; ++i) {
			String sortType = Base.CPI_COLUMN[i];
			String pathname = Base.NETDATAPATH + "data_" + type + "_" + firstId + "_" + sortType + ".json";
			File writename = new File(pathname);
			if (!writename.exists()) {
				BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
				writename.createNewFile();
				String jsonString = new String();
				if (new Type().getIdType(firstId) == Base.PROTTYPE){
					jsonString = searchProtein(firstId, sortType);
				}else {
					jsonString = searchChemical(firstId, sortType);
				}//else
				hander.write(jsonString);
				hander.flush();
				hander.close();
			}//if file exist, then go on
		}//for
	}//
	
	public String searchProtein(String firstId, String sortType) throws Exception {
		TopN topN = new TopN();
		List<ItemCPI> list = topN.getTopCPI(firstId, Base.CPI_MAX_NODE, sortType);
		SqlQuery sqlQuery = new SqlQuery();
		Weight weight = new Weight();
		int count = 0;
		Group group = new Group();
		Transfer trans = new Transfer();
		//////////////////////////////////////////////////////////////////////
		ItemProt firstProt = sqlQuery.getProt(firstId);
		group.getNodesProt().add(trans.transProtNode(firstProt, weight.getFirstWeight(), Integer.toString(count++)));
		for (int j = 0; j < list.size(); ++j) {
			ItemCPI itemCPI = list.get(j);
			ItemChem chem = sqlQuery.getChem(itemCPI.getName());
			double nodeSize = weight.getOtherWeight(weight.getCPIWeight(itemCPI, sortType));
			group.getNodesChem().add(trans.transChemNode(chem, Double.toString(nodeSize), Integer.toString(count++)));
			double lineSize = weight.getCPIWeight(itemCPI, sortType) + 1;
			group.getEdgeCPI().add(trans.transCPIEdge(firstId, itemCPI, Double.toString(lineSize)));
		}
		//compute the cci edge
		ArrayList<String> nameList = trans.itemCPIList2NameList(list);
		List<ItemCCI> listCCI = sqlQuery.getCCIList(nameList);
		group.setEdgeCCI(trans.transCCIEdgeList(listCCI));
		return new JsonOutFile().searchProtJsonOut(group);
	}
	
	public String searchChemical(String firstId, String sortType) throws Exception {
		TopN topN = new TopN();
		List<ItemCPI> list = topN.getTopCPI(firstId, Base.CPI_MAX_NODE, sortType);
		SqlQuery sqlQuery = new SqlQuery();
		Weight weight = new Weight();
		int count = 0;
		Group group = new Group();
		Transfer trans = new Transfer();
		/////////////////////////////////////////////////////////////////////////
		ItemChem firstChem = sqlQuery.getChem(firstId);
		group.getNodesChem().add(trans.transChemNode(firstChem, weight.getFirstWeight(), Integer.toString(count++)));
		for (int j = 0; j < list.size(); j++) {
			ItemCPI itemCPI = list.get(j);
			ItemProt prot = sqlQuery.getProt(itemCPI.getName());
			double nodeSize = weight.getOtherWeight(weight.getCPIWeight(itemCPI, sortType));
			group.getNodesProt().add(trans.transProtNode(prot, Double.toString(nodeSize), Integer.toString(count++)));
			double lineSize = weight.getCPIWeight(itemCPI, sortType) + 1;
			group.getEdgeCPI().add(trans.transCPIEdge(firstId, itemCPI, Double.toString(lineSize)));
		}
		ArrayList<String> nameList = trans.itemCPIList2NameList(list);
		List<ItemPPI> listPPI = sqlQuery.getPPIList(nameList);
		group.setEdgePPI(trans.transPPIEdgeList(listPPI));
		return new JsonOutFile().searchChemJsonOut(group);
	}
}
