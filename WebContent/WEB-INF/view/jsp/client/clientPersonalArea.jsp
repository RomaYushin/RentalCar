<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.sql.Date" %>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Order"%>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Car"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><fmt:message key="cliPerArea.jsp.title" /></title>	
		<!-- <link href = "css/welcomeAuthorizationStyle.css" type = "text/css" rel = "stylesheet" /> -->
		
		<script src = "js/jquery-3.1.0.js" type = "text/javascript"></script>
		<!-- <script src = "js/jquery-3.1.0.min.js" type = "text/javascript"></script>  -->
		<script src = "js/clientPersonalAreaScript.js" type = "text/javascript"></script>	
	</head>
	<body>
		<div class = "wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class = "mainContent" >
				<!--<fmt:message key="welcomeAuthorization.jsp.mainBlockName" /> -->
				<h1> <fmt:message key="cliPerArea.jsp.mainContent" /> </h1>
				<div class = "mainButtons">
					<input id = "clientId" type = "hidden" value="${ user.getId() }" >
					<!-- ajax submin in js file --> 
					<button id = "orderCarButton" onclick ="sendOrderCar()"> <fmt:message key="cliPerArea.jsp.orderCar_btn" /> </button>
					<button id = "myOrders" onclick ="sendMyOrders()" > <fmt:message key="cliPerArea.jsp.myOrders_btn" /> </button>
					<button id = "carsReview" onclick ="sendCarsReview()" > <fmt:message key="cliPerArea.jsp.carsReview_btn" /> </button>
					<button id = "ordersArchive" onclick ="sendOrdersArchive()" > <fmt:message key="cliPerArea.jsp.ordersArchive_btn" /> </button>
				</div>
				
				<div class = "mainWindow">
				<c:if test="${not empty isCreateOrderSuccess}">
					<c:if test="${isCreateOrderSuccess}">
						<div class = "createOrderSuccess">
							<p> <fmt:message key="cliPerArea.jsp.yourOrderSuccessCreated" /> </p>
							<p> <fmt:message key="cliPerArea.jsp.yourOrderDate" />: </p>
							<p> 1. <fmt:message key="cliPerArea.jsp.yourOrderCar" />:
								${newOrder.getOrderCar().getCarBrend()}  
								${newOrder.getOrderCar().getCarModel()}								
								<%
									Order newOrder = (Order) request.getAttribute("newOrder");
									GregorianCalendar gc = new GregorianCalendar();
									gc.setTime(newOrder.getOrderCar().getCarYearOfIssue());
								%>						
								<%= gc.get(Calendar.YEAR)%>
							</p>
							<p> 2. <fmt:message key="cliPerArea.jsp.yourDriver" />:
								<c:if test="${newOrder.isOrderPresenceOfTheDriver()}">
									<fmt:message key="cliPerArea.jsp.personalDriverYes" />
								</c:if>
								<c:if test="${not newOrder.isOrderPresenceOfTheDriver()}">
									<fmt:message key="cliPerArea.jsp.personalDriverNo" />
								</c:if>
							</p>
							<p> 3. <fmt:message key="cliPerArea.jsp.yourStartDate" />:	${newOrder.getOrderStartDate()} </p>
							<p> 4. <fmt:message key="cliPerArea.jsp.yourEndDate" />: ${newOrder.getOrderEndDate()}  </p>
							<p> 5. <fmt:message key="cliPerArea.jsp.yourAccountId" />: ${newOrder.getOrderAccount().getId()} </p>
							<p> 6. <fmt:message key="cliPerArea.jsp.totalPrice" />:  ${newOrder.getOrderAccount().getAccountForRent()} </p>
							<div class = "createOrderSuccess_buttons">
								<form action = "Controller" method = "POST">
									<input type = "hidden" name = "command" value = "payOrder"/>
									<input type = "hidden" name = "rentPayment" value = "${newOrder.getOrderAccount().getAccountForRent()}"/>	
									<input type = "hidden" name = "orderId" value = "${newOrder.getId()}"/>
									<input type = "submit" value = " <fmt:message key="cliPerArea.jsp.payOrder" />" />
								</form>
								<form action = "Controller" method = "POST">
									<input type = "hidden" name = "command" value = "deleteOrder"/>
									<input type = "hidden" name = "orderId" value = "${newOrder.getId()}"/>
									<input type = "submit" value = "<fmt:message key="cliPerArea.jsp.deleteOrder" />" />
								</form>
							</div> 																	
						</div>
						
					</c:if>					
					<c:if test="${not isCreateOrderSuccess}">
						<c:out value="${errorMessage}"></c:out>
					</c:if>
				</c:if>
				
				<c:if test="${not empty payment}">
					<c:if test="${payment}">
						<p> <fmt:message key="cliPerArea.jsp.succesfullPayment" />! </p>
					</c:if>
					<c:if test="${not payment}">
						<p> <fmt:message key="cliPerArea.jsp.failPayment" />! </p>
					</c:if>
				</c:if>	
				
				</div>								
			</div>
		</div>
		
	</body>
</html>