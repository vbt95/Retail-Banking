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
<title>View All Customer Status</title>
<link href="Style.css" rel="stylesheet">

</head>
<body>

	<br>
	<br>
	<%@page
		import="com.Bean.CustomerStatus,java.util.ArrayList,java.sql.Date,java.text.DateFormat,java.text.SimpleDateFormat"%>
	<% ArrayList<CustomerStatus> list=(ArrayList<CustomerStatus>)request.getAttribute("list");%>


	<table>
		<caption>
			<h1>All customer status</h1>
		</caption>
		<tr>
			<th>Customer ID</th>
			<th>SSN ID</th>
			<th>Status</th>
			<th>Message</th>
			<th>Last updated</th>
			<th>Refresh Buttons</th>
		</tr>

		<%
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
for(CustomerStatus cust:list){
%>
		<tbody class="div<%=cust.getCustomerID()%>">
			<tr>
				<td><%= cust.getCustomerID() %>
				<td><%=cust.getSSN()%></td>
				<td><%=cust.getStatus() %></td>
				<td><%=cust.getMessage() %></td>
				<td><%=cust.getLastUpdate()%></td>
				<td><button type="button"
						onclick="$.get('RefreshCustomer?custId=<%=cust.getCustomerID()%>', function(data) {
	  $('.div<%=cust.getCustomerID()%>').html(data);
});">Refresh</button>
			</tr>
		</tbody>
		<%}%>

	</table>

</body>
<script>
	function refresh() {
		location.reload(true);
	}
</script>
</html>