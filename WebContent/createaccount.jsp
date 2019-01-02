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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="Style.css" rel="stylesheet">
<script>
function validate() {
    var custid,acctype,depamt;

    // Get the value of the input field with id="numb"
    custid = document.f1.custid.value;
    acctype= document.f1.acctype.value;
    depamt= document.f1.depamt.value;
    
    if (isNaN(custid) || custid < 99999999 || custid > 999999999 || custid=="" ) 
    {
       alert("Please Enter valid id");
       return false;
    } 
   else if (!isNaN(acctype) || acctype=="") 
   {
       alert("Please Enter account type");
       return false;
    }
   else if(isNaN(depamt) || depamt <0 || depamt=="")
   {
       alert("Please enter amount");
       return false;
   } 
  	return true;
}
</script>
</head>
<body>
	<div id="first">
	<form action="AccountServlet" method="post" name="f1">
  		<div class="container">
    		<h1>Account Details</h1>
    		<p>Please fill in this form to create an account.</p>
    		<hr>
			<label for="Custid"><b>Customer ID:</b><br><br></label>
    		<input type="text" placeholder="Enter Customer ID" name="custid" required>
    		<hr>
			Account Type:<br><br>
			<select name="acctype">
				<option value="Savings">Savings Account</option>
				<option value="Current">Current Account</option>
			</select><br><br>
			<label for="depamt">Deposit Amount:<br><br></label>
    		<input type="text" placeholder="Enter Deposit Amount" name="depamt" required>
    		<hr>
    		<p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
			
			<button type="submit" class="registerbtn" onclick="return (validate())">Add Account</button>
  		</div>
 		<input type="hidden" name="action" value="add">
 	</form>
	</div>
</body>
</html>
