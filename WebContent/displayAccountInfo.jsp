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
<title>Display Account Information</title>
<script type="text/javascript">
    function Validate() 
    {
        var select = document.getElementById("select").value;
        var id=document.getElementById("id").value;
        
        if (select== "")
        {
     
            alert("Please select a type!");
           return false;
        }
        else if (id<=0 ||id == "" || id < 99999999 || id > 999999999) 
       {
   
           alert("Please Enter valid  id");
           return false;
        }
      return true;
    }
    
    function ValidateAccountId() 
     {
           var accountid=document.getElementById("aid").value;
           if (accountid == "" ||accountid < 99999999 || accountid > 999999999)
               {
         
                     alert("Please enter valid account id!");
                     return false;
                }
          return true;
     }
    
    function ValidateSelect()
    { 
        var select2 = document.getElementById("select2").value;
  		if(select2=="0")
    		{
    		
    		alert("Please enter valid Account ID");
            return false;
       }
  		
       return true;
     }
</script>
<link href="Style.css" rel="stylesheet">
</head>
<body>
	<%@page import="java.util.ArrayList" %>
	<div id="first">
	<form action="AccountServlet" method="post"  >
    	<h3> Please select:</h3> 	
      	<select name="type" id="select">
      		<option value="">--Select--</option>
      		<option value="SSN ID" >SSN ID</option>
      		<option value="Customer ID">Customer ID</option>
      	</select>
      	<br><br>
       	Enter ID:
       	<br>
      	<input type="text" name="id"  id="id" value=0 >
      	<input type="hidden" name="action" value="view">
       	<input type="submit" value="Submit" onclick="return (Validate())"/>
	</form>
	
	<form action="AccountServlet" method="post" >
		<br>
		<% 
 		ArrayList<Integer> allid=new ArrayList<Integer>();
 		allid=(ArrayList<Integer>)request.getAttribute("idlist");
 		if(allid!=null){
		%>
 		<select name="accountid" id="selectid">    
		<% 
		for(Integer x: allid)
		{ %>	 
     		<option value="<%= x %>"><%=x %>
     		</option>
		<%}
		%>
  		</select>
  		<%
		}
		else
  		{
		%>
		<select name="accountid" id="select2">
			<option value="0" >Select Account ID</option>
			
		</select>
		<br>
		<%
  		}
  		%>
    	<input type="hidden" name="action" value="acid">
    	<br><br>
		<input type="submit" value="Submit" onclick="return (ValidateSelect())"/>
	</form>	
	<hr>
	<form action="AccountServlet" method="post">
		<h3 align="center">OR</h3>
     	Account ID:
     	<br>
    	<input type="text" name="aid" id="aid"> 
		<input type="hidden" name="action" value="accid">
		<br><br>
		<input type="submit" value="Submit" onclick=" return (ValidateAccountId())"/>
	</form>
	</div>
</body>
</html>