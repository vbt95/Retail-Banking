<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<style>
			body {font-family: Arial, Helvetica, sans-serif;}
			form {
				margin: auto;
				border: 3px solid #1f9e97 ; 
				width: 50%;
				align: center;
			}
			input[type=text], input[type=password] {
				width: 100%;
				padding: 12px 20px;
				margin: 8px 0;
				display: inline-block;
				border: 1px solid #ccc ;
				box-sizing: border-box;
			}
			button {
				background-color: #4CAF50 ;
				color: white;
				padding: 14px 20px;
				margin: 8px 0;
				border: none;
				cursor: pointer;
				width: 25%;
			}
			button:hover {
				opacity: 0.8;
			}

			.cancelbtn {
				width: auto;
				padding: 10px 18px;
				background-color: #f44336 ;
			}
		
			.imgcontainer {
				text-align: center;
				margin: 24px 0 12px 0;
			}
		
			img.avatar {
				width: 20%;
				border-radius: 20%;
			}

			.container {
 				margin: auto;
  				width: 50%;
				padding: 16px;
			}

			span.psw {
				float: right;
				padding-top: 16px;
			}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
span.psw {
display: block;
float: none;
}
.cancelbtn {
width: 100%;
}
}
</style>



</head>
<body background="images/bg13.jpg">
	<div align="center">
		<b><img src="images/alphanew.png" alt="BANK LOGO"></b>
	</div>
	<h2 style="text-align:center;">ALPHA BANK </h2>
	<form name="form1" action="LoginServlet" onsubmit="validate()" method="post" >
		<div class="imgcontainer">
			<img src="images/AVATAR.png" alt="Avatar">
		</div>
		<div class="container">
			<label for="uname"><b>Username</b></label>
			<input type="text" placeholder="Enter Username" name="uname" required>
			<label for="psw"><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="pwd" required>
			<br>
        	<button type="submit" name="submit" onclick="CheckPassword(document.form1.password); CheckUserId(document.form1.username);" >Login</button>
        	<button type="reset" >Reset </button>
    	</div>
		<% 
        if(request.getAttribute("status")!=null){
        	int status=(Integer)request.getAttribute("status");
			if(status==0){
				out.println("<p style=\"color:red; text-align:center;\">	Wrong credentials<p>");
			}
			else if(status==-1){
				out.println("<p style=\"color:red; text-align:center;\">	Failed due to some internal error<p>");
			}
        }
		%>	
	</form>
	<center>
	<div style=" bottom:20px; left:600px">
		<h6>&copy Copyright to HJA16 2018</h6>
	</div>
	</center>
</body>
</html>

