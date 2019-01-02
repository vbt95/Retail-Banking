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
<title>Delete Customer</title>
</head>
<link href="Style.css" rel="stylesheet">
<body>
	<div id="first">
	<form action="CustomerServlet" method="post" name="f1">
		Enter Customer Id:<br><br>
		<input type="text" name="CustId" placeholder="Enter Customer ID" required>
		<br><br>
		<input type="hidden" name="action" value="delete">
		<input type="submit" value="Delete"  onclick="return validate()">
	</form>
	</div>
	<script type="text/javascript">
	
	
	function validate()
	{
		 var CustId;

		    // Get the value of the input field with id="numb"
		    CustId = document.f1.CustId.value;
		    
		    
		 if (isNaN(CustId) || CustId=="" || CustId < 99999999 || CustId > 999999999) 
		   {
		       alert("Please Enter Customer ID");
		       return false;
		    }
		  
		 return confirmComplete();
		  return true;
		    
	}
	function confirmComplete() {
		
		var answer=confirm("Are you sure you want to continue ?");
		if (answer==true)
		  {
		    return true;
		  }
		else
		  {
		    return false;
		  }
	}
	
	</script>
</body>
</html>