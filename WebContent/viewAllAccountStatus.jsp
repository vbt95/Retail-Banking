<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp" />

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Account Status</title>

<link href="Style.css" rel="stylesheet">

</head>
<body>

	<br>
	<br>
	<%@page
		import="com.Bean.AccountStatus,java.util.ArrayList,java.sql.Date,java.text.DateFormat,java.text.SimpleDateFormat"%>
	<% ArrayList<AccountStatus> list=(ArrayList<AccountStatus>)request.getAttribute("list");%>


	<table>
		<caption>
			<h1>All account status</h1>
		</caption>
		<tr>
			<th>Customer ID</th>
			<th>Account ID</th>
			<th>Account Type</th>
			<th>Status</th>
			<th>Message</th>
			<th>Last updated</th>
			<th>Refresh Buttons</th>
		</tr>
		<%
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
for(AccountStatus acct:list){
%>
		<tbody class="div<%=acct.getAccid() %>">
			<tr>
				<td><%= acct.getCustid() %>
				<td><%= acct.getAccid() %>
				<td><%=acct.getAcctype()%></td>
				<td><%=acct.getStatus() %></td>
				<td><%=acct.getMessage() %></td>
				<td><%=acct.getLastUpdate()%></td>
				<td><button type="button"
						onclick="$.get('RefreshAccount?acctId=<%=acct.getAccid()%>', function(data) {
	  $('.div<%=acct.getAccid()%>').html(data);
});">Refresh</button>
			</tr>
			</div>
			<%}%>
		
	</table>
</body>
<script>
	function refresh() {
		location.reload(true);
	}
</script>
</html>