<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
<script>
	history.forward();
</script>
<link href="Style.css" rel="stylesheet">
</head>
<body>
	<%@ page trimDirectiveWhitespaces="true"%>
	<%
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			if (session.getAttribute("id") == null) {
				response.sendRedirect("index.jsp");
			} else {
			}
		} catch (Exception ex) {
			out.println(ex);
		}
	%>
	<%
		session.invalidate();
	%>

	<div id="first">
		<form action="index.jsp">
			<input type="submit" value="Go to Home" />
		</form>
	</div>
</body>
</html>