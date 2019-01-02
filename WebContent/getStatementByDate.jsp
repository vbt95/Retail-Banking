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
function validate() 
{
    var accountId,start,end;

    // Get the value of the input field with id="numb"
    accountId = document.f1.accountId.value;
    start= document.f1.start.value;
    end= document.f1.end.value;
    if (isNaN(accountId) || accountId < 99999999 || accountId > 999999999 || accountId=="" ) 
    {
       alert("Please Enter valid Account id");
       return false;
    } 
   else if (start=="") 
   {
       alert("Please Enter start date");
       return false;
    }
   else if(end=="")
   {
       alert("Please enter end date");
       return false;
   } 
  return true;
  }
</script>
</head>

<link href="Style.css" rel="stylesheet">
<body>
	<div id="first">
		<form action="TransactionServlet"  method="post" name="f1">
			Account Id : <input type="text" name="accountId" required><br><br>
			<br>
			Select start date : <input type="date" name="start" required><br><br>
			Select end date : <input type="date" name="end" required>
			<input type="submit" value="submit" onclick="return validate()"/> 
			<input type="hidden" name="action" value="getStatement">
			<input type="hidden" name="type" value="date">
		</form>
	</div>
</body>
</html>