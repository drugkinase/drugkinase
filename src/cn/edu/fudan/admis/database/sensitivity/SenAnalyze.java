package cn.edu.fudan.admis.database.sensitivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.edu.fudan.admis.database.item.ItemCellLine;
import cn.edu.fudan.admis.database.item.ItemKinase;
import cn.edu.fudan.admis.database.query.SqlQuery;
import cn.edu.fudan.admis.database.servlet.MultiIdMap;

public class SenAnalyze {
	private List<ItemKinase> listKinase;
	private String errorChem;
	
	public boolean isInDatabase(String assemble) throws Exception {
		listKinase = new ArrayList<ItemKinase>();//all items, including duplacate items
		String[] chems = assemble.split(",");
		MultiIdMap multiIdMap = new MultiIdMap();
		for (String id : chems) {
			String id1 = multiIdMap.getSingleID(id);
			List<ItemKinase> listItem = new SqlQuery().getKinase(id1);
			//System.out.println(listItem.size());
			if(listItem.size() > 0) {
				listKinase.addAll(listItem);
			}
			else {
				errorChem = id;
				return false;
			}
		}
		return true;
	}
	
	public List<ItemCellLine> getCellLines(String assemble) throws Exception {
		SqlQuery sqlQuery = new SqlQuery();
		List<ItemCellLine> list = sqlQuery.getCellLines();//get all cell lines, with score 0
		if(isInDatabase(assemble)) {
			Set<ItemKinase> set = new HashSet<ItemKinase>(listKinase);
			for (ItemKinase itemKinase : set) {//traversal each itemKinase in set
				//for each itemKinase, we get the celline list
				double kinScore = itemKinase.getScore();
				List<ItemCellLine> listSen = sqlQuery.getSensitivity(itemKinase.getKinase());
				for (int i = 0; i < list.size(); ++i) {
					double senScore = listSen.get(i).getScore();
					double addedScore = kinScore * senScore;
					//System.out.println(addedScore);
					ItemCellLine element = list.get(i);
					element.addScore(addedScore);
					list.set(i, element);
				}//inner for
			}//outer for
		}
		return list;
	}
	
	public String getErrorChem() {
		return errorChem;
	}
}
