package cn.edu.fudan.admis.database.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.base.BaseDao;
import cn.edu.fudan.admis.database.base.Type;
import cn.edu.fudan.admis.database.chart.TsvData;
import cn.edu.fudan.admis.database.download.Download;
import cn.edu.fudan.admis.database.redirection.Redirect;
import cn.edu.fudan.admis.database.sensitivity.SenAnalyze;
import cn.edu.fudan.admis.database.time.ConTimerDB;

public class SimpleSearchServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public SimpleSearchServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//if(new BaseDao().connectState() == null) {
			new ConTimerDB();//start the reconnection processing
		//}
		String id = request.getParameter("search");
		if (id == "") {
			response.sendRedirect("index.jsp");
			return;
		}//if
		//do assemble search
		if (new Type().isAssemble(id)) {
			try {
				searchAssemble(request, response, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				id = new MultiIdMap().getSingleID(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			search(request, response, id);
		}
	}
	
	public void searchAssemble(HttpServletRequest request, HttpServletResponse response, String assemble) throws Exception {
		SenAnalyze senAnalyze = new SenAnalyze();
		Redirect redirect = new Redirect();
		if(senAnalyze.isInDatabase(assemble)) {
			String retString = assemble + new DataFetch().getAssembleString(assemble);
			new TsvData().writeAssemble(retString);
			new Download().writeAssemble(retString);
			redirect.showAssemble(response, request, retString);
		} else {
			String retString = "Sorry! There is no Chemical for " + senAnalyze.getErrorChem();
			redirect.reNoResult(response, request, retString);
		}
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
		String idType = new Type().getIdType(id);
		String sql = "select * from cpi where " + new Type().getColName(id, idType) + "='" + id + "'";
		String retString = new String();
		try {
			retString = new DataFetch().getAllData(sql, id, "CPI");
			TsvData tsvData = new TsvData();
			if(idType == Base.CHEMTYPE) {
				tsvData.writeIC50(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Redirect redirect = new Redirect();
		if(retString.split(";").length < 3) {
			retString = "There is no result for " + id;
			redirect.reNoResult(response, request, retString);
		}
		else {
			retString = getReturnType(idType) + ";" + id + retString;
			redirect.showResult(response, request, retString);
		}
	}
	//output id type
	public String getReturnType(String type) {
		if(type == Base.PROTTYPE)
			return Base.CHEMTYPE;
		return Base.PROTTYPE;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
