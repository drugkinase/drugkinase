package cn.edu.fudan.admis.database.download;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.edu.fudan.admis.database.base.Base;


public class Download {
	//write CPI results into files
	public void writeCPI(String str) throws IOException {
		String[] retArray = str.split(";");
		String pathname = Base.DOWNLOADPATH + "/data/temp/CPI_" + retArray[1] + ".txt";
		File writename = new File(pathname);
		if (!writename.exists()) {
			BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
			writename.createNewFile();
			StringBuilder ret = new StringBuilder();
			ret.append("Interactions associated to " + retArray[1] + "\r\n");
			ret.append("ID\tName\tDTHybrid\tkbmf\tminhash\tRLS\tsvm\tdrugbank\tstitch\tkiba\tintegrative\r\n");
			for (int i = 2; i < retArray.length; ++i) {
				String[] secondStr = retArray[i].split(",");
				for (int j = 0; j < secondStr.length; ++j) {
					ret.append(secondStr[j] + "\t");
				}
				ret.append("\r\n");
			}
			hander.write(ret.toString());
			hander.flush();
			hander.close();
		}
	}
	//write CCI results into files
	public void writeCCI(String str) throws IOException {
		String[] retArray = str.split(";");
		String pathname = Base.DOWNLOADPATH + "/data/temp/CCI_" + retArray[1] + ".txt";
		File writename = new File(pathname);
		if (!writename.exists()) {
			BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
			writename.createNewFile();
			StringBuilder ret = new StringBuilder();
			ret.append("The Similar " + retArray[0] + "s with " + retArray[1] + "\r\n");
			ret.append("ID\tName\tSub-structure\r\n");
			for (int i = 2; i < retArray.length; ++i) {
				String[] secondStr = retArray[i].split(",");
				for (int j = 0; j < secondStr.length; ++j) {
					ret.append(secondStr[j] + "\t");
				}
				ret.append("\r\n");
			}
			hander.write(ret.toString());
			hander.flush();
			hander.close();
		}
	}
	//write PPI results into files
	public void writePPI(String str) throws IOException {
		String[] retArray = str.split(";");
		String pathname = Base.DOWNLOADPATH + "/data/temp/PPI_" + retArray[1] + ".txt";
		File writename = new File(pathname);
		if (!writename.exists()) {
			BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
			writename.createNewFile();
			StringBuilder ret = new StringBuilder();
			ret.append("The Similar " + retArray[0] + "s with " + retArray[1] + "\r\n");
			ret.append("ID\tName\tDomain\tGO\tSequence\r\n");
			for (int i = 2; i < retArray.length; ++i) {
				String[] secondStr = retArray[i].split(",");
				for (int j = 0; j < secondStr.length; ++j) {
					ret.append(secondStr[j] + "\t");
				}
				ret.append("\r\n");
			}
			hander.write(ret.toString());
			hander.flush();
			hander.close();
		}
	}
	
	//write PPI results into files
	public void writeCCLE(String str) throws IOException {
		String[] retArray = str.split(";");
		String pathname = Base.DOWNLOADPATH + "/data/temp/CCLE_" + retArray[0] + ".txt";
		File writename = new File(pathname);
		if (!writename.exists()) {
			BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
			writename.createNewFile();
			StringBuilder ret = new StringBuilder();
			ret.append("The CCLE Results relative to " + retArray[0] + "\r\n");
			ret.append("CCLE Name\tTarget\tDoes\tIC50\r\n");
			for (int i = 1; i < retArray.length; ++i) {
				String[] secondStr = retArray[i].split(":");
				for (int j = 0; j < secondStr.length; ++j) {
					ret.append(secondStr[j] + "\t");
				}
				ret.append("\r\n");
			}
			hander.write(ret.toString());
			hander.flush();
			hander.close();
		}
	}
		
	//write Assemble results into files
	public void writeAssemble(String str) throws IOException {
		String[] retArray = str.split(";");
		String pathname = Base.DOWNLOADPATH + "/data/temp/Assemble_" + retArray[0] + ".txt";
		File writename = new File(pathname);
		//if (!writename.exists()) {
			BufferedWriter hander = new BufferedWriter(new FileWriter(writename));
			writename.createNewFile();
			StringBuilder ret = new StringBuilder();
			ret.append("The Assemble Results relative to " + retArray[0] + "\r\n");
			ret.append("CCLE Name\tScore\r\n");
			for (int i = 1; i < retArray.length; ++i) {
				String[] secondStr = retArray[i].split(",");
				for (int j = 0; j < secondStr.length; ++j) {
					ret.append(secondStr[j] + "\t");
				}
				ret.append("\r\n");
			}
			hander.write(ret.toString());
			hander.flush();
			hander.close();
		//}
	}
}
