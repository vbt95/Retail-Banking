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
    var CustomerName,age,city,address1,state,status,message;
    // Get the value of the input field with id="numb"   
    CustomerName= document.f1.CustomerName.value;
    age= document.f1.age.value;
    address1= document.f1.address1.value;
    city= document.f1.city.value;
    state= document.f1.state.value;
    status=document.f1.status.value;
    message=document.f1.message.value;
   if (!isNaN(CustomerName) || CustomerName=="" || CustomerName=="null") {
       alert("Please Enter Customer Name");
       return false;
    }
   else if(isNaN(age) || age <5 || age > 100 || age=="null")
   {
       alert("Please enter valid Age");
       return false;
   } 
   else if (address1=="" || address1=="null") {
        alert("Please Enter address");
        return false;
    }
    else if (!isNaN(city) || city=="" || city=="null") {
        alert("Please Enter City");
        return false;
    }
    else if (!isNaN(state) || state=="" || state=="null") {
        alert("Please Enter state");
        return false;
    }
    else if (!isNaN(status) || status=="" || status=="null") {
        alert("Please Enter status");
        return false;
    }
    else if (!isNaN(message) || message=="" || message=="null") {
        alert("Please Enter message");
        return false;
    }
   
 return true;
    }
</script>
</head>
<body>

	<div id="first" style="text-align: center">
		<h1>Update Customer</h1>
		<form action="CustomerServlet" method="post" name="f1">

			Customer ID :<br> <input type="text"
				value="<%=request.getAttribute("id")%>" disabled="disabled" /> <br>
			SSN :<br> <input type="text"
				value="<%=request.getAttribute("ssn")%>" disabled="disabled" /> <br>
			Customer Name:<br> <input type="text" name="CustomerName"
				value="<%=request.getAttribute("name")%>" required /> <br>

			Age:<br> <input type="text" name="age"
				value="<%=request.getAttribute("age")%>" required /> <br>
			Address Line 1:<br> <input type="text" name="address1"
				value="<%=request.getAttribute("add1")%>" required /> <br>
			Address Line 2:<br> <input type="text" name="address2"
				value="<%=request.getAttribute("add2")%>" /> <br> City :<br>
			<input type="text" name="city"
				value="<%=request.getAttribute("city")%>" required /> <br>
			State :<br> <input type="text" name="state"
				value="<%=request.getAttribute("state")%>" required /> <br>
			Status :<br> <input type="text" name="status"
				value="<%=request.getAttribute("status")%>" required /> <br>
			Message :<br> <input type="text" name="message"
				value="<%=request.getAttribute("message")%>" required /> <br>
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="customerID"
				value="<%=request.getAttribute("id")%>"> <input
				type="hidden" name="SSN" value="<%=request.getAttribute("ssn")%>">
			<input type="submit" value="Update" onclick="return validate()" />
		</form>
	</div>
</body>
</html>