<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>



<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update your post</title>
</head>
<body>
<a href = "${pageContext.request.contextPath}/">home</a>
	<form:form action = "updatePost"  method = "post" modelAttribute="posts" enctype="multipart/form-data">
	<input type="hidden" name="id" value = "${post.id}" />
	<form:input path="email" value = "${post.email}" type = "hidden"/>
	Title : <form:input path="title" value = "${post.title}" />
	Body: <form:input path="body" value = "${post.body}" />
	Image: <img width="100" height="100" src="${pageContext.request.contextPath}/yourPosts/getPostclip/id/${post.id}/type/{image}">
	<input type="file" name="image1" />
	<br>Video:<br><video width="320" height="240" controls>
  <source src="${pageContext.request.contextPath}/yourPosts/getPostclip/id/${post.id}/type/{video}" type="video/mp4">
  <source src="${pageContext.request.contextPath}/yourPosts/getPostclip/id/${post.id}/type/{video}" type="video/ogg">
Your browser does not support the video tag.
</video>
	<input type="file" name="video1" />
	<form:button>post</form:button>
	</form:form>
</body>
</html>