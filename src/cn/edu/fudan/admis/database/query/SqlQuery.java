package cn.edu.fudan.admis.database.query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.base.BaseDao;
import cn.edu.fudan.admis.database.base.Type;
import cn.edu.fudan.admis.database.item.ItemCCI;
import cn.edu.fudan.admis.database.item.ItemCPI;
import cn.edu.fudan.admis.database.item.ItemCellLine;
import cn.edu.fudan.admis.database.item.ItemChem;
import cn.edu.fudan.admis.database.item.ItemKinase;
import cn.edu.fudan.admis.database.item.ItemPPI;
import cn.edu.fudan.admis.database.item.ItemProt;


public class SqlQuery {
	public List<ItemCPI> getAllData_CPI(String sql, String id) throws Exception{
		int colNum = 0;
		if (new Type().getIdType(id) == Base.PROTTYPE) {
			colNum = 1;
		}else {
			colNum = 2;
		}
		BaseDao dao = new BaseDao();
		List<ItemCPI> list = new ArrayList<ItemCPI>();
		ResultSet rs = dao.executeQuery(sql);
		while(rs.next()) {
			ItemCPI item = new ItemCPI();
			item.setName(rs.getString(colNum));
			item.setDthybrid(rs.getString(3));
			item.setKbmf(rs.getString(4));
			item.setMinhash(rs.getString(5));
			item.setRls(rs.getString(6));
			item.setSvm(rs.getString(7));
			item.setDrugbank(rs.getString(8));
			item.setStitch(rs.getString(9));
			item.setKiba(rs.getString(10));
			item.setCombined_score(rs.getString(11));
			list.add(item);
		}
		rs.close();
		return list;
	}
	
	//judge if the id is exist, get kinase from cpi
	//return protein name and combined_score
	public List<ItemKinase> getKinase(String id) throws Exception {
		List<ItemKinase> list = new ArrayList<ItemKinase>();
		BaseDao dao = new BaseDao();
		String sql = "select protein,kiba from cpi where chemical='" + id +"'";
		ResultSet rs = dao.executeQuery(sql);
		while(rs.next()) {
			ItemKinase itemKinase = new ItemKinase();
			itemKinase.setKinase(rs.getString(1));
			itemKinase.setScore(rs.getDouble(2));
			list.add(itemKinase);
		}
		rs.close();
		return list;
	}
	
	public List<ItemCCI> getAllData_CCI(String sql, String id) throws Exception{
		/// Given corresponding page number, get all data
		List<ItemCCI> list = new ArrayList<ItemCCI>();
		BaseDao dao = new BaseDao();
		ResultSet rs = dao.executeQuery(sql);
		
		while(rs.next()) {
			ItemCCI item = new ItemCCI();
			//item.setChemical1(rs.getString(1));
			item.setChemical2(rs.getString(2));
			item.setSub_structure(rs.getString(3));
			list.add(item);
		}
		rs.close();
		return list;
	}
	
	public List<ItemPPI> getAllData_PPI(String sql, String id) throws Exception{
		/// Given corresponding page number, get all data
		List<ItemPPI> list = new ArrayList<ItemPPI>();
		BaseDao dao = new BaseDao();
		ResultSet rs = dao.executeQuery(sql);
		
		while(rs.next()) {
			ItemPPI item = new ItemPPI();
			//item.setProtein1(rs.getString(1));
			item.setProtein2(rs.getString(2));
			item.setDomain_score(rs.getString(3));
			item.setGo_score(rs.getString(4));
			item.setSeq_score(rs.getString(5));
			list.add(item);
		}
		rs.close();
		return list;
	}
	
	public ItemProt getProt(String id) throws Exception {
		BaseDao dao = new BaseDao();
		String sql = "select * from protein_detail where id='" + id + "'";
		ItemProt itemProt = new ItemProt();
		ResultSet rs = dao.executeQuery(sql);
		while(rs.next()) {
			itemProt.setId(rs.getString(1));
			itemProt.setName(rs.getString(2));
			itemProt.setUniprotKB(rs.getString(3));
			itemProt.setProteinName(rs.getString(4));
			itemProt.setOrganism(rs.getString(5));
		}
		rs.close();
		return itemProt;
	}
	
	public ItemChem getChem(String id) throws Exception {
		BaseDao dao = new BaseDao();
		String sql = "select * from chemical_detail where id='" + id + "'";
		ResultSet rs = dao.executeQuery(sql);
		ItemChem itemChem = new ItemChem();
		while(rs.next()) {
			itemChem.setId(rs.getString(1));
			itemChem.setName(rs.getString(2));
			itemChem.setMolecularWeight(rs.getString(3));
			itemChem.setSmilesString(rs.getString(4));
		}
		rs.close();
		return itemChem;
	}
	
	public ItemCCI getCCI(String id1, String id2) throws Exception {
		//find single cci
		BaseDao dao = new BaseDao();
		String sql = "select * from cci where chemical1='" + id1 + "' and chemical2='" + id2 + "'";
		ResultSet rs = dao.executeQuery(sql);
		ItemCCI itemCCI = new ItemCCI();
		while(rs.next()) {
			itemCCI.setChemical1(rs.getString(1));
			itemCCI.setChemical2(rs.getString(2));
			itemCCI.setSub_structure(rs.getString(3));
		}
		rs.close();
		return itemCCI;
	}
	
	public ItemPPI getPPI(String id1, String id2) throws Exception {
		//find single ppi
		BaseDao dao = new BaseDao();
		String sql = "select * from cci where chemical1='" + id1 + "' and chemical2='" + id2 + "'";
		ResultSet rs = dao.executeQuery(sql);
		ItemPPI itemPPI = new ItemPPI();
		while(rs.next()) {
			itemPPI.setProtein1(rs.getString(1));
			itemPPI.setProtein2(rs.getString(2));
			itemPPI.setDomain_score(rs.getString(3));
			itemPPI.setGo_score(rs.getString(4));
			itemPPI.setSeq_score(rs.getString(5));
		}
		rs.close();
		return itemPPI;
	}
	//given a query name list, then return the cci object list
	public List<ItemCCI> getCCIList(ArrayList<String> list) throws Exception {
		List<ItemCCI> cciList = new ArrayList<ItemCCI>();
		BaseDao dao = new BaseDao();
		StringBuilder condition = new StringBuilder();
		condition.append("'" + list.get(0) + "'");
		for (int i = 1; i < list.size(); ++i) {
			condition.append(",'" + list.get(i) + "'");
		}
		String sql = "select * from cci where chemical1 in (" + condition.toString() +
				") and chemical2 in (" + condition.toString() + ");";
		ResultSet rs = dao.executeQuery(sql);
		while(rs.next()) {
			ItemCCI itemCCI = new ItemCCI();
			itemCCI.setChemical1(rs.getString(1));
			itemCCI.setChemical2(rs.getString(2));
			itemCCI.setSub_structure(rs.getString(3));
			cciList.add(itemCCI);
		}
		rs.close();
		return cciList;
	}
	//given a query name list, then return the ppi object list
	public List<ItemPPI> getPPIList(ArrayList<String> list) throws Exception {
		List<ItemPPI> ppiList = new ArrayList<ItemPPI>();
		BaseDao dao = new BaseDao();
		StringBuilder condition = new StringBuilder();
		if(list.size() > 0) {
			condition.append("'" + list.get(0) + "'");
			for (int i = 1; i < list.size(); ++i) {
				condition.append(",'" + list.get(i) + "'");
			}
			String sql = "select * from ppi where protein1 in (" + condition.toString() +
					") and protein2 in (" + condition.toString() + ");";
			ResultSet rs = dao.executeQuery(sql);
			while(rs.next()) {
				ItemPPI itemPPI = new ItemPPI();
				itemPPI.setProtein1(rs.getString(1));
				itemPPI.setProtein2(rs.getString(2));
				itemPPI.setDomain_score(rs.getString(3));
				itemPPI.setGo_score(rs.getString(4));
				itemPPI.setSeq_score(rs.getString(5));
				ppiList.add(itemPPI);
			}
			rs.close();
		}
		return ppiList;
	}
	
	public String getDetailName(String id) throws Exception {
		BaseDao dao = new BaseDao();
		String table = "";
		if (new Type().getIdType(id) == Base.PROTTYPE) {
			table = "protein_detail";
		}else {
			table = "chemical_detail";
		}
		String sql = "select name from " + table + " where id='" + id + "'";
		ResultSet rs = dao.executeQuery(sql);
		rs.next();
		String retString = rs.getString(1);
		rs.close();
		return retString;
	}
	
	//protein sensitivity query
	public String getProtSen(String id) throws Exception {
		BaseDao dao = new BaseDao();
		StringBuilder sb = new StringBuilder();
		sb.append("height\trank\tname\r\n");
		String sql = "select protein,kinase,cell_line,sensitivity from drug_sensitivity where protein='" + id + "' order by coefficient asc";
		ResultSet rs = dao.executeQuery(sql);
		int rank = 1;
		while(rs.next()) {
			sb.append(rs.getString(4) + "\t" + Integer.toString(rank) + "\t" + rs.getString(3) + "\r\n");
			++rank;
		}
		rs.close();
		return sb.toString();
	}
	
	//protein sensitivity query
	public String getCCLE(String id) throws Exception {
		BaseDao dao = new BaseDao();
		StringBuilder sb = new StringBuilder();
		sb.append("height\trank\tname\r\n");
		String sql = "select PCLName,IC50 from ccle where pubchemCID='" + id + "' order by IC50 asc";
		//String sqlLow = "select score from ccle where pubchemCID='" + id + "' and indicator='IC_50_LOW'";
		//String sqlHigh = "select score from ccle where pubchemCID='" + id + "' and indicator='IC_50_HIGH'";
		ResultSet rs = dao.executeQuery(sql);
		int rank = 1;
		while(rs.next()) {
			sb.append(rs.getString(2) + "\t" + Integer.toString(rank) + "\t" + rs.getString(1) + "\r\n");
			++rank;
		}
		rs.close();
		return sb.toString();
	}
	
	//protein sensitivity query
	public String getCCLEDetail(String id) throws Exception {
		BaseDao dao = new BaseDao();
		StringBuilder sb = new StringBuilder();
		String sql = "select PCLName,Target,Doses,IC50 from ccle where pubchemCID='" + id + "'";
		//String sqlLow = "select score from ccle where pubchemCID='" + id + "' and indicator='IC_50_LOW'";
		//String sqlHigh = "select score from ccle where pubchemCID='" + id + "' and indicator='IC_50_HIGH'";
		ResultSet rs = dao.executeQuery(sql);
		while(rs.next()) {
			sb.append(";" + rs.getString(1) + ":" + Integer.toString(2) + ":" + rs.getString(3) + ":" + rs.getString(4));
		}
		rs.close();
		return sb.toString();
	}
	
	public String getSingleId(String id) throws Exception {
		BaseDao dao = new BaseDao();
		String ret = "";
		String sqlProt = "select uniprot from protein_id_mapping where multiID='" + id + "'";
		String sqlChem = "select pubchemID from chemical_id_mapping where multiID='" + id + "'";
		ResultSet rsProt = dao.executeQuery(sqlProt);
		ResultSet rsChem = dao.executeQuery(sqlChem);
		while(rsProt.next()) {
			ret += rsProt.getString(1);
			break;
		}
		while(rsChem.next()) {
			if(ret.equals("")) {
				ret += rsChem.getString(1);
				break;
			}
		}
		rsProt.close();
		rsChem.close();
		return ret;
	}
	
	//get cell lines ,and return ItemCellLine list, with score 0
	public List<ItemCellLine> getCellLines() throws Exception {
		BaseDao dao = new BaseDao();
		String sql = "select name from cellLine order by name asc";
		ResultSet rs = dao.executeQuery(sql);
		List<ItemCellLine> list = new ArrayList<ItemCellLine>();
		while(rs.next()) {
			ItemCellLine item = new ItemCellLine();
			item.setName(rs.getString(1));
			item.setScore(0);
			list.add(item);
		}
		rs.close();
		return list;
	}
	
	//get drug sensitivity, return ITemcellLine list, with specified score
	public List<ItemCellLine> getSensitivity(String id) throws Exception {
		BaseDao dao = new BaseDao();
		String sql = "select cell_line,coefficient from drug_sensitivity where protein='" + id + "' order by cell_line asc";
		ResultSet rs = dao.executeQuery(sql);
		List<ItemCellLine> list = new ArrayList<ItemCellLine>();
		while(rs.next()) {
			ItemCellLine item = new ItemCellLine();
			item.setName(rs.getString(1));
			item.setScore(rs.getDouble(2));
			list.add(item);
		}
		rs.close();
		return list;
	}
	
	
	
}
