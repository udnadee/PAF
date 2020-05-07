//hide status messages on page load
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

$(document).on("click", "#btnLogin1", function(event)
		{
			// Clear alerts---------------------
			$("#alertError").text("");
			$("#alertError").hide();
			// Form validation-------------------
			
			var status = validateLoginForm();
			
			if (status != true)
			{
				$("#alertError").text(status);
				$("#alertError").show();
				return;
			}
			
			// If valid------------------------
			$.ajax(
			{
				url : "AuthApi",
				type : "POST",
				data : $("#formLogin1").serialize(),
				dataType : "text",
				complete : function(response, status)
				{
					onLoginComplete1(response.responseText, status);
				}
			});
		});



		function onLoginComplete1(response, status)
		{
			if (status == "success")
			{
				var resultSet = JSON.parse(response);
				if (resultSet.status.trim() == "success")
				{
					document.location = "Register.jsp";
				}
				else if (resultSet.status.trim() == "error")
				{
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
				}
			} 
			else if (status == "error")
			{
				$("#alertError").text("Error while login.");
				$("#alertError").show();
			} 
			else
			{
				$("#alertError").text("Unknown error while login.");
				$("#alertError").show();
			}
		}

$(document).on("click", "#btnLogin", function(event)
{
	// Clear alerts---------------------
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	
	var status = validateLoginForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	$.ajax(
	{
		url : "AdminAuth",
		type : "POST",
		data : $("#formLogin").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onLoginComplete(response.responseText, status);
		}
	});
});



function onLoginComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			document.location = "Register.jsp";
		}
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while login.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while login.");
		$("#alertError").show();
	}
	$("#hidHosIDSave").val("");
	$("#Hospitalform")[0].reset();
}

function validateLoginForm()
{
	// USERNAME
	if ($("#txtUsername").val().trim() == "")
	{
		return "Insert Username.";
	}

	// PASSWORD
	if ($("#txtPassword").val().trim() == "")
	{
		return "Insert Password.";
	}
	
	return true;
}

$(document).on("click", "#btnLogout", function(event)
{
	$.ajax(
	{
		url : "AdminAuth",
		type : "DELETE",
		data : "",
		dataType : "text",
		complete : function(response, status)
		{
			onLogoutComplete(response.responseText, status);
		}
	});
});
		
function onLogoutComplete(response, status)
{
	if (status == "success")
	{
		if (response.trim() == "success")
		{
			document.location = "index.jsp";
		}
	}
}
		