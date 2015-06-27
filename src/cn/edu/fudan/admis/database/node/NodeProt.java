package cn.edu.fudan.admis.database.node;

public class NodeProt {
	private String id;
	private String name;
	private String uniprotKB;
	private String proteinName;
	private String organism;
	private String type;
	private String size;
	private String count;
	
////////////////////set functions//////////////////////////////
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUniprotKB(String uniprotKB) {
		this.uniprotKB = uniprotKB;
	}
	public void setProteinName(String proteinName) {
		this.proteinName = proteinName;
	}
	public void setOrganism(String organism) {
		this.organism = organism;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setCount(String count) {
		this.count = count;
	}
	////////////get functions/////////////////
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getUniprotKB() {
		return uniprotKB;
	}
	public String getProteinName() {
		return proteinName;
	}
	public String getOrganism() {
		return organism;
	}
	public String getType() {
		return type;
	}
	public String getSize() {
		return size;
	}
	public String getCount() {
		return count;
	}
}
