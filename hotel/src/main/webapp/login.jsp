<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/loginstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form method='post' action="LoginCheck">
	<h1><center>Login</center></h1>
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="uname"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="login"></td>
			</tr>

		</table>

	</form>
</body>
</html>