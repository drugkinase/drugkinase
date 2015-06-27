package cn.edu.fudan.admis.database.network;

import java.util.HashMap;
import java.util.Map;

import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.item.ItemCPI;

public class Weight {
	public String getFirstWeight() {
		return Integer.toString(Base.FIRST_NODE_WEIGHT + Base.LAMDA);
	}
	
	public double getOtherWeight(double weight) {
		return weight * Base.FIRST_NODE_WEIGHT + Base.LAMDA;
	}
	
	public double getCPIWeight(ItemCPI itemCPI, String sortType) {
		//chemical", "protein", "DTHybrid", "kbmf", "minhash", "RLS", "svm", "drugbank", "stitch"
		Map<String, String> CPI_MAP = new HashMap<String, String>(){
			{
				put("chemical", "1");
				put("protein", "2");
				put("DTHybrid", "3");
				put("kbmf", "4");
				put("minhash", "5");
				put("RLS", "6");
				put("svm", "7");
				put("drugbank", "8");
				put("stitch", "9");
				put("kiba", "10");
				put("combined_score", "11");
			}
		};
		double ret = 0.0;
		switch (Integer.parseInt(CPI_MAP.get(sortType))) {
		case 3:
			ret =  Double.parseDouble(itemCPI.getDthybrid());
			break;
		case 4:
			ret = Double.parseDouble(itemCPI.getKbmf());
			break;
		case 5:
			ret = Double.parseDouble(itemCPI.getMinhash());
			break;
		case 6:
			ret = Double.parseDouble(itemCPI.getRls());
			break;
		case 7:
			ret = Double.parseDouble(itemCPI.getSvm());
			break;
		case 8:
			ret = Double.parseDouble(itemCPI.getDrugbank());
			break;
		case 9:
			ret = Double.parseDouble(itemCPI.getStitch());
			break;
		case 10:
			ret = Double.parseDouble(itemCPI.getKiba());
			break;
		case 11:
			ret = Double.parseDouble(itemCPI.getCombined_score());
			break;
		default:
			break;
		}
		return ret;
	}
	
}
