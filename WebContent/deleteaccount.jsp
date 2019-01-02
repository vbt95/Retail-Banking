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
<title>View Account Info</title>
</head>
<link href="Style.css" rel="stylesheet">
<body>
	<br>
	<div align="center">
		<table border=1>
			<tr>
				<th>Account ID</th>
				<th>Account Type</th>
				<th>Balance</th>
				<th>Delete account</th>
			</tr>

			<%@page import="java.util.ArrayList,com.Bean.Account" %>
			<% ArrayList<Account> idlist=(ArrayList<Account>)(request.getAttribute("idlist")); 
			for(Account cur:idlist){
		
			%>
			<tr> 
				<td> <%=cur.getAccid()%></td>
				<td> <%=cur.getAcctype()%></td>
				<td> <%=cur.getBalance()%> </td>
				<td> <form action="DeleteServlet" method="post">
	 					<input type="submit" name="Delete" value="Delete">
	 					<input type="hidden" name="accountId" value="<%=cur.getAccid() %>">
	 				</form>
	 			</td>
  			</tr>
			<%} %>
		</table>
  	</div>
  
  	<br>

</body>
</html>