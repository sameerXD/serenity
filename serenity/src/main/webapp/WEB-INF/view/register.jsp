<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>register</title>
</head>
<body>
Welcome to Register Page


<form:form action = "signUp"  method = "post" modelAttribute="user">
     Email:  <c:out value="${email }"></c:out>
     <form:input path="name"  value = "${email}" type = "hidden"/>
    
     <br><br> 
     
     Password:
        <form:input path="password" type="password" placeholder ="PASSWORD"/>  
  
        <br><br> 
        
                Role:   
        Teacher <form:radiobutton path="role" value="ROLE_Teacher"/>  
        Student <form:radiobutton path="role" value="ROLE_Student"/> 
        <br><br> 
        
            
     <form:button type = "submit">Register</form:button>
</form:form>
</body>
</html>