<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
			ul {
    			list-style-type: none;
    			margin: 0;
    			padding: 0;
    			overflow: hidden;
    			background-color: #333;
				position: fixed;
    			top: 0;
    			width: 100%;
			}
	
			li {
	    		float: left;
			}
			
			li a {
	    		display: block;
    			color: white;
    			text-align: center;
    			padding: 14px 16px;
    			text-decoration: none;
			}
			
			li a:hover:not(.active) {
	    		background-color: #111;
			}
			
			.active {
	    		background-color: green;
			}
		</style>
	</head>

	<body>
	
		<ul>
  			<li><img src="alpha.png" alt="logo"></li>
  			<% if(session.getAttribute("type")!=null){
  					String type=(String)session.getAttribute("type");
  					if(type.equals("cae")){%>
  						<li><a class="active" href="caehome.jsp">Home</a></li>
  					<%}
  					else if(type.equals("cashier_teller")){%>
  						<li><a class="active" href="cashier_tellerhome.jsp">Home</a></li>
  			<%
  					}
  				}	
  			%>
  			
  			<li><a href="#about">About</a></li>
  			
  			<li style="float:right"><a href="logout.jsp">Logout</a></li>
  			<li style="float:right; color:white; padding: 14px 16px; ">Welcome <%=session.getAttribute("id")%></li>
		</ul>
	
	<br><br><br>
	</body>
</html>
