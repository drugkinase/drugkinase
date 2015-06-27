package cn.edu.fudan.admis.database.item;

public class ItemChem {
	private String id;
	private String name;
	private String molecular_weight;
	private String smiles_string;

	//////////set functions///////////////
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
	/////////get functions//////////////////
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
	
}
