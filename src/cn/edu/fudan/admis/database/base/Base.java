package cn.edu.fudan.admis.database.base;

//some important configure info
public class Base {
	//D:/Tomcat 6.0/webapps/drugkinase
	public static final String PROTTYPE = "protein";//ID type
	public static final String CHEMTYPE = "chemical";//ID type
	public static final String basepath = "D:/Tomcat 6.0/webapps/drugkinase";//ID type
	public static final String basepath2 = "E:/Conference&Journal/Database/Workspace/drugkinase/WebRoot";//ID type
	public static final String DOWNLOADPATH = basepath2;
	public static final String NETDATAPATH = basepath2 + "/network/data/";
	public static final String CHARTDATAPATH =basepath2 + "/chart/data/";
	public static final String SENIC50PATH = basepath2 + "/ccle/data/";
	public static final String ASSEMBLEPATH = basepath2 + "/assemble/data/";
	public static final int FIRST_NODE_WEIGHT = 10;//first node weight
	public static final int LAMDA = 1;//the smooth parameter
	public static final String CPI_COLUMN[] = {"chemical", "protein", "DTHybrid", "kbmf", 
		"minhash", "RLS", "svm", "drugbank", "stitch", "kiba", "combined_score"};
	public static final String PPI_COLUMN[] = {"protein1", "protein2", "domain_score", "go_score", "seq_score"};
	public static final String CCI_COLUMN[] = {"chemical1", "chemical2", "sub_structure"};
	public static final int CPI_MAX_NODE = 20; //The CPI nodes to be shown max nodes
	public static final int CCI_PPI_MAX_NODE = 20; //The CCI or PPI nodes to be shown max nodes
}
