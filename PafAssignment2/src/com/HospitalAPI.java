package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import controller.HospitalDBHandler;

/**
 * Servlet implementation class HospitalAPI
 */
@WebServlet("/HospitalAPI")
public class HospitalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HospitalDBHandler Hosobj = new HospitalDBHandler();
   
    public HospitalAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = Hosobj.HospitalInsert(
				request.getParameter("hospital_reg_no"),
				request.getParameter("hos_name"),
				request.getParameter("hos_type"),
				request.getParameter("AddressLine1"),
				request.getParameter("city"),
				request.getParameter("district"),
				request.getParameter("province"),
				request.getParameter("telephone"),
				request.getParameter("hospital_fee"),
				request.getParameter("hos_password"));
				
		response.getWriter().write(output);
			
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					
			String[] params = queryString.split("&");
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e)
		{
		}
		
		return map;
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		
		String output = Hosobj.updateHos(
				paras.get("hidHosIDSave").toString(),
				paras.get("hos_name").toString(),
				paras.get("hos_type").toString(),
				paras.get("AddressLine1").toString(),
				paras.get("city").toString(),
				paras.get("district").toString(),
				paras.get("province").toString(),
				paras.get("telephone").toString(),
				paras.get("hospital_fee").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		String output = Hosobj.deleteHospital(paras.get("hos_id").toString());
		response.getWriter().write(output);
	}

}
