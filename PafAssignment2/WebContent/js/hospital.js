
//hide status messages on page load
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateHosForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// if valid
	var type = ($("#hidHosIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
			{
			url : "HospitalAPI",
			type : type,
			data : $("#Hospitalform").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onHosSaveComplete(response.responseText, status);
			}
		});
});
	
$(document).on("click", ".btnRemove", function(event)
		{
			$.ajax(
			{
				url : "HospitalAPI",
				type : "DELETE",
				data : "hos_id=" + $(this).data("hosid"),
				dataType : "text",
				
				complete : function(response, status)
				{
					onDeleteComplete(response.responseText, status);
				}
		});
});

function onDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divHosGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}
	
function onHosSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divHosGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidHosIDSave").val("");
	$("#Hospitalform")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidHosIDSave").val($(this).closest("tr").find('#hidHosIDUpdate').val());
	$("#hospital_reg_no").val($(this).closest("tr").find('td:eq(1)').text());
	$("#hos_name").val($(this).closest("tr").find('td:eq(2)').text());
	$("#hos_type").val($(this).closest("tr").find('td:eq(3)').text());
	$("#AddressLine1").val($(this).closest("tr").find('td:eq(4)').text());
	$("#city").val($(this).closest("tr").find('td:eq(5)').text());
	$("#district").val($(this).closest("tr").find('td:eq(6)').text());
	$("#province").val($(this).closest("tr").find('td:eq(7)').text());
	$("#telephone").val($(this).closest("tr").find('td:eq(8)').text());
	$("#hospital_fee").val($(this).closest("tr").find('td:eq(9)').text());
	$("#hos_password").val("************");
	$("#hos_password_con").val("************");
});



//CLIENT-MODEL=================================================================
$("#hos_password, #hos_password_con").on("keyup", function () {
	  if ($("#hos_password").val() == $("#hos_password_con").val()) {
	    $("#message").html("Matching").css("color", "green");
	  } else 
	    $("#message").html("Not Matching").css("color", "red");
});

function validateHosForm()
{
	if ($("#hospital_reg_no").val().trim() == "")
	{
		return "Insert regeistration no.";
	}
	
	if ($("#hos_name").val().trim() == "")
	{
		return "Insert hospital name.";
	}
	if ($("#hos_type").val() == "0")
	{
		return "Select hospital type.";
	}
	if ($("#AddressLine1 ").val().trim() == "")
	{
		return "Insert Address line.";
	}
	if ($("#city").val().trim() == "")
	{
		return "Insert city";
	}
	if ($("#district").val() == "0")
	{
		return "Select district.";
	}
	if ($("#province").val() == "0")
	{
		return "Select province.";
	}
	
	if ($("#hospital_fee").val().trim() == "")
	{
		return "Insert hospital fee";
	}
	else if (isNaN($("#hospital_fee").val() ))
	{
		return "Insert valid fee";
	} 
	
	if ($("#telephone").val().trim() == "")
	{
		return "Insert telephone number";
	}
	else if($("#telephone").length >= "10" && $("#telephone").length <= "15"){
		return "Insert valid telephone number";
	}
	
	if ($("#hos_password").val().trim() == "")
	{
		return "Insert password";
	}
	
	if ($("#hos_password_con").val().trim() == "")
	{
		return "Confirm password";
	}
	
	if ($("#hos_password").val() == $("#hos_password_con").val()) {
	} 
	else {
		return " password not match";
	}
	
	var a = document.getElementById("telephone").value;
    var filter = /^((\+[1-9]{1,4}[ \-]*)|(\([0-9]{2,3}\)[ \-]*)|([0-9]{2,4})[ \-]*)*?[0-9]{3,4}?[ \-]*[0-9]{3,4}?$/;
    if (filter.test(a)) {
        return true;
    }
    else {
        return "invalide phone number";
    }
	
	return true;
}