<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.like1 {
  display: none;
}
.comment1 {
  display: none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
var x = false;
$(document).ready(function(){
  $(".like").hover(function(){
    $(".like1").css("display", "block");
    
    }, function(){
    $(".like1").css("display", "none");
  });
  
  $(".comment").click(function(){
	  if (!x) {
		  $(".comment1").css("display", "block");x = true;
		} else if(x){
			$(".comment1").css("display", "none");x = false;
		}
	    
	    });
});

function goBack()
{
window.history.back()
}
</script>
<meta charset="ISO-8859-1">
<title>user</title>
</head>
<body>
<a onclick="goBack()">back to all users</a><br><br>

name : ${userProfile.userName }<br>
email :  ${userProfile.email }<br>
course :  ${userProfile.course }<br>
image: <img width="100" height="100" src="${pageContext.request.contextPath}/getStudentPhoto/<c:out value='${userProfile.email}'/>">

<h1>user's posts</h1>
<c:forEach items ="${postList }" var  = "post">
title: ${post.title } <br>
body:: ${post.body } <br>
image: <img width="100" height="100" src="${pageContext.request.contextPath}/getPostclip/id/${post.id}/type/{image}"><br>
video:<video width="320" height="240" controls>
  <source src="${pageContext.request.contextPath}/getPostclip/id/${post.id}/type/{video}" type="video/mp4">
  <source src="${pageContext.request.contextPath}/getPostclip/id/${post.id}/type/{video}" type="video/ogg">
Your browser does not support the video tag.
</video>
<br>
time: ${post.time}
<br>
	<br>
<button class = "like">liked by:-</button>	
<a  href= "${pageContext.request.contextPath}/like/${post.id}">Like</a>
<c:out value="${likeCount.get(post.id) }"></c:out>

<div class = "like1">
<h2>liked by---------></h2>
<c:forEach items="${likeList }" var = "like">
<c:if test="${(like.post_id) == post.id }">

<h3 >${like.email}</h3>

</c:if>
</c:forEach>
</div>

<br>
	<br>
<button class = "comment">comment</button>
<div class = "comment1">
<h2>comment section</h2>
<form action="${pageContext.request.contextPath}/comment/${post.id}">
<input type = "text" name = "comment" placeholder = "comment"/>
<button>post</button>
</form>
<c:forEach items = "${commentList }" var = "comment">
<c:if test="${comment.post_id == post.id }">
<h2>${comment.email } posted:</h2>
<p>${comment.comment}</p>
<h5>${comment.time }</h5>
<c:if test="${comment.email == student.email}">
<a href = "${pageContext.request.contextPath}/deleteComment/${comment.id}">delete</a>
</c:if>
</c:if>
</c:forEach>
</div>
<hr>
</c:forEach>
</body>
</html>