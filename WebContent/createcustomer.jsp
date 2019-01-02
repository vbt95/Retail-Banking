<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN http://www.w3.org/TR/html4/loose.dtd>
<html>
<head>
<jsp:include page="header.jsp"/> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Customer</title>
<link href="Style.css" rel="stylesheet">
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
<script>
function myFunction() {
    var ssn,custid,age,address,city,state;

    // Get the value of the input field with id="numb"
    ssn = document.f1.SSN.value;
    custname= document.f1.CustomerName.value;
    age= document.f1.age.value;
    add1= document.f1.address1.value;
    city= document.f1.city.value;
    state= document.f1.state.value;
    if (isNaN(ssn) || ssn < 99999999 || ssn > 999999999) {
       alert("Please Enter valid SSN ID");
       return false;
    } 
   else if (!isNaN(custname) || custname=="") {
       alert("Please Enter Customer Name");
       return false;
    }
   else if(isNaN(age) || age <5 || age > 100)
   {
       alert("Please enter valid Age");
       return false;
   } 
   else if (add1=="") {
        alert("Please Enter address");
        return false;
    }
    else if (!isNaN(city) || city=="") {
        alert("Please Enter City");
        return false;
    }
    else if (!isNaN(state) || state=="") {
        alert("Please Enter state");
        return false;
    }
 
    }
</script>
</head>
<body >

<div id="first">
<center><h1>Add Customer</h1></center>
<form action="CustomerServlet" method="post" name="f1">

					SSN :<br>
					<input type="text" name="SSN" required><br>
				
					Customer Name:<br>
					<input type="text" name="CustomerName" required><br>
				
					Age:<br>
					<input type="text" name="age" required><br>
				
					Address Line 1:<br>
					<input type="text" name="address1" required ><br>
				
					Address Line 2:<br>
					<input type="text" name="address2" ><br>
				
					City :<br>
					<input type="text" name="city" required><br>
			
					State:<br>
					<input type="text" name="state" required><br>
				
        <input type="hidden" name="action" value="add">
     <input type="submit" value="Submit" onclick=" return (myFunction())">
</form>
</div>
</body>
</html>
