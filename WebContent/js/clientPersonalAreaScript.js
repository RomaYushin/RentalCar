
/************************************
 * ******** main buttons ************
 * **********************************
 * */
 
function sendOrderCar() {
	
	$.ajax({
		type : "GET",
		url : "Controller?command=orderCarButton",
		success : function(responseText) {
			// alert ("successResponse" + responseText);
			$(".mainWindow").html(responseText);
			// selectDates();
		},
		error : function() {
			alert("error in orderCarButton ");
		}
	});
}

function sendMyOrders() {
	
	var clientId = $('#clientId').val();
	//alert(clientId);
	
	$.ajax({
		type : "POST",
		url : "Controller?command=clientNotClosedOrders",
		data: {
			clientId: clientId
		},
		success : function(responseText) {
			$(".mainWindow").html(responseText);

		},
		error : function() {
			alert("error in checkOrderStatus ");
		}
	});
	
}

function sendCarsReview () {
	
	$.ajax({
		type : "GET",
		url : "Controller?command=clientCarsReview",
		success : function(responseText) {
			$(".mainWindow").html(responseText);

		},
		error : function() {
			alert("error in checkOrderStatus ");
		}
	});
	
}

function sendOrdersArchive() {
	
	$.ajax({
		type : "GET",
		url : "Controller?command=clientOrdersArchive",
		success : function(responseText) {
			$(".mainWindow").html(responseText);

		},
		error : function() {
			alert("error in checkOrderStatus ");
		}
	});
}
/**************************************
 * ********* after orderCar *********** 
 * ************************************
 */
function submitDates() {

	var startDate = $('#orderStartDate').val();
	var endDate = $('#orderEndDate').val();

	if (startDate.length != 0 & endDate.length != 0) {

		$.ajax({
			type : "POST",
			url : "Controller?command=selectCarsByRentalDates",
			data : {
				orderStartDate : startDate,
				orderEndDate : endDate
			},
			success : function(responseText) {
				$(".availableCars").html(responseText);
				$(".driver").hide();
				$(".totalPrice").hide();
				$(".createOrder").hide();
			},
			error : function() {
				alert("error");
			}
		});
	}
}

function sortByPriceASC() {
	var sortingType = "sortByPriceASC";
	sendSort(sortingType);
}

function sortByPriceDESC() {
	var sortingType = "sortByPriceDESC";
	sendSort(sortingType);
}

function sortByCarName() {	
	var sortingType = "sortByCarName";
	sendSort(sortingType);
}

function sendSort(sortingType) {
	var startDate = $('#orderStartDate').val();
	var endDate = $('#orderEndDate').val();
	
	$.ajax({
		type : "POST",
		url : "Controller?command=selectCarsByRentalDates",
		data : {
			orderStartDate : startDate,
			orderEndDate : endDate,
			sortingType : sortingType			
		},
		success : function(responseText) {
			// alert ("successResponse" + responseText);
			$(".availableCars").html(responseText);
			$(".driver").hide();
			$(".totalPrice").hide();
			$(".createOrder").hide();
		},
		error : function() {
			alert("error in checkOrderStatus ");
		}
	});
}

function selectCarBrend() {
	var selectType = "selectCarBrend";
	var selectOption = $('#carBrend_select > option[name=carBrend_option]:selected').attr('id');
	sendSelect(selectType, selectOption);
	
}

function selectCarQualityClass() {
	var selectType = "selectCarQualityClass";
	var selectOption = $('#carQualityClass_select > option[name=carQualityClass_option]:selected').attr('id');
	sendSelect(selectType, selectOption);
}

function sendSelect(selectType, selectOption) {	
	//alert ("selectType: " + selectType + "; selectOption: " + selectOption);
	var startDate = $('#orderStartDate').val();
	var endDate = $('#orderEndDate').val();
	
	$.ajax({
		type : "POST",
		url : "Controller?command=selectCarsByRentalDates",
		data : {
			orderStartDate : startDate,
			orderEndDate : endDate,
			selectType : selectType,
			selectOption: selectOption
		},
		success : function(responseText) {
			// alert ("successResponse" + responseText);
			$(".availableCars").html(responseText);
			$(".driver").hide();
			$(".totalPrice").hide();
			$(".createOrder").hide();
		},
		error : function() {
			alert("error in checkOrderStatus ");
		}
	});
	
}

function selectCar() {
	$(".driver").show();
	var driver = $('.driver > input[name=driver]:checked').val();
	if (driver != undefined) {
		sendCar();
	}
}

function sendCar() {

	var driver = $('.driver > input[name=driver]:checked').val();
	var startDate = $('#orderStartDate').val();
	var endDate = $('#orderEndDate').val();
	var carId = $('.car > input[name=car]:checked').attr('id');
	// alert (driver + " " + startDate + " " + endDate + " " + carId);

	$.ajax({
		type : "POST",
		url : "Controller?command=calculateTotalPriceAsync",
		data : {
			driver : driver,
			orderStartDate : startDate,
			orderEndDate : endDate,
			carId : carId
		},
		success : function(responseText) {
			$(".totalPrice").show();
			$(".totalPrice").html(responseText);
			$(".createOrder").show();
		},
		error : function() {
			alert("error");
		}
	});
}

function createNewOrder() {
	
	var startDate = $('#orderStartDate').val();
	var endDate = $('#orderEndDate').val();
	var car =  $('.car > input[name=car]:checked').attr('value');
	var carId = $('.car > input[name=car]:checked').attr('id');
	var driver = $('.driver > input[name=driver]:checked').val();
	var totalPrice = $('#totalPrice').attr('value');

	$('#ordFormStartDate_id').val(startDate);
	$('#ordFormEndDate_id').val(endDate);
	$('#ordFormCar_id').val(carId);
	$('#ordFormDriver_id').val(driver);
	$('#ordFormTotalPrice_id').val(totalPrice);
	
	var message = "Your order parametrs:" + 
			"\n  1. Start date: " + startDate +
			"\n  2. End date: " + endDate +
			"\n  3. Car: " + car + 
			"\n  4. Presence of the driver: " + driver +
			"\n  5. Total price: " + totalPrice + 
			"\n\n If you want to create a new order , click Ok, otherwise click Cancel" ;
			
	var answer = confirm(message);	
	
	if (answer) {
		$('#createOrderForm').submit(); 
	}
}

/******************************************
 * ******* after my orders ****************
 * ****************************************
 */
function openOrder (orderId) {
	
	
	$.ajax({
		type : "POST",
		url : "Controller?command=openClientOrder",
		data : {
			orderId : orderId
		},
		success : function(responseText) {
			$(".mainWindow").html(responseText);
		},
		error : function() {
			alert("error");
		}
	});
}

/******************************************
 * ******* after cars review **************
 * ****************************************
 */
























