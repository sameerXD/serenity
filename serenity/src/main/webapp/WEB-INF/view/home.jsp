<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>



<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page </h2>
	<hr>
	<c:if test="getStudentPhoto/<c:out value='${student.email}'/>"></c:if>
	<img width="100" height="100" src="getStudentPhoto/<c:out value='${student.email}'/>">
	<video width="320" height="240" controls>
  <source src="getStudentPhoto/<c:out value='${student.email}'/>" type="video/mp4">
  <source src="getStudentPhoto/<c:out value='${student.email}'/>" type="video/ogg">
Your browser does not support the video tag.
</video>
	<c:out value="${ student}"></c:out>
	
	
	<!-- users posts -->
	<a href = "${pageContext.request.contextPath}/yourPosts/<c:out value='${student.email}'/>">your posts</a>
	
	
	
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
	<c:forEach items="${posts1}" var="post">
	<h4 class="card-title"><c:out value="${post.email}"></c:out></h4>
<h4 class="card-title"><c:out value="${post.title}"></c:out></h4>
<h4 class="card-title"><c:out value="${post.body}"></c:out></h4>
<h4 class="card-title"><c:out value="${post.time}"></c:out></h4>

  	<img width="100" height="100" src="yourPosts/getPostclip/id/${post.id}/type/{image}">
  	
  		<video width="320" height="240" controls>
  <source src="yourPosts/getPostclip/id/${post.id}/type/{video}" type="video/mp4">
  <source src="yourPosts/getPostclip/id/${post.id}/type/{video}" type="video/ogg">
Your browser does not support the video tag.
</video>

</c:forEach>
	
	<security:authorize access="hasRole('Teacher')">
	
		<!-- Add a link to point to /leaders ... this is for the managers -->
		
		<p>
			<a href="${pageContext.request.contextPath}/teachers">Leadership Meeting</a>
			(Only for Manager peeps)
		</p>

	</security:authorize>	
	
	
	<security:authorize access="hasRole('ADMIN')">  

		<!-- Add a link to point to /systems ... this is for the admins -->
		
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
			(Only for Admin peeps)
		</p>
	
	</security:authorize>
	
	<hr>
	

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









