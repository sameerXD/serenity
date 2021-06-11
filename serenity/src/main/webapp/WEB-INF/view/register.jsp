<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.teacher1 {
  display: none;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
var x = false;
$(document).ready(function(){
	 $(".teacher").click(function(){
		  if (!x) {
			  $(".teacher1").css("display", "block");x = true;
			} else if(x){
				$(".teacher1").css("display", "none");x = false;
			}
		    
		    });
	});
	</script>

<meta charset="ISO-8859-1">
<title>register</title>
</head>
<body>
Welcome to Register Page


<form:form action = "signUp"  method = "post" modelAttribute="user" enctype="multipart/form-data">
     Email:  <c:out value="${email }"></c:out>
     <form:input path="name"  value = "${email}" type = "hidden"/>
    
     <br><br> 
     
     Password:
        <form:input path="password" type="password" placeholder ="PASSWORD"/>  
  
        <br><br> 
        
                Role:   
        Teacher <form:radiobutton  cssClass = "teacher" path="role" value="ROLE_Teacher"/>  
        <div class = "teacher1">
        
        <form:input path="idCard1" type = "file"/>
        </div>
        Student <form:radiobutton path="role" value="ROLE_Student"/> 
        <br><br> 
        
            
     <form:button type = "submit">Register</form:button>
</form:form>
</body>
</html>