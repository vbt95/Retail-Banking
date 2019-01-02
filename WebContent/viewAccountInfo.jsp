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
<title>View Account Info</title>
<link href="Style.css" rel="stylesheet">
</head>
<body>
	<br>
	<div id="first">
		<table>
			<tr>
				<th>Customer ID</th>
				<th>Account ID</th>
				<th>Account Type</th>
				<th>Balance</th>
			</tr>
			<tr>
				<td><%=request.getAttribute("custid")%></td>
				<td><%=request.getAttribute("accid")%></td>
				<td><%=request.getAttribute("atype")%></td>
				<td><%=request.getAttribute("bal")%></td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<div id="first">
		<form action="deposit.jsp">
			<button type="submit" name="deposit">Deposit</button>
		</form>
		<br>
		<form action="withdraw.jsp">
			<button type="submit" name="withdraw">Withdraw</button>
		</form>
	</div>

</body>
</html>