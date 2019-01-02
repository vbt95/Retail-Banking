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
<title>Withdraw amount</title>
<link href="Style.css" rel="stylesheet">
</head>
<body>

	<div id="first">
		<form action="TransactionServlet" method="post" name="f1">
			Enter amount:<input type="text" name="amount">Rs<br> <input
				type="submit" value="submit" onclick="return validate()"><br>
			<input type="hidden" name="action" value="withdraw">
		</form>
	</div>
</body>
<script>
	function validate() {
		var amt = document.f1.amount.value;
		if (amt == "" || isNaN(amt) || amt <= 0) {
			alert("Enter a valid amount");
			return false;
		}
		return true;

	}
</script>
</html>