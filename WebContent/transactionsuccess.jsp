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
<title>Transaction successful</title>
<link href="Style.css" rel="stylesheet">
</head>
<body>
	<div id="first">
		<%@page import="java.util.ArrayList"%>
		<%
			ArrayList status = (ArrayList) request.getAttribute("status");
		%>
		<table>
			<tr>
				<th>Account Id</th>
				<th>Balance before transaction</th>
				<th>Latest balance</th>
			</tr>
			<tr>
				<td><%=status.get(1)%></td>
				<td><%=status.get(2)%></td>
				<td><%=status.get(3)%></td>
			</tr>
		</table>
	</div>
</body>
</html>