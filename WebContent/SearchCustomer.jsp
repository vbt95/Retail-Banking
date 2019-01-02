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
<title>Update Customer</title>
<link href="Style.css" rel="stylesheet">

<script>
function validate() {
    var value,searchval;

    // Get the value of the input field with id="numb"
    searchval = document.f1.searchval.value;
    value= document.f1.value.value;
    if (isNaN(searchval) || searchval < 99999999 || searchval > 999999999 || searchval=="" ) 
    {
       alert("Please Enter valid id");
       return false;
    } 
   else if (!isNaN(value) || value=="") 
   {
       alert("Please Enter account type");
       return false;
    }
  
  return true;
    }
</script>
</head>
<body>
	<div id="first" style="text-align: center">
		<h1>Search Customer</h1>
		<form action="CustomerServlet" method="post" name="f1">
			
					Search By :<br><br>
					<select name="value">
							<option value="customerID">Customer ID</option>
							<option value="SSN ID">SSN ID</option>
					</select>
					<br/><br><br>
					Enter Value :<br>
					<input type="text" placeholder="Enter value"
						name="searchval" required>
					<br><br>
			<input type="hidden" name="action" value="search"> <input
				type="submit" value="Search" onclick="return (validate())" />
		</form>
	</div>
</body>
</html>