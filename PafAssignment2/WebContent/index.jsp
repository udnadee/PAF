<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="controller.HospitalDBHandler"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
	<link rel="stylesheet" href="css/bootstrap.min.css">
		<script src="js/jquery-3.5.0.min.js"></script>
		<script src="js/auth.js"></script>
		<link rel="stylesheet" href="css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		
		<script>
			$(document).ready(function(){
			  $("#search").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
		</script>
		
		<title>Hospital</title>
	</head>
	<body>
		
		<nav class="navbar navbar-default navbar-expand-lg navbar-light">
			<div class="navbar-header d-flex col">
				<a class="navbar-brand" href="#"><b>Hospital</b></a>  		
			</div>
			<div >	
				<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Login</button>
			</div>
		</nav>
	
		<br>
		<form class="navbar-form form-inline">
					<div class="input-group search-box" >								
						<input type="text" id="search" name="search"class="form-control" placeholder="Search here...">
					</div>
		</form>
		
		<h3 align = "center">Hospital details</h3>
	
		<%
			HospitalDBHandler itemObj = new HospitalDBHandler();
			out.print(itemObj.readAllHospitalView());
		%>
		
		<div id="id01" class="modal">
			<form id="formLogin" class="modal-content animate">
				<div class="imgcontainer">
		      		<span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
		      		
		      		<h2>Login</h2>
		    	</div>
				<div class="container">
					<label> Username: </label> <input id="txtUsername" name="txtUsername" type="text" class="form-control form-control-sm">
					<label> Password: </label> <input id="txtPassword" name="txtPassword" type="password" class="form-control form-control-sm">
					<br>
					<input id="btnLogin" name="btnLogin" type="button" value="Login" class="btn btn-primary" style="width:100%">
					<br>
					<div id="alertError" class="alert alert-danger"></div>
				</div>	
			</form>
		</div>
	</body>
</html> 