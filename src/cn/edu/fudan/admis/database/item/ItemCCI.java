package cn.edu.fudan.admis.database.item;

public class ItemCCI {
	private String chemical1;
	private String chemical2;
	private String sub_structure;
	
	public void setChemical1(String chemical1) {
		this.chemical1 = chemical1;
	}
	public void setChemical2(String chemical2) {
		this.chemical2 = chemical2;
	}
	public void setSub_structure(String sub_structure){
		this.sub_structure = sub_structure;
	}
	
	public String getChemical1() {
		return chemical1;
	}
	public String getChemical2() {
		return chemical2;
	}
	public String getSub_structure() {
		return sub_structure;
	}
	public String getScore(String scoreType) {
		if (scoreType == "sub_structure") {
			return sub_structure;
		}
		return sub_structure;
	}
}
