function showUntreatedOrders() {
	var command = "showUntreatedOrders";
	sendCommandAsync(command);
}

function showActiveOrders() {
	var command = "showActiveOrders";
	sendCommandAsync(command);
}

function showClosedOrders() {
	var command = "showClosedOrders";
	sendCommandAsync(command);
}

function showRejectedOrders() {
	var command = "showRejectedOrders";
	sendCommandAsync(command);
}

function showAllOrders() {
	var command = "showAllOrders";
	sendCommandAsync(command);
}

function sendCommandAsync (command) {
	$.ajax({
		type : "GET",
		url : "Controller?command=showOrders",
		data: {
			sortingType: command
		},
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});	
}

function openOrder (orderId) {
	$.ajax({
		type : "POST",
		url : "Controller?command=showSpecifiedOrder",
		data: {
			orderId: orderId
		},
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});	
}

