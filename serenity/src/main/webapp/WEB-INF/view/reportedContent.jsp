<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reported Content</title>
</head>
<body>

<c:forEach items = "${reportedAll }" var = "report">
<c:forEach items = "${ reportedPost}" var = "post">
<br>
<c:if test="${report.post_id == post.id }">
<br>
<br>
report count :${report.count}
<br>

	<a href= "${pageContext.request.contextPath}/userProfile/${post.email}"><c:out value="${post.email}"></c:out></a>
<h4 class="card-title"><c:out value="${post.title}"></c:out></h4>
<h4 class="card-title"><c:out value="${post.body}"></c:out></h4>
<h4 class="card-title"><c:out value="${post.time}"></c:out></h4>

  	<img width="100" height="100" src="${pageContext.request.contextPath}/getPostclip/id/${post.id}/type/{image}">
  	
  		<video width="320" height="240" controls>
  <source src="${pageContext.request.contextPath}/getPostclip/id/${post.id}/type/{video}" type="video/mp4">
  <source src="${pageContext.request.contextPath}/getPostclip/id/${post.id}/type/{video}" type="video/ogg">
Your browser does not support the video tag.
</video>
<br>
<form action="${pageContext.request.contextPath}/teachers/deletePost/${post.id}">
 <button onclick="return confirm('Are you sure you want to delete this post?');">Delete post</button>

</form>

	<br>
</c:if>

</c:forEach>
</c:forEach>
</body>
</html>