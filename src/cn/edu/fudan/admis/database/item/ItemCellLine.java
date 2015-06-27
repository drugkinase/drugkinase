package cn.edu.fudan.admis.database.item;

public class ItemCellLine implements Comparable{
	private String name;
	private double score;// the total score;
	
	//set functions///
	public void setName(String name) {
		this.name = name;
	}
	public void setScore(double score) {
		this.score = score;
	}
	///get functions/////////
	public String getName() {
		return name;
	}
	public double getScore() {
		return score;
	}
	
	public void addScore(double score) {
		this.score += score;
	}
	
	public int compareTo(Object o) {
		ItemCellLine itemCellLine = (ItemCellLine)o;
		if(score < itemCellLine.getScore()) {
			return -1;
		}
		if(score > itemCellLine.getScore()) {
			return 1;
		}
		return 0;
	}
}
