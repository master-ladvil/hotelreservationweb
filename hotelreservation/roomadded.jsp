<html>
<body>
<%        
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);
    if(session.getAttribute("username")==null){

	response.sendRedirect("index.jsp");
	}
%>
	<form action = "Logout">
	<h3>room added</h3><br>
	<input type = "submit" value = "logout">
	</form>
</body>
</html>