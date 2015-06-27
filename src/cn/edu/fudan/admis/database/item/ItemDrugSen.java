package cn.edu.fudan.admis.database.item;

public class ItemDrugSen {
	private String kinase;
	private String cellLine;
	private String coefficient;
	
	//////set functions////////////
	public void setKinase(String kinase) {
		this.kinase  = kinase;
	}
	public void setCellLine(String cellLine) {
		this.cellLine = cellLine;
	}
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}
	////////get functions///////////
	public String getKinase() {
		return kinase;
	}
	public String getCellLine() {
		return cellLine;
	}
	public String getCoefficient() {
		return coefficient;
	}
}
