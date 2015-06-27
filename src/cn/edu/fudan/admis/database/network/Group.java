package cn.edu.fudan.admis.database.network;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.admis.database.edge.EdgeCCI;
import cn.edu.fudan.admis.database.edge.EdgeCPI;
import cn.edu.fudan.admis.database.edge.EdgePPI;
import cn.edu.fudan.admis.database.node.NodeChem;
import cn.edu.fudan.admis.database.node.NodeProt;


public class Group {
	private List<NodeProt> nodesProt = new ArrayList<NodeProt>();
	private List<NodeChem> nodesChem = new ArrayList<NodeChem>();
	private List<EdgeCPI> linksCPI = new ArrayList<EdgeCPI>();
	private List<EdgeCCI> linksCCI = new ArrayList<EdgeCCI>();
	private List<EdgePPI> linksPPI = new ArrayList<EdgePPI>();
	/////////////get functions///////////////////
	public List<NodeProt> getNodesProt() {
		return nodesProt;
	}
	public List<NodeChem> getNodesChem() {
		return nodesChem;
	}
	public List<EdgeCPI> getEdgeCPI() {
		return linksCPI;
	}
	public List<EdgeCCI> getEdgeCCI() {
		return linksCCI;
	}
	public List<EdgePPI> getEdgePPI() {
		return linksPPI;
	}
	////////////set functions////////////////
	public void setEdgeCCI(List<EdgeCCI> linksCCI) {
		this.linksCCI = linksCCI;
	}
	public void setEdgePPI(List<EdgePPI> linksPPI) {
		this.linksPPI = linksPPI;
	}
}
