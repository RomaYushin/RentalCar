function showAddNewCarForm () {
	var command = "showAddNewCarForm";
	sendCommandAsync(command);
} 

function showAllCarsForm () {
	var command = "showAllCarsForm";
	sendCommandAsync(command);
}

function showBlockUserForm() {
	var command = "showBlockUserForm";
	sendCommandAsync(command);
}

function showRegisterManagerForm() {
	var command = "showRegisterManagerForm";
	sendCommandAsync(command);
}

function sendCommandAsync (command) {
	$.ajax({
		type : "GET",
		url : "Controller?command="+command,
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});
}

function removeCar(carId) {	
	
	var answer = confirm("Are you sure to delete this car?");
	
	if (answer) {
		$(this).submit(function () {
			$(this).serialize;
		});
	}	
}

function showEditCarForm (carId) {	
	$.ajax({
		type : "POST",
		url : "Controller?command=showEditCarForm",
		data : {
			carId : carId,
		}, 
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});
}
/*
function saveNewCarParametrs() {
	
	var carId = $('#carId').val();
	var newCarBrend = $('.field > input[name=newCarBrend]').val();
	var newCarModel = $('.field > input[name=newCarModel]').val();
	var newCarYearOfIssue = $('.field > input[name=newCarYearOfIssue]').val();
	var newCarQualityClass = $('#newCarQualityClass').val();
	var newCarRentalCost = $('.field > input[name=newCarRentalCost]').val();
	var newCarStatus = $('#newCarStatus').val();
	
	alert ("carId:" + carId);
	alert ("newCarBrend:" + newCarBrend);
	alert ("newCarModel:" + newCarModel);
	alert ("newCarYearOfIssue:" + newCarYearOfIssue);
	alert ("newCarQualityClass:" + newCarQualityClass);
	alert ("newCarRentalCost:" + newCarRentalCost);
	alert ("newCarStatus:" + newCarStatus);
	
	
	$.ajax({
		type : "POST",
		url : "Controller?command=editCar",
		data : {
			carId: carId,
			newCarBrend: newCarBrend,
			newCarModel: newCarModel,
			newCarYearOfIssue: newCarYearOfIssue,
			newCarQualityClass: newCarQualityClass,
			newCarRentalCost: newCarRentalCost,
			newCarStatus: newCarStatus
		}, 
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});
	
}
*/
function updateBlocking (userId, isUserBlocking) {
	//alert ("userId: " + userId + ", isUserBlocking: " + isUserBlocking);
	
	$.ajax({
		type : "POST",
		url : "Controller?command=updateUserBlocking",
		data : {
			userId: userId,
			isUserBlocking: isUserBlocking
		}, 
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});	
}

function registerNewManagerForm() {	
	var message = $('#registerManagerForm').serialize();
	
	$.ajax({
		type : "POST",
		url : "Controller?command=registerNewManager",
		data : message, 
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});	
}
//$('#addNewCarForm').submit();