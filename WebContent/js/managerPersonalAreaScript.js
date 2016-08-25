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
	
	//alert (command);
	$.ajax({
		type : "GET",
		url : "Controller?command=showOrders",
		data: {
			sortingType: command
		},
		success : function(responseText) {
			$(".mainWindow").html(responseText);
			
			//alert(responseText);
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
			$(".rejTextArea").hide();
			//alert(responseText);
		},
		error : function() {
			alert("error");
		}
	});	
}

function approveOrder () {	
	
	var orderId = $('#orderId').val();

	$.ajax({
		type : "POST",
		url : "Controller?command=approveOrder",
		data : {
			orderId: orderId 
		},
		success : function(responseText) {
			$(".mainWindow").html(responseText);
			$(".rejTextArea").hide();
			//alert(responseText);
		},
		error : function() {
			alert("error");
		}
	});	
}

function closeOrder () {
	
	var orderId = $('#orderId').val();
	
	$.ajax({
		type : "POST",
		url : "Controller?command=closeOrder",
		data : {
			orderId: orderId 
		},
		success : function(responseText) {
			$(".mainWindow").html(responseText);
			$(".rejTextArea").hide();
			//alert(responseText);
		},
		error : function() {
			alert("error");
		}
	});	
}

function rejectOrder () {	
	
	var orderId = $('#orderId').val();
	var rejectionReason = $('#ta').val();
	//alert("orderId " + orderId + ", rejectionReason: " + rejectionReason);
	
	
	$.ajax({
		type : "POST",
		url : "Controller?command=rejectOrder",
		data : {
			orderId: orderId,
			rejectionReason: rejectionReason
		}, 
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});
	
}

function showTextArea () {
	$('.rejTextArea').show();
}
