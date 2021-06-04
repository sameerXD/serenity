<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>welcome lets build your profile <c:out value="${userName1 }"></c:out></h1>

<form:form action = "buildProfile"  method = "post" modelAttribute="stud" enctype="multipart/form-data" >
<form:input path="userName" placeholder = "Name" type=" hidden" /><br>
<form:input path="email"  value = "${userName1}"   type = "hidden" /><br>
<form:input path="course"   placeholder="COURSE" /><br>
<form:input path="specialisation"  placeholder="specialisation" /><br>
<form:input path="gender"  placeholder="GENDER"/><br>
<input type="file" name="file" />
<form:button type= "submit">post</form:button>
</form:form>


</body>
</html>