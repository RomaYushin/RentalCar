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
		<title>clientPersonalArea</title>	
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
				<h1>Client personal Area</h1>
				<div class = "mainButtons">
					<!-- ajax submin in js file -->
					<button id = "orderCarButton" onclick ="sendOrderCar()"> Order Car </button>
					<button id = "checkOrderStatus" onclick ="sendOrderStatus()" > Check order status </button>
				</div>
				
				<div class = "mainWindow">
				<c:if test="${not empty isCreateOrderSuccess}">
					<c:if test="${isCreateOrderSuccess}">
						<div class = "createOrderSuccess">
							<p> Your order was successfully created!!! </p>
							<p> Date of your order:</p>
							<p> 1. Car:
								${newOrder.getOrderCar().getCarBrend()}  
								${newOrder.getOrderCar().getCarModel()}								
								<%
									Order newOrder = (Order) request.getAttribute("newOrder");
									GregorianCalendar gc = new GregorianCalendar();
									gc.setTime(newOrder.getOrderCar().getCarYearOfIssue());
								%>						
								<%= gc.get(Calendar.YEAR)%>
							</p>
							<p> 2. The presence of the driver:
								<c:if test="${newOrder.isOrderPresenceOfTheDriver()}">
									yes
								</c:if>
								<c:if test="${not newOrder.isOrderPresenceOfTheDriver()}">
									no
								</c:if>
							</p>
							<p> 3. Start date of order (inclusive):	${newOrder.getOrderStartDate()} </p>
							<p> 4. End date of order (inclusive): ${newOrder.getOrderEndDate()}  </p>
							<p> 5. Account id: ${newOrder.getOrderAccount().getId()} </p>
							<p> 6. Total rent price:  ${newOrder.getOrderAccount().getAccountForRent()} </p>
							<div class = "createOrderSuccess_buttons">
								<form action = "Controller" method = "POST">
									<input type = "hidden" name = "command" value = "payOrder"/>
									<input type = "hidden" name = "orderId" value = "${newOrder.getId()}"/>
									<input type = "submit" value = "Pay" />
								</form>
								<form action = "Controller" method = "POST">
									<input type = "hidden" name = "command" value = "deleteOrder"/>
									<input type = "hidden" name = "orderId" value = "${newOrder.getId()}"/>
									<input type = "submit" value = "Delete order" />
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
						<p> Payment was successfull! </p>
					</c:if>
					<c:if test="${not payment}">
						<p> Payment was not made! </p>
					</c:if>
				</c:if>		
				</div>								
			</div>
		</div>
		
	</body>
</html>