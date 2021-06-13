<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@                                  page isELIgnored = "false" %>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">


function updateTextInput(val,id) {
    document.getElementById('demo'+id).innerHTML=val; 
    document.getElementById('textInput'+id).value=val; 
  }
  
function myFunction() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
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
<style>
.slidecontainer {
  width: 100%;
}

.slider {
  -webkit-appearance: none;
  width: 100%;
  height: 25px;
  background: #d3d3d3;
  outline: none;
  opacity: 0.7;
  -webkit-transition: .2s;
  transition: opacity .2s;
}

.slider:hover {
  opacity: 1;
}

.slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 25px;
  height: 25px;
  background: #04AA6D;
  cursor: pointer;
}

.slider::-moz-range-thumb {
  width: 25px;
  height: 25px;
  background: #04AA6D;
  cursor: pointer;
}

#myInput {
  background-image: url('/css/searchicon.png');
  background-position: 10px 12px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}
</style>
	<title>Teachers Control Pannel</title>
</head>

<body>



<hr>

<p>
Welcome to teachers control pannel
</p>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">
<h1>All students post</h1>

<div class="container" >
  <h2>Post Table</h2>
  <p>This table can be used to mark or update the marks of a single post by student</p>            
  <table class="table table-condensed" id="myTable">
    <thead>
      <tr>
        <th>Email</th>
        <th>Titile</th>
        <th>Time</th>
        <th>Points</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items = "${allPosts }" var = "post">
          <tr>
        <td>${post.email }</td>
        <td>${post.title }</td>
        <td>${post.time }</td>
        <td>
 <div class="slidecontainer">
  <input type="range" min="0" max="10" value="${post.points }" class="slider" class="myRange" onchange="updateTextInput(this.value, ${post.id });">
   <p>Value: <span id="demo${post.id }" >${post.points }</span></p>
   <form method = "Get" action="teachers/mark">
   <input name = "marks" value = "" id = "textInput${post.id }" type = "hidden">
   <input name = "postId" value = "${post.id }" type = "hidden">
   <button>Mark</button>
   </form>
   
</div>
</td>
      </tr>
    </c:forEach>
   
    </tbody>
  </table>
</div>
<hr>
<a href = "teachers/ReportedContent">Most Reported Content</a>
<a href="${pageContext.request.contextPath}/">Back to Home Page</a>


</body>

</html>









