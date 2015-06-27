package cn.edu.fudan.admis.database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.edu.fudan.admis.database.base.Base;
import cn.edu.fudan.admis.database.base.Type;

public class SimilarSearchServlet extends HttpServlet {
	//public static final String 
	/**
	 * Constructor of the object.
	 */
	public SimilarSearchServlet() {
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
		String id = request.getParameter("ID");
		String sql = "select * from " + new Type().getTableName(id) + " where " + new Type().getColName(id) + "='" + id + "';";
		String retString = new String();
		try {
			retString = new DataFetch().getAllData(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String type = new Type().getIdType(id);
		if(type == Base.PROTTYPE) {
			goPPIPage(request, response, retString, id);
		} else {
			goCCIPage(request, response, retString, id);
		}
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
	
	public void goPPIPage(HttpServletRequest request, HttpServletResponse response, String retString, String id) throws IOException {
		HttpSession session = request.getSession();
		String type = new Type().getIdType(id);
		try {
			session.setAttribute("domain", new DataFetch().getPPIListDomain(id, "domain_score"));
			session.setAttribute("go", new DataFetch().getPPIListGo(id, "go_score"));
			session.setAttribute("seq", new DataFetch().getPPIListSeq(id, "seq_score"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		retString = type + ";" + id + retString;
		if(retString.split(";").length < 3) {
			session.setAttribute("noResult", retString);
			response.sendRedirect("noResults.jsp");
		}else {
			String scoreType[] = {"domain", "go", "seq"};
			session.setAttribute("scoreType", scoreType);
			session.setAttribute("rets", retString);
			response.sendRedirect("detailedResultsPPI.jsp");
		}
	}
	
	public void goCCIPage(HttpServletRequest request, HttpServletResponse response, String retString, String id) throws IOException {
		HttpSession session = request.getSession();
		String type = new Type().getIdType(id);
		try {
			session.setAttribute("score", new DataFetch().getCCIListScore(id, "sub_structure"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		retString = type + ";" + id + retString;
		if(retString.split(";").length < 3) {
			session.setAttribute("noResult", retString);
			response.sendRedirect("noResults.jsp");
		}else {
			String scoreType[] = {"score"};
			session.setAttribute("scoreType", scoreType);
			session.setAttribute("rets", retString);
			response.sendRedirect("detailedResultsCCI.jsp");
		}
	}

}
