<%@ page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/pay.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Payment Management</h1>
				<form id="formPayment" name="formPayment" method="post" action="PaymentUI.jsp">  
					Bill ID:  
 	 				<input id="BillID" name="BillID" type="text"  class="form-control form-control-sm">
					<br>Card ID:   
  					<input id="cardID" name="cardID" type="text" class="form-control form-control-sm">   
  					<br>Card Type:   
  					<input id="cardType" name="cardType" type="text"  class="form-control form-control-sm">
					<br>Total Amount:   
  					<input id="totalAmount" name="totalAmount" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divPaymentGrid">
					<%
						Payment PaymentObj = new Payment();
						out.print(PaymentObj.readPayment());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>