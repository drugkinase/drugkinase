package cn.edu.fudan.admis.database.edge;

public class EdgePPI {
	private String source;
	private String target;
	private String domain_score;
	private String go_score;
	private String seq_score;
	private String attribute;
	private String weight;
	
//////////////set functions//////////////
	public void setSource(String source) {
		this.source = source;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
//////////////get functions/////////////////
	public String getSource() {
		return source;
	}
	public String getTarget() {
		return target;
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
	public String getAttribute() {
		return attribute;
	}
	public String getWeight() {
		return weight;
	}
}
