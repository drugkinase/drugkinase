package cn.edu.fudan.admis.database.network;

import com.alibaba.fastjson.JSON;

public class JsonOutFile {
	public String searchProtJsonOut(Group group) {
		String jsonString = new String();
		String bCCI = "", aCCI = "", bCPI = "", aCPI = "", bPPI = "", aPPI = "";
		String bProt = "", aProt = "", bChem = "", aChem = "";
		if(group.getEdgeCCI().size() == 0) {
			bCCI = "\"edgeCCI\":[],"; aCCI = "";
			bCPI = "edgeCPI"; aCPI = "links";
		}
		else {
			bCCI = "edgeCCI"; aCCI = "links";
			bCPI = "],\"edgeCPI\":["; aCPI = ",";
		}
		bPPI = "\"edgePPI\":[],"; aPPI = "";
		bChem = "nodesChem"; aChem = "nodes";
		bProt = "],\"nodesProt\":["; aProt = ",";
		
		jsonString = JSON.toJSONString(group);
		jsonString = jsonString.replace(bCCI, aCCI);
		jsonString = jsonString.replace(bCPI, aCPI);
		jsonString = jsonString.replace(bPPI, aPPI);
		jsonString = jsonString.replace(bChem, aChem);
		jsonString = jsonString.replace(bProt, aProt);
		//logger.debug("Json 转换完毕。。。");
		return jsonString;
	}
	
	public String searchChemJsonOut(Group group) {
		String jsonString = new String();
		String bCCI = "", aCCI = "", bCPI = "", aCPI = "", bPPI = "", aPPI = "";
		String bProt = "", aProt = "", bChem = "", aChem = "";
		bCCI = "\"edgeCCI\":[],"; aCCI = "";
		bCPI = "edgeCPI"; aCPI = "links";
		if(group.getEdgePPI().size() == 0) {
			bPPI = "\"edgePPI\":[],"; aPPI = "";
		}
		else {
			bPPI = "],\"edgePPI\":["; aPPI = ",";
		}
		bChem = "nodesChem"; aChem = "nodes";
		bProt = "],\"nodesProt\":["; aProt = ",";
		jsonString = JSON.toJSONString(group);
		jsonString = jsonString.replace(bCCI, aCCI);
		jsonString = jsonString.replace(bCPI, aCPI);
		jsonString = jsonString.replace(bPPI, aPPI);
		jsonString = jsonString.replace(bChem, aChem);
		jsonString = jsonString.replace(bProt, aProt);
		return jsonString;
	}
}
