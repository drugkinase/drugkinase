package cn.edu.fudan.admis.database.edge;

public class EdgeCPI {
	private String source;
	private String target;
	private String dthybrid;
	private String kbmf;
	private String minhash;
	private String rls;
	private String svm;
	private String drugbank;
	private String stitch;
	private String kiba;
	private String combined_score;
	private String attribute;
	private String weight;
	
//////////////////get functions ///////////////////////////////
	public String getSource() {
		return source;
	}
	public String getTarget() {
		return target;
	}
	public String getDthybrid() {
		return dthybrid;
	}
	public String getKbmf(){
		return kbmf;
	}
	public String getMinhash() {
		return minhash;
	}
	public String getRls() {
		return rls;
	}
	public String getSvm(){
		return svm;
	}
	public String getDrugbank() {
		return drugbank;
	}
	public String getStitch() {
		return stitch;
	}
	public String getKiba() {
		return kiba;
	}
	public String getCombined_score() {
		return combined_score;
	}
	public String getAttribute() {
		return attribute;
	}
	public String getWeight() {
		return weight;
	}
///////////////////set functions ////////////////////////////////
	public void setSource(String source){
		this.source = source;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public void setDthybrid(String dthybrid){
		this.dthybrid = dthybrid;
	}
	public void setKbmf(String kbmf){
		this.kbmf = kbmf;
	}
	public void setMinhash(String minhash) {
		this.minhash = minhash;
	}
	public void setRls(String rls){
		this.rls = rls;
	}
	public void setSvm(String svm){
		this.svm = svm;
	}
	public void setDrugbank(String drugbank){
		this.drugbank = drugbank;
	}
	public void setStitch(String stitch){
		this.stitch = stitch;
	}
	public void setKiba(String kiba) {
		this.kiba = kiba;
	}
	public void setCombined_score(String combined_score) {
		this.combined_score = combined_score;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
