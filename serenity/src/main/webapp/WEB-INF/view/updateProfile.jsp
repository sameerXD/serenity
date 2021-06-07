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
<title>updateProfile</title>
</head>
<body>
<a href = "${pageContext.request.contextPath}/">home</a>
<h1>Lets update your profile</h1>
<img width="100" height="100" src="getStudentPhoto/<c:out value='${student.email}'/>">

<c:out value="${ student}"></c:out>
<c:if test="${(duplicate) != null}">
<c:out value="${duplicate }"></c:out>
</c:if>
<form:form action = "updateProfile"  method = "post" modelAttribute="stud" enctype="multipart/form-data" >
<form:input path="userName" value = "${student.userName }" placeholder = "Name" type=" hidden" /><br>
<form:input path="email"  value = "${userName1}"   type = "hidden" /><br>
<form:input path="course"  value = "${student.course }" placeholder="COURSE" /><br>
<form:input path="specialisation"  value = "${student.specialisation }" placeholder="specialisation" /><br>
<form:input path="gender" value = "${student.gender }" placeholder="GENDER"/><br>
<input type="file" name="file" />
<form:button type= "submit">post</form:button>
</form:form>


</body>
</html>