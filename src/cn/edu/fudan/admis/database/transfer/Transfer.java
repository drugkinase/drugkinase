package cn.edu.fudan.admis.database.transfer;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.edge.EdgeCCI;
import cn.edu.fudan.admis.database.edge.EdgeCPI;
import cn.edu.fudan.admis.database.edge.EdgePPI;
import cn.edu.fudan.admis.database.item.ItemCCI;
import cn.edu.fudan.admis.database.item.ItemCPI;
import cn.edu.fudan.admis.database.item.ItemChem;
import cn.edu.fudan.admis.database.item.ItemPPI;
import cn.edu.fudan.admis.database.item.ItemProt;
import cn.edu.fudan.admis.database.node.NodeChem;
import cn.edu.fudan.admis.database.node.NodeProt;

public class Transfer {
	//protein node
	public NodeProt transProtNode(ItemProt item, String size, String count) {
		NodeProt node = new NodeProt();
		node.setId(item.getId());
		node.setName(item.getName());
		node.setUniprotKB(item.getUniprotKB());
		node.setProteinName(item.getProteinName());
		node.setOrganism(item.getOrganism());
		node.setType(Base.PROTTYPE);
		node.setSize(size);
		node.setCount(count);
		return node;
	}
	//chemical node
	public NodeChem transChemNode(ItemChem item, String size, String count) {
		NodeChem node = new NodeChem();
		node.setId(item.getId());
		node.setName(item.getName());
		node.setMolecularWeight(item.getMolecularWeight());
		node.setSmilesString(item.getSmilesString());
		node.setType(Base.CHEMTYPE);
		node.setSize(size);
		node.setCount(count);
		return node;
	}
	//CPI edge
	public EdgeCPI transCPIEdge(String source, ItemCPI item, String weight) {
		EdgeCPI edge = new EdgeCPI();
		edge.setSource(source);
		edge.setTarget(item.getName());
		edge.setDrugbank(item.getDrugbank());
		edge.setDthybrid(item.getDthybrid());
		edge.setKbmf(item.getKbmf());
		edge.setMinhash(item.getMinhash());
		edge.setRls(item.getRls());
		edge.setSvm(item.getSvm());
		edge.setStitch(item.getStitch());
		edge.setKiba(item.getKiba());
		edge.setCombined_score(item.getCombined_score());
		edge.setAttribute("CPI");
		edge.setWeight(weight);
		return edge;
	}
	//PPI edge
	public EdgePPI transPPIEdge(ItemPPI item, String weight) {
		EdgePPI edge = new EdgePPI();
		edge.setSource(item.getProtein1());
		edge.setTarget(item.getProtein2());
		edge.setDomain_score(item.getDomain_score());
		edge.setGo_score(item.getGo_score());
		edge.setSeq_score(item.getSeq_score());
		edge.setAttribute("PPI");
		edge.setWeight(weight);
		return edge;
	}
	//CCI edge
	public EdgeCCI transCCIEdge(ItemCCI item, String weight) {
		EdgeCCI edge = new EdgeCCI();
		edge.setSource(item.getChemical1());
		edge.setTarget(item.getChemical2());
		edge.setSub_structure(item.getSub_structure());
		edge.setAttribute("CCI");
		edge.setWeight(weight);
		return edge;
	}
	//transfer ItemPPI list to EdgePPI list
	public List<EdgePPI> transPPIEdgeList(List<ItemPPI> list) {
		List<EdgePPI> listEdge = new ArrayList<EdgePPI>();
		for (ItemPPI itemPPI : list) {
			listEdge.add(transPPIEdge(itemPPI, "1"));
		}
		return listEdge;
	}
	//transfer ItemCCI list to EdgeCCI list
	public List<EdgeCCI> transCCIEdgeList(List<ItemCCI> list) {
		List<EdgeCCI> listEdge = new ArrayList<EdgeCCI>();
		for (ItemCCI itemCCI : list) {
			listEdge.add(transCCIEdge(itemCCI, "1"));
		}
		return listEdge;
	}
	
	public ArrayList<String> itemCPIList2NameList (List<ItemCPI> list) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (ItemCPI itemCPI : list) {
			arrayList.add(itemCPI.getName());
		}
		return arrayList;
	}
}
