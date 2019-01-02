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
<title>Transfer</title>
<link href="Style.css" rel="stylesheet">
</head>
<body>
	<br>
	<br>
	<div id="first">

		<form action="TransactionServlet" method="post" name="transferForm">
			Transfer amount: <input type="text" name="amount"><br>
			<br> Source account id: <input type="text" name="source"><br>
			<br> Target account id: <input type="text" name="target"><br>
			<br> <input type="submit" value="submit"
				onclick="return validate()"><br> <input type="hidden"
				name="action" value="transfer">
		</form>
	</div>
	<script>
function validate(){
	var amount=document.transferForm.amount.value;
	if(amount=="" || isNaN(amount) ||amount<0 ){
		alert("Please enter valid amount");
		return false;
	}
	var sourceId=document.transferForm.source.value;
	if(sourceId=="" || isNaN(sourceId) || sourceId<0 || sourceId < 99999999 || sourceId > 999999999){
		alert("Please enter valid source account id");
		return false;
	}
	var targetId=document.transferForm.target.value;
	if(targetId=="" || isNaN(targetId) || targetId<0 || targetId < 99999999 || targetId > 999999999){
		alert("Please enter valid target account id");
		return false;
	}
	return confirm("Are you sure");
}
</script>
</body>
</html>