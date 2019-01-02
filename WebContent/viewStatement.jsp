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
<title>Statement</title>
<link href="Style.css" rel="stylesheet">
</head>
<body>

	<%@page import="com.Bean.Transaction,java.util.ArrayList"%>
	<%
		ArrayList<Transaction> list = (ArrayList<Transaction>) request
				.getAttribute("list");
	%>
	<div id="first">
		<table>

			<tr>
				<th>Date</th>
				<th>Transaction Description</th>
				<th>Balance</th>
			</tr>

			<%
				for (Transaction t : list) {
			%>
			<tr>
				<td><%=t.getTransactionDate()%></td>
				<td><%=t.getDescription()%></td>
				<td><%=t.getBalance()%></td>
			</tr>
			<%
				}
			%>

		</table>

	</div>
</body>
</html>