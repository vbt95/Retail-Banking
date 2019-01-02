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
<title>View Statement</title>
<script>
function validate() {
    var accountId,days;
    // Get the value of the input field with id="numb"
    accountId = document.f1.accountId.value;
    days= document.f1.days.value;
   
    
    if (isNaN(accountId) || accountId < 99999999 || accountId > 999999999 || accountId=="" ) 
    {
       alert("Please Enter valid Account id");
       return false;
    } 
   else if (isNaN(days) || days=="") 
   {
       alert("Please Enter account type");
       return false;
    }
 
  return true;
    }
</script>
<link href="Style.css" rel="stylesheet">
</head>
<body>
	<div id="first">
		<form action="TransactionServlet"  method="post" name="f1">
			Account Id : <input type="text" name="accountId" required>
			Select Transactions : 
			<select name="days" required>
	  			<option value="1">1</option>
  				<option value="2">2</option>
  				<option value="3">3</option>
  				<option value="4">4</option>
  				<option value="5">5</option>
  				<option value="6">6</option>
  				<option value="7">7</option>
  				<option value="8">8</option>
  				<option value="9">9</option>
  				<option value="10">10</option>
			</select>
			<input type="submit" value="submit" onclick="return (validate())"> 
			<input type="hidden" name="action" value="getStatement" >
			<input type="hidden" name="type" value="mini">
		</form>
	</div>
</body>
</html>