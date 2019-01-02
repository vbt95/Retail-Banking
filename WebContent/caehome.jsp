<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="header.jsp"/> 

<style>
	body {
		font-family: Arial, Helvetica, sans-serif;
	}
	button {
		background-color: #989C93 ;
		color: WHITE;
		border-radius:5px;
		padding: 14px 20px;
		margin: 8px 0;
		border: none;
		cursor: pointer;
		width: 25%;
		font-style: italic;
		font-size:18px;
	}
	
</style>
<title>Home Page</title>
</head>
<body background="images/4.jpg">
	<div align="center">
		<form action="createcustomer.jsp">
			<button type="submit" name="createcustomer" >CREATE CUSTOMER</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="SearchCustomer.jsp">
			<button type="submit" name="updatecustomer">UPDATE CUSTOMER</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="deleteCustomer.jsp">
			<button type="submit" name="deletecustomer">DELETE CUSTOMER</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="createaccount.jsp">
			<button type="submit" name="createaccoutn">CREATE ACCOUNT</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="deleteaccountmain.jsp">
			<button type="submit" name="deleteaccount">DELETE ACCOUNT</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="CustomerServlet">
			<button type="submit" method="get" name="viewallcustomerstatus">VIEW ALL CUSTOMER STATUS</button>			
			<input type="hidden" name="action" value="viewallcustomerstatus">
		</form>
	</div><br><br>

	<div align="center">
		<form action="AccountServlet">
			<button type="submit" method="get" name="viewallaccountstatus">VIEW ALL ACCOUNT STATUS</button>
			<input type="hidden" name="action" value="viewallaccountstatus">
		</form>
	</div>

</body>
<footer style="color:white;background-color:black;text-align:center;padding:20px;">
<h6>&copy Copyright to HJA16 2018</h6>
</footer>
</html>

