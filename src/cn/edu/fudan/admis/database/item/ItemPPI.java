package cn.edu.fudan.admis.database.item;

public class ItemPPI {
	private String protein1;
	private String protein2;
	private String domain_score;
	private String go_score;
	private String seq_score;
	
	////////////set functions/////////////
	public void setProtein1(String protein1) {
		this.protein1 = protein1;
	}
	public void setProtein2(String protein2) {
		this.protein2 = protein2;
	}
	public void setDomain_score(String domain_score) {
		this.domain_score = domain_score;
	}
	public void setGo_score(String go_score) {
		this.go_score = go_score;
	}
	public void setSeq_score(String seq_score) {
		this.seq_score = seq_score;
	}
	///////////get functions/////////////
	public String getProtein1() {
		return protein1;
	}
	public String getProtein2() {
		return protein2;
	}
	public String getDomain_score() {
		return domain_score;
	}
	public String getGo_score() {
		return go_score;
	}
	public String getSeq_score() {
		return seq_score;
	}
	public String getScore(String scoreType) {
		if (scoreType == "domain_score") {
			return domain_score;
		}
		else if(scoreType == "go_score") {
			return go_score;
		}
		return seq_score;
	}
}
