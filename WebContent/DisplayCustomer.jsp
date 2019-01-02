<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"/> 
<% 
	String sessionId=(String)session.getAttribute("sessionId");
	if(sessionId==null)
		response.sendRedirect("index.jsp");
	else{
		if(session.getId()!=sessionId)
			response.sendRedirect("index.jsp");
	
	String id=(String)session.getAttribute("id");
	if(id==null)
		response.sendRedirect("index.jsp");
	}
	%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Details</title>
</head>
<link href="Style.css" rel="stylesheet">
<body>
	<h1>Customer Details</h1>
			<table>
				<tr>
					<td>Customer ID :</td>
					<td><%= request.getAttribute("id") %></td>
				</tr>
				<tr>
					<td>SSN :</td>
					<td><%=request.getAttribute("ssn") %></td>
				</tr>	
				<tr>
					<td>Customer Name:</td>
					<td><%=request.getAttribute("name") %></td>
				</tr>
				<tr>
					<td>Age:</td>
					<td><%= request.getAttribute("age") %></td>
				</tr>
				<tr>
					<td>Address Line 1:</td>
					<td><%= request.getAttribute("add1") %></td>
				</tr>
				<tr>
					<td>Address Line 2:</td>
					<td><%= request.getAttribute("add2") %></td>
				</tr>
				<tr>
					<td>City :</td>
					<td><%= request.getAttribute("city") %></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><%= request.getAttribute("state") %></td>
				</tr>
				<tr>
					<td>Status:</td>
					<td><%= request.getAttribute("status") %></td>
				</tr>
				<tr>
					<td>Message :</td>
					<td><%= request.getAttribute("message") %></td>
				</tr>
				<tr>
					<td>Last Updated :</td>
					<td><%= request.getAttribute("last_updated") %></td>
				</tr>	
			</table>
</body>
</html>