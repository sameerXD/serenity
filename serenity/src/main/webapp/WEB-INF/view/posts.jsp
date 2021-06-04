<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All posts</title>
</head>
<body>
<h1>hello These are all your posts</h1>
<c:forEach items="${posts}" var="post">
<h4 class="card-title"><c:out value="${post.title}"></c:out></h4>
<h4 class="card-title"><c:out value="${post.body}"></c:out></h4>
<h4 class="card-title"><c:out value="${post.time}"></c:out></h4>

  	<img width="100" height="100" src="getPostclip/id/${post.id}/type/{image}">
  	
  		<video width="320" height="240" controls>
  <source src="getPostclip/id/${post.id}/type/{video}" type="video/mp4">
  <source src="getPostclip/id/${post.id}/type/{video}" type="video/ogg">
Your browser does not support the video tag.
</video>

</c:forEach>
</body>
</html>