<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>

	<h1>Register</h1>
	<a href="<c:url value='/index' />">Index</a>
	
	<form:form action="${pageContext.request.contextPath }/register" method="Post" modelAttribute="user">
		<table>
			<tr>
				<td>UserName: </td>
				<td> <form:input path="username"/> </td>
			</tr>
			<tr>
				<td>Password: </td>
				<td> <form:input path="password" type="password"/> </td>
			</tr>
			<tr>
				<td colspan="2"> <button type="submit">Register</button>
			</tr>
		</table>
	</form:form>
	
</body>
</html>