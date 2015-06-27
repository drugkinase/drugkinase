package cn.edu.fudan.admis.database.base;

import java.util.regex.Pattern;
import java.lang.String;

//get and id parameter type(protein or chemical), and obtain other infos
public class Type {
	public String getIdType(String id) {
		String typeString = "";
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if(pattern.matcher(id).matches()) {
			typeString = Base.CHEMTYPE;
			return typeString;
		}
		typeString = Base.PROTTYPE;
		return typeString;
	}
	
	public String getTableName(String id) {
		String table = "";
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if(pattern.matcher(id).matches()) {
			table = "cci";
			return table;
		}
		table = "ppi";
		return table;
	}
	
	public String getColName(String id) {
		//get search column name for given idString of ppi or cci
		String column = "";
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if(pattern.matcher(id).matches()) {
			column = "chemical1";
			return column;
		}
		column = "protein1";
		return column;
	}
	
	public String getColName(String id, String idType) {
		//
		String column = new String();
		if(idType == Base.PROTTYPE) {
			column = "protein";
			return column;
		}
		column = "chemical";
		return column;
	}
	//judge if exist $ in id
	public boolean isAssemble(String id) {
		if(id.contains(",")) {
			return true;
		}
		return false;
	}
}
