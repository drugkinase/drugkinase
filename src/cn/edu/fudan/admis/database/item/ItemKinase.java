package cn.edu.fudan.admis.database.item;

//a simple CPI items
public class ItemKinase {
	private String kinase;
	private double score;
	
	/////set functions////////////
	public void setKinase(String kinase) {
		this.kinase = kinase;
	}
	public void setScore(double score) {
		this.score = score;
	}
	/////get functions////////////
	public String getKinase() {
		return kinase;
	}
	public double getScore() {
		return score;
	}
}
