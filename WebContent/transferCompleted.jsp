<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp" />
<%
	String sessionId = (String) session.getAttribute("sessionId");
	if (sessionId == null)
		response.sendRedirect("index.jsp");
	else {
		if (session.getId() != sessionId)
			response.sendRedirect("index.jsp");

		String id = (String) session.getAttribute("id");
		if (id == null)
			response.sendRedirect("index.jsp");
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer completed</title>
<link href="Style.css" rel="stylesheet">
</head>
<body>
	<div id="first">
		<h1>Amount transfer completed successfully</h1>
		<br>
		<br>
		<h3>Source account details</h3>
		<br>
		<br>
		<table>
			<tr>
				<th>Source account id</th>
				<th>Balance before transfer</th>
				<th>Latest balance</th>
			</tr>
			<tr>
				<%@page import="java.util.ArrayList"%>
				<%
					ArrayList details = (ArrayList) request.getAttribute("details");
				%>
			
			<tr>
				<td><%=details.get(1)%></td>
				<td><%=details.get(2)%></td>
				<td><%=details.get(3)%></td>
			</tr>
		</table>


		<br>
		<br>
		<h3>Target account details</h3>
		<br>
		<br>
		<table>
			<tr>
				<th>Target account id</th>
				<th>Balance before transfer</th>
				<th>Latest balance</th>
			</tr>
			<tr>
			<tr>
				<td><%=details.get(4)%></td>
				<td><%=details.get(5)%></td>
				<td><%=details.get(6)%></td>
			</tr>
		</table>

	</div>
</body>
</html>