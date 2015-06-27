package cn.edu.fudan.admis.database.jsp;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.admis.database.query.SqlQuery;

public class Jsp {
	public String getStringLabel(List<ArrayList<String>> list) {
		ArrayList<String> labelArray = list.get(0);//get labels
		String labelStr = "";
		labelStr += labelArray.get(0);
		for(int i = 1; i < labelArray.size(); ++i) {
			labelStr += ";" + labelArray.get(i);
		}
		return labelStr;
	}
	
	public String getStringScore(List<ArrayList<String>> list) {
		ArrayList<String> scoreArray = list.get(1);//get scores
		String scoreStr = ""; 
		scoreStr += scoreArray.get(0);
		for(int i = 1; i < scoreArray.size(); ++i) {
			scoreStr += ";" + scoreArray.get(i);
		}
		return scoreStr;
	}
	
	public String getCCLE(String id) throws Exception {
		String retString = new SqlQuery().getCCLEDetail(id);
		return id + retString;
	}
}
