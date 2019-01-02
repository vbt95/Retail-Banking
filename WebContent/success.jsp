<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
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
</head>
<body>
	<div align="center">
		<h2>
			<%=request.getAttribute("message")%>
		</h2>
		<img src="images/success.gif" />
	</div>
</body>
</html>