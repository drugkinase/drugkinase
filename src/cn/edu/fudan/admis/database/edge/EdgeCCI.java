package cn.edu.fudan.admis.database.edge;

public class EdgeCCI {
	private String source;
	private String target;
	private String sub_structure;
	private String attribute;
	private String weight;
//////////////set functions//////////////
	public void setSource(String source) {
		this.source = source;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public void setSub_structure(String sub_structure) {
		this.sub_structure = sub_structure;
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
	public String getSub_structure() {
		return sub_structure;
	}
	public String getAttribute() {
		return attribute;
	}
	public String getWeight() {
		return weight;
	}
}
