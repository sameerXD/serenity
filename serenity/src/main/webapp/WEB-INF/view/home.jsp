<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>



<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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

$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#allPosts .post").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
</script>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h1>SERENITY </h1>
	<br>
	<input id="myInput" type="text" placeholder="Search..">
<br><br>
	<a href = "${pageContext.request.contextPath}/profile">update Profile</a>
		<!-- users posts -->
	<a href = "${pageContext.request.contextPath}/yourPosts/">your posts</a>
	<!-- Top Scorers -->
	<a href = "${pageContext.request.contextPath}/topScorers/">Top Scorers</a>
	<hr>
	<br>
	<img width="100" height="100" src="getStudentPhoto/<c:out value='${student.email}'/>">

	<c:out value="${ student}"></c:out>

	<p>
	Welcome to the luv2code company home page!
	</p>
	
	<hr>
	<!--post a POST -->
	
	<form:form action = "post"  method = "post" modelAttribute="posts" enctype="multipart/form-data">
	<form:input path="email" value = "${student.email}" type = "hidden"/>
	<form:input path="title" placeholder = "title"/>
	<form:input path="body" placeholder = "body"/>
	<input type="file" name="image1" />
	<input type="file" name="video1" />
	<form:button>post</form:button>
	</form:form>
	
	<hr>
	<!-- display user name and role -->
	
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</p>
	
	<!-- all users posts -->
	

	
	<h1>these are all the posts</h1>

	<c:forEach items="${posts1}" var="post" >
		<div id="allPosts">
		<div class="post">
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
<a href = "${pageContext.request.contextPath}/reportPost/${post.id}">report post</a>
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
</div>
</div>
<br>
<br>
</c:forEach>
	
	<security:authorize access="hasRole('Teacher')">
	
		<!-- Add a link to point to /leaders ... this is for the managers -->
		
		<p>
			<a href="${pageContext.request.contextPath}/teachers">Teacher Control Pannel</a>
			(Only for Manager peeps)
		</p>

	</security:authorize>	
	
	
	<security:authorize access="hasRole('ADMIN')">  

		<!-- Add a link to point to /systems ... this is for the admins -->
		
		<p>
			<a href="${pageContext.request.contextPath}/systems">Admin Control Pannel</a>
			(Only for Admin peeps)
			
		</p>
	
	</security:authorize>
	

	

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









