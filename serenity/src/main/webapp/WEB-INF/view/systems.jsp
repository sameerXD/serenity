<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>

<html>

<head>
	<title>luv2code SYSTEMS Home Page</title>
</head>

<body>

<h2>luv2code SYSTEMS Home Page</h2>

<hr>

<p>
<a href = "${pageContext.request.contextPath}/systems/allMembers">all members</a><br>
<a href="${pageContext.request.contextPath}/">Back to Home Page</a>
<br>
<h1>users want teachers authority</h1>
<c:forEach items = "${toBeTeacherList}" var = "teacher">
email : "${teacher.userName }"<br>

image: <img width="100" height="100" src="${pageContext.request.contextPath}/admin/${teacher.id}">
<a href = "${pageContext.request.contextPath}/teacherORNot/id/${teacher.id}/user/${teacher.userName }/permission/{approve}">approve</a>
<a href = "${pageContext.request.contextPath}/teacherORNot/id/${teacher.id}/user/${teacher.userName }/permission/{deny}">deny</a>
</c:forEach>
</p>

<hr>



</body>

</html>









