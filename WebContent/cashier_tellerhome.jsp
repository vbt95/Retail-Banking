<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
	body {font-family: Arial, Helvetica, sans-serif;}
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
		<form action="SearchSpecificCustomer.jsp">
			<button type="submit" name="createcustomer" >GET CUSTOMER DETAILS</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="displayAccountInfo.jsp">
			<button type="submit" name="viewaccount">GET ACCOUNT DETAILS</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="transfer.jsp">
			<button type="submit" name="transfer">TRANSFER</button>
		</form>
	</div><br><br>
	<div align="center">
		<form action="getStatementMain.jsp">
			<button type="submit" name="statement">GET STATEMENT</button>
		</form>
	</div><br><br>

</body>

</html>

