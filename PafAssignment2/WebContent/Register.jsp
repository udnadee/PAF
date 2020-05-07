<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%@ page import="controller.HospitalDBHandler"%>
 <%
if (session.getAttribute("Username") == null)
{
response.sendRedirect("index.jsp");
}
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<script src="js/jquery-3.5.0.min.js"></script>
		<script src="js/hospital.js"></script>
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
		
		<title>Hospital Registration </title>
		
	</head>
	<body>
			<nav class="navbar navbar-default navbar-expand-lg navbar-light">
			<div class="navbar-header d-flex col">
				<a class="navbar-brand" href="#"><b>Hospital</b></a>  		
			</div>
			<!-- Collection of nav links, forms, and other content for toggling -->
	<div >	
				<label id="nn"><%= session.getAttribute("Username")%>&nbsp;&nbsp;&nbsp;</label>
				<input id="btnLogout" name="btnLogout" type="button" value="Logout" class="btn btn-primary">
	</div>
</nav>
		
		<div class="container">
			<form id="Hospitalform" method="post">
		
		    <!-- Form Name -->
		    <h2><b>Hospital Registration Form</b></h2>
		    
		    <!-- Hospital Registration No -->
			<div class="form-group">
				<label class="col-md-4 control-label">Hospital Registration No: </label>  
		      	<div class="col-md-4 inputGroupContainer">
		      		<div class="input-group">
		     			 <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		     			 <input  id="hospital_reg_no" name="hospital_reg_no" placeholder="Registration No" class="form-control"  type="text">
		        	</div>
		      	</div>
		    </div>
		
		    <!-- Hospital name-->
		    <div class="form-group">
		    	<label class="col-md-4 control-label" >Hospital Name</label> 
		        <div class="col-md-4 inputGroupContainer">
		       		 <div class="input-group">
		      			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		     			 <input id="hos_name" name="hos_name" placeholder="Hospital Name" class="form-control"  type="text">
		      		 </div>
		        </div>
			</div>
		    
		    <!-- Select type-->
		    <div class="form-group"> 
		      	<label class="col-md-4 control-label">Type</label>
		        <div class="col-md-4 selectContainer">
		        	<div class="input-group">
			        	<span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
			        	<select id="hos_type" name="hos_type" class="form-control selectpicker">
			        		<option value="0">----Select type----</option>
			          		<option>Private</option>
			          		<option>Government</option>
			       	 	</select>
			    	</div>
		    	</div>
		    </div>
		    
		    <!-- Address line i--> 
		    <div class="form-group">
		    	<label class="col-md-4 control-label" >Address line 1</label> 
		        <div class="col-md-4 inputGroupContainer">
		        	<div class="input-group">
		      			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      			<input id="AddressLine1" name="AddressLine1" placeholder="Address line" class="form-control"  type="text">
		        	</div>
		      	</div>
		    </div>
		    
		    <!-- City-->
		    <div class="form-group">
		     	<label class="col-md-4 control-label" >City</label> 
		        <div class="col-md-4 inputGroupContainer">
		        	<div class="input-group">
		    			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      			<input id="city" name="city" placeholder="City" class="form-control"  type="text">
		        	</div>
		      	</div>
		    </div>
		    
		    <!-- Select District-->
		    <div class="form-group"> 
		    	<label class="col-md-4 control-label">District</label>
		        <div class="col-md-4 selectContainer">
			        <div class="input-group">
			        	<span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
			      		<select id="district" name="district" class="form-control selectpicker">
			          		<option value="0">----Select District----</option>
			          		<option>Kandy</option>
							<option>Matale</option>
							<option>Nuwara Eliya</option>
							<option>Ampara</option>
							<option>Batticaloa</option>
							<option>Trincomalee</option>
							<option>Anuradhapura</option>
							<option>Polonnaruwa</option>
							<option>Kurunegala</option>
							<option>Puttalam</option>
							<option>Jaffna</option>
							<option>Kilinochchi</option>
							<option>Mannar</option>
							<option>Mullaitivu</option>
							<option>Vavuniya</option>
							<option>Kegalle</option>
							<option>Ratnapura</option>
							<option>Galle</option>
							<option>Hambantota</option>
							<option>Matara</option>
							<option>Badulla</option>
							<option>Monaragala</option>
							<option>Colombo</option>
							<option>Gampaha</option>
							<option>Kalutara</option>
			        	</select>
			      	</div>
		    	</div>
		    </div>
		    
		    <!-- Select province-->
		    <div class="form-group"> 
		    	<label class="col-md-4 control-label">Province</label>
		        <div class="col-md-4 selectContainer">
		        	<div class="input-group">
		            	<span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
		        		<select id="province" name="province" class="form-control selectpicker">
		          			<option value="0">----Select Province----</option>
		          			<option>Central</option>
							<option>Eastern</option>
							<option>North Central</option>
							<option>North Western</option>
							<option>Northern</option>
							<option>Sabaragamuwa</option>
							<option>Southern</option>
							<option>Uva</option>
							<option>Western</option>
		        		</select>
		      		</div>
		    	</div>
		    </div>
		    
		    <!-- password-->
		    <div class="form-group">
		    	<label class="col-md-4 control-label" >Password</label> 
		        <div class="col-md-4 inputGroupContainer">
		        	<div class="input-group">
		     			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      			<input id="hos_password" name="hos_password" placeholder="Password" class="form-control"  type="password">
		        	</div>
		      	</div>
		    </div>
		    
		    <!-- confirm password-->
		    <div class="form-group">
		    	<label class="col-md-4 control-label" >Confirm Password</label> 
		        <div class="col-md-4 inputGroupContainer">
		        	<div class="input-group">
		      			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      			<input id="hos_password_con" name="hos_password_con" data-rule-equalTo="#hos_password" placeholder="Confirm Password" class="form-control"  type="password">
		        		<span id='message'></span>
		        	</div>
		      	</div>
		    </div>
		    
		     <!-- fee-->
		    <div class="form-group">
		     	<label class="col-md-4 control-label" >Hospital charge (Rs.) </label> 
		        <div class="col-md-4 inputGroupContainer">
		        	<div class="input-group">
		    			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      			<input id="hospital_fee" name="hospital_fee" placeholder="hospital_fee" class="form-control"  type="text">
		        	</div>
		      	</div>
		    </div>
		    
		    <!-- Contact No-->        
		    <div class="form-group">
		    	<label class="col-md-4 control-label">Contact No</label>  
		        <div class="col-md-4 inputGroupContainer">
		        	<div class="input-group">
		            	<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
		      			<input id="telephone" name="telephone"  class="form-control" type="text">
		        	</div>
		      	</div>
		    </div>
		    
		    <!-- alert message -->
		    <div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
		    <input type="button" id="btnSave" value="Save" class="btn btn-primary">	
			<input type="hidden" id="hidHosIDSave" name="hidHosIDSave" value="">	
			
		   </form>
	    </div>
	    <div style = "float: right;margin-right: 100px;">
	    <form class="navbar-form form-inline">
					<div class="input-group search-box" >								
						<input type="text" id="search" name="search"class="form-control" placeholder="Search here...">
					</div>
		</form>
	    </div>
	    <br>
	    <div id="divHosGrid">
		<%
			HospitalDBHandler itemObj = new HospitalDBHandler();
			out.print(itemObj.readHospital());
		%>
		</div>
		<br>
		<br>
	</body>
</html>