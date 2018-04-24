$( document ).ready(function() {
	
	// GET REQUEST
	$("#getAllEmployeeBtnId").on('click', function(){
		$.ajax({
			type : "GET",
			url : window.location + "http://localhost:8080/employee/list",
			dataType: "json",
			success: function(json){
					$('#resultGetAllEmployeeDiv ul').empty();
					var empList = "";
					$.each(result, function(i, employee){
						var emp = "{empId: " + emp.empId + 
						", empName: " + emp.empName +
						", empAddress: " + emp.empAddress +"}";
						
						$('#resultGetAllEmployeeDiv.list-group').append("<li>" + emp + "</li>");
			        });
					
					// just re-css for result-div
					$('#resultGetAllCustomerDiv').css({'background-color':'#D16953', 'color':'white', 'padding':'20px 20px 5px 30px'});
					
					// just hide POST button
					if($('#customerTable').is(":hidden")){
						$('#postCustomersBtn').hide();	
					}
			},
			error : function(e) {
				$("#getResultDiv").html("<strong>Error</strong>");
				console.log("ERROR: ", e);
			}
		});
	});
})