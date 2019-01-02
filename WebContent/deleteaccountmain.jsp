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
<title>View Account Information</title>
<script>
function validate() {
    var type,id;

    // Get the value of the input field with id="numb"
    type = document.f1.type.value;
    id= document.f1.id.value;
  
    
    if (!isNaN(type) ||  type=="" ) 
    {
       alert("Please Enter account type");
       return false;
    } 
   else if (isNaN(id) || id=="" || id < 99999999 || id > 999999999) 
   {
       alert("Please Enter valid ID");
       return false;
    }
  
  
  return true;
    }
</script>


</head>
<link href="Style.css" rel="stylesheet">
<body>
	<%@page import="java.util.ArrayList" %>
	<div id="first">
		<form action="AccountServlet" method="post" name="f1"> 
	      	<h3> Please select:</h3> 	
      		<select name="type">
	      		<option value="SSN ID" >SSN ID</option>
      			<option value="Customer ID">Customer ID</option>
      		</select>
      		<br><br>
       		Enter ID:<br><br>
      		<input type="text" name="id"  value=0>	      
      		<input type="hidden" name="action" value="viewaccounttable">
      		<br><br>
      		<input type="submit" value="Submit"  onclick="return validate()"/>
		</form>
	</div>
</body>
</html>