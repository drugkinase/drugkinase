package cn.edu.fudan.admis.database.node;

public class NodeChem {
	private String id;
	private String name;
	private String molecular_weight;
	private String smiles_string;
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
	public void setMolecularWeight(String molecular_weight) {
		this.molecular_weight = molecular_weight;
	}
	public void setSmilesString(String smiles_string) {
		this.smiles_string = smiles_string;
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
////////////////////get functions//////////////////////////////
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMolecularWeight() {
		return molecular_weight;
	}
	public String getSmilesString() {
		return smiles_string;
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
