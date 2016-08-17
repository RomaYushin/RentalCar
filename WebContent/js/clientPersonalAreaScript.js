$(function() {
	main();
});

function main() {
	
	$('#orderCarButton').click (function () {
		$.ajax({
			type : "GET",
			url : "Controller?command=orderCarButton",
			success : function(responseText) {
				//alert ("successResponse" + responseText);
				$(".mainWindow").html(responseText);
				selectDates();
			},
			error: function () {
				alert ("error in orderCarButton ");
			}
		});
	});
	
	$('#checkOrderStatus').click (function () {
		$.ajax({
			type : "GET",
			url : "Controller?command=checkOrderStatus",
			success : function(responseText) {
				//alert ("successResponse" + responseText);
				$(".mainWindow").html(responseText); 
				
			},
			error: function () {
				alert ("error in checkOrderStatus ");
			}
		});
	});	
	
	$('.sortButtons > button').click(function() {		
		var sortingType = this.id;
		var startDate = $('#orderStartDate').val();
		var endDate = $('#orderEndDate').val();
		
		$.ajax({
			type : "GET",
			url : "Controller?command=selectCarsByRentalDates",
			data: {
				orderStartDate: startDate,
				orderEndDate: endDate,
				sortingType: sortingType,
			} ,
			success : function(responseText) {
				//alert ("successResponse" + responseText);
				$(".availableCars").html(responseText); 
				$("#createOrderForm").hide();
				main();
			},
			error: function () {
				alert ("error in checkOrderStatus ");
				main();
			}
		});
	});
	
	$('.car > input').click (function () {
		var driver = $('.driver > input[name=driver]:checked').val();
		var startDate = $('#orderStartDate').val();
		var endDate = $('#orderEndDate').val();
		var carId = this.id;
		
		if (driver == undefined ) {
			var answer = confirm("Do you need personal driver?");
			if (answer) {
				driver = true;
			} else {
				driver = false;
			}
		}
		//alert (driver + " " + startDate + " " + endDate + " " + carId);

		$.ajax({
			type : "POST",
			url : "Controller?command=calculateTotalPriceAsync",
			data: {
				driver : driver,
				orderStartDate: startDate,
				orderEndDate: endDate,
				carId: carId
			} ,
			success : function(responseText) {
				//alert ("successResponse" + responseText);
				$(".totalPrice").html(responseText);
				//main();
				$("#createOrderForm").show();
				
			},
			error: function () {
				alert ("error");
				//main();
			}
		});
		
	});
	
}

function selectDates() {

	var startDate;
	var endDate;

	$('#orderStartDate').change(function() {
		startDate = $('#orderStartDate').val();
		submitDates(startDate, endDate);
	});

	$('#orderEndDate').change(function() {
		endDate = $('#orderEndDate').val();
		submitDates(startDate, endDate);
	});
}


function submitDates(startDate, endDate) {
	if (startDate != undefined & endDate != undefined) {
		// alert (startDate + " " + endDate);

		$.ajax({
			type : "GET",
			url : "Controller?command=selectCarsByRentalDates",
			data: {
				orderStartDate: startDate,
				orderEndDate: endDate
			} ,
			success : function(responseText) {
				//alert ("successResponse" + responseText);
				$(".availableCars").html(responseText);
				$("#createOrderForm").hide();
				main();
			},
			error: function () {
				alert ("error");
				main();
			}
		});
	}
}
