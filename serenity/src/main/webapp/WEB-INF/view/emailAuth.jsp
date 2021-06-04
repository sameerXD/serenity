<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Email</title>
</head>
<body>
<h1><c:out value="${invalid }"></c:out></h1>
<form action="${pageContext.request.contextPath}/emailAuthanticate">
<h1> we require your email to authenticate you</h1>
Email:
<input type = "email" name = "email" placeholder = "email">
<button type = "submit">Finish</button>
</form>
</body>
</html>