<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user</title>
</head>
<body>

	<h1>User Page</h1>
	<h3>Welcome User:${pageContext.request.userPrincipal.name }</h3>
	<a href="<c:url value='/index' />">Index</a>
	<br>
	<form action="<c:url value='/j_spring_security_logout' />" method="get">
		<button type="submit">Logout</button>
	</form>

</body>
</html>