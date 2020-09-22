<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>

	<h1>Demo Spring Security Prevent Brute Force</h1>
	<a href="<c:url value='/login' />">Login</a> <br>
	<a href="<c:url value='/register' />">Register</a> <br>
	<a href="<c:url value='/admin' />">Admin</a> <br>
	<a href="<c:url value='/user' />">User</a>
</body>
</html>