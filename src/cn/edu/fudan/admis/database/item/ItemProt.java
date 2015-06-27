package cn.edu.fudan.admis.database.item;

public class ItemProt {
	private String id;
	private String name;
	private String uniprotKB;
	private String proteinName;
	private String organism;
	
/////////////set functions////////////////
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProteinName(String proteinName) {
		this.proteinName = proteinName;
	}
	public void setUniprotKB(String uniprotKB) {
		this.uniprotKB = uniprotKB;
	}
	public void setOrganism(String organism) {
		this.organism = organism;
	}
	//////////get functions///////////////
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getProteinName() {
		return proteinName;
	}
	public String getUniprotKB() {
		return uniprotKB;
	}
	public String getOrganism() {
		return organism;
	}
	
}
