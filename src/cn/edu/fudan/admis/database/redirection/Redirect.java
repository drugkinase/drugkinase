package cn.edu.fudan.admis.database.redirection;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Redirect {
	//jump to noResult page
	public void reNoResult(HttpServletResponse response, HttpServletRequest request, String retString) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("noResult", retString);
		response.sendRedirect("noResults.jsp");
	}
	//jump to showResults page
	public void showResult(HttpServletResponse response, HttpServletRequest request, String retString) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("resultset", retString);
		response.sendRedirect("showResults.jsp");
	}
	public void showAssemble(HttpServletResponse response, HttpServletRequest request, String retString) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("assemble", retString);
		response.sendRedirect("showAssemble.jsp");
	}
}
