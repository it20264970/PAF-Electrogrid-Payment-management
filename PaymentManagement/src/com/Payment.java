package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electropayment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String BillID, String cardID, String cardType, String totalAmount)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into payment1(`PaymentID`,`BillID`,`cardID`,`cardType`,`totalAmount`)" + " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, BillID);
			 preparedStmt.setString(3, cardID);
			 preparedStmt.setString(4, cardType);
			 preparedStmt.setString(5, totalAmount);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newPayment = readPayment(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readPayment()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Bill ID</th><th>Card ID</th><th>Card Type</th><th>Total Amount</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from payment1";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String PaymentID = Integer.toString(rs.getInt("PaymentID"));
				 String BillID = rs.getString("BillID");
				 String cardID = rs.getString("cardID");
				 String cardType = rs.getString("cardType");
				 String totalAmount = rs.getString("totalAmount");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidPaymentIDUpdate\' name=\'hidPaymentIDUpdate\' type=\'hidden\' value=\'" + PaymentID + "'>" 
							+ BillID + "</td>"; 
				output += "<td>" + cardID + "</td>";
				output += "<td>" + cardType + "</td>";
				output += "<td>" + totalAmount + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-payid='" + PaymentID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Payment.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updatePayment(String PaymentID, String BillID, String cardID, String cardType, String totalAmount)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE payment1 SET BillID=?,cardID=?,cardType=?,totalAmount=?"  + "WHERE PaymentID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, BillID);
			 preparedStmt.setString(2, cardID);
			 preparedStmt.setString(3, cardType);
			 preparedStmt.setString(4, totalAmount);
			 preparedStmt.setInt(5, Integer.parseInt(PaymentID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newPayment = readPayment();    
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deletePayment(String PaymentID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from payment1 where PaymentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(PaymentID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPayment = readPayment();    
			output = "{\"status\":\"success\", \"data\": \"" +  newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
