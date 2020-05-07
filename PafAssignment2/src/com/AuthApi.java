package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.HospitalDBHandler;

@WebServlet("/AuthApi")
public class AuthApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HospitalDBHandler Hosobj = new HospitalDBHandler();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String authStatus = Hosobj.loginHos(request.getParameter("txtUsername1"),
		request.getParameter("txtPassword1"));
		String output = "";
		
		if (authStatus.equals("success"))
		{
			output = "{\"status\":\"success\", \"data\": \"\"}";
			session.setAttribute("Username", request.getParameter("txtUsername1"));
		}
		else
		{
			output = "{\"status\":\"error\", \"data\": \"" + authStatus + "\"}";
		}
		response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		session.invalidate();
		response.getWriter().write("success");
	}
}
