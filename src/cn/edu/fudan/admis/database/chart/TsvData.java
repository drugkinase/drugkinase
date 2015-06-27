package cn.edu.fudan.admis.database.chart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.item.ItemCCI;
import cn.edu.fudan.admis.database.item.ItemPPI;
import cn.edu.fudan.admis.database.query.SqlQuery;
import cn.edu.fudan.admis.database.query.TopN;

public class TsvData {
	//get protein sensitivity
	public void writeProtSen(String id) throws Exception {
		String pathname = Base.SENIC50PATH + "data_" + id + "_Prot.tsv";
		File writenameFile = new File(pathname);
		BufferedWriter hander = new BufferedWriter(new FileWriter(writenameFile));
		writenameFile.createNewFile();
		
		SqlQuery sqlQuery = new SqlQuery();
		hander.write(sqlQuery.getProtSen(id));
		hander.flush();
		hander.close();
	}
	//get chemical IC50
	public void writeIC50(String id) throws Exception {
		String pathname = Base.SENIC50PATH + "data_" + id + "_IC50.tsv";
		File writenameFile = new File(pathname);
		BufferedWriter hander = new BufferedWriter(new FileWriter(writenameFile));
		writenameFile.createNewFile();
		
		SqlQuery sqlQuery = new SqlQuery();
		hander.write(sqlQuery.getCCLE(id));
		hander.flush();
		hander.close();
	}
	
	//get assemble results
	public void writeAssemble(String retString) throws IOException {
		String[] arrStrings = retString.split(";");
		String pathname = Base.ASSEMBLEPATH + "data_" + arrStrings[0] + "_Assemble.tsv";
		File writenameFile = new File(pathname);
		BufferedWriter hander = new BufferedWriter(new FileWriter(writenameFile));
		writenameFile.createNewFile();
		StringBuilder sb = new StringBuilder();
		sb.append("height\trank\tname\r\n");
		for(int i = 1; i < arrStrings.length; ++i) {
			String[] secondStrings = arrStrings[i].split(",");
			sb.append(secondStrings[1] + "\t");
			sb.append(Integer.toString(i) + "\t");
			sb.append(secondStrings[0] + "\r\n");
		}
		hander.write(sb.toString());
		hander.flush();
		hander.close();
	}
	
	public void getTopDataPPI(String id) throws Exception {
		for(int i = 2; i < Base.PPI_COLUMN.length; ++i) {
			String sortType = Base.PPI_COLUMN[i];
			String pathname = Base.CHARTDATAPATH + "data_" + id + "_" + sortType + ".tsv";
			
			File writename = new File(pathname);
			BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
			writename.createNewFile();
			
			TopN topN = new TopN();
			List<ItemPPI> list = topN.getTopPPI(id, Base.CCI_PPI_MAX_NODE, sortType);
			hander.write("name\tscore\r\n");
			for (ItemPPI itemPPI : list) {
				hander.write(itemPPI.getProtein2() + "\t" + itemPPI.getScore(sortType) + "\r\n");
			}
			hander.flush();
			hander.close();
		}
	}
	
	public void getTopDataCCI(String id) throws Exception {
		for(int i = 2; i < Base.CCI_COLUMN.length; ++i) {
			String sortType = Base.CCI_COLUMN[i];
			String pathname = Base.CHARTDATAPATH + "data_" + id + "_" + sortType + ".tsv";
			
			File writename = new File(pathname);
			BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
			writename.createNewFile();
			
			TopN topN = new TopN();
			List<ItemCCI> list = topN.getTopCCI(id, Base.CCI_PPI_MAX_NODE, sortType);
			hander.write("name\tscore\r\n");
			for (ItemCCI itemCCI : list) {
				hander.write(itemCCI.getChemical2() + "\t" + itemCCI.getScore(sortType) + "\r\n");
			}
			hander.flush();
			hander.close();
		}
	}
	
}
