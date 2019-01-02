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
<title>Get statement</title>
</head>
<link href="Style.css" rel="stylesheet">
<body>
	<div id="first">
		<form action="getMiniStatement.jsp">
			<button type="submit" name="getMiniStatement" >Get mini statement</button>
		</form>
		</div><br><br>
	<div id="first">
		<form action="getStatementByDate.jsp">
			<button type="submit" name="getStatementByDate" >Get statement between dates</button>
		</form>
	</div><br><br>
</body>
</html>