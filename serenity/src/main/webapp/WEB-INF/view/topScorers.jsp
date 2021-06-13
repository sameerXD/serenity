<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>



<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  jQuery(document).ready(function($) {
	    $(".clickable-row").click(function() {
	        window.location = $(this).data("href");
	    });
	});
  
  function myFunction() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}
  </script>
  <style type="text/css">
  #myInput {
  background-image: url('/css/searchicon.png');
  background-position: 10px 12px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
  </style>
<title>conquerors</title>
</head>
<body>

<div class="container">
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for rank" title="Type in a name">
  <h2>Conqueror Table</h2>
  <p>This Season</p>      
  <fmt:parseNumber var="sno" type="number" value="1" /> 
  <table class="table table-condensed table-hover" id="myTable">
    <thead>
  
      <tr>
        <th>S.NO</th>
        <th>EMAIL</th>
        <th>SCORE</th>
          
      </tr>
    
    </thead>
    <tbody>
   
    <c:forEach items="${topScorers }" var = "score">
      <tr class = "clickable-row" data-href='${pageContext.request.contextPath}/userProfile/${score.email }' >
        <td>${sno }</td>
        <td>${score.email }</td>
        <td>${score.marks }</td>

      </tr>
       <fmt:parseNumber var="sno" type="number" value="${sno+1 }" /> 
     </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>