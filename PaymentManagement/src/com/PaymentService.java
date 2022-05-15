package com;

import com.Payment;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PaymentService")
public class PaymentService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Payment PaymentObj = new Payment();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentService() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = PaymentObj.insertPayment(request.getParameter("BillID"),      
				request.getParameter("cardID"),
				request.getParameter("cardType"),
				request.getParameter("totalAmount")); 
				response.getWriter().write(output);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = PaymentObj.updatePayment(paras.get("hidPaymentIDSave").toString(),     
		    		paras.get("BillID").toString(),     
		    		paras.get("cardID").toString(), 
		    		paras.get("cardType").toString(),
		    		paras.get("totalAmount").toString()); 
		 
		 			response.getWriter().write(output);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 
		 String output = PaymentObj.deletePayment(paras.get("PaymentID").toString());  
		 
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

}
