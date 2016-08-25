<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<c:if test="${not empty order}">
	

	<%@page import="java.util.Calendar"%>
	<%@page import="java.util.GregorianCalendar"%>
	<%@page import="java.sql.Date"%>
	<%@page import="ua.nure.yushin.SummaryTask4.entity.Order"%>
	<%@page import="ua.nure.yushin.SummaryTask4.entity.Car"%>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src = "js/jquery-3.1.0.js" type = "text/javascript"></script>
		<script src = "js/managerPersonalAreaScript.js" type = "text/javascript"></script>
		
	</head>

		<div class="createOrderSuccess">
			<p> <fmt:message key="showSpecifiedOrder.jsp.mainBlockName" /> </p>
			1.  <fmt:message key="showSpecifiedOrder.jsp.orderId_lbl" />: ${order.getId()}
			<br>
			2. <fmt:message key="showSpecifiedOrder.jsp.orderCreationDate_lbl" />: ${order.getCreateOrderDate()}
			<br>
			
			3. <fmt:message key="showSpecifiedOrder.jsp.car_lbl" />: 
				${order.getOrderCar().getCarBrend()}
				${order.getOrderCar().getCarModel()}
				<%
					Order newOrder = (Order) request.getAttribute("order");
							GregorianCalendar gc = new GregorianCalendar();
							gc.setTime(newOrder.getOrderCar().getCarYearOfIssue());
				%>
				<%=gc.get(Calendar.YEAR)%>
			
			<br>
			4. <fmt:message key="showSpecifiedOrder.jsp.client_lbl" />:
				${order.getOrderClient().getUserPassSurname()}
				${order.getOrderClient().getUserPassName()}
				${order.getOrderClient().getUserPassPatronomic()}
			<br>
			5. <fmt:message key="showSpecifiedOrder.jsp.startDate_lbl" />: ${order.getOrderStartDate()} 
			<br>
			6. <fmt:message key="showSpecifiedOrder.jsp.endDate_lbl" />:	${order.getOrderEndDate()}
			<br>
			
			7. <fmt:message key="showSpecifiedOrder.jsp.driver_lbl" />:
				<c:if test="${order.isOrderPresenceOfTheDriver()}"> yes </c:if>
				<c:if test="${not order.isOrderPresenceOfTheDriver()}"> no </c:if>
			<br>
			8. <fmt:message key="showSpecifiedOrder.jsp.accountId_lbl" />: ${order.getOrderAccount().getId()}
			<br>
			9. <fmt:message key="showSpecifiedOrder.jsp.rentalTotalPrice_lbl" />: ${order.getOrderAccount().getAccountForRent()}
			<br>
			10. <fmt:message key="showSpecifiedOrder.jsp.paid_lbl" />:
				<c:if test="${order.getOrderAccount().isAccountRentPaid()}">yes</c:if>
				<c:if test="${not order.getOrderAccount().isAccountRentPaid()}">no</c:if>
			<br>
			11. <fmt:message key="showSpecifiedOrder.jsp.orderStatus_lbl" />: ${order.getOrderStatus()}
			<br>

			<div class="createOrderSuccess_buttons">
				<input id = "orderId" type = "hidden" value="${order.getId()}" />				
				<button onclick = "approveOrder()" ><fmt:message key="showSpecifiedOrder.jsp.approve_btn" /></button>		
				<button onclick = "showTextArea()" ><fmt:message key="showSpecifiedOrder.jsp.rejectReason_btn" /></button>			
				<button onclick = "closeOrder()" ><fmt:message key="showSpecifiedOrder.jsp.close_btn" /> </button>			
			</div>
			
			<div class = "rejTextArea" >
				<textarea id = "ta" rows="5" cols="50"></textarea>
				<br>
				<button onclick = "rejectOrder()" > <fmt:message key="showSpecifiedOrder.jsp.reject_btn" /> </button>
			</div>
		</div>
		
		
</c:if>

<c:if test="${not empty respMessage }">
	<c:out value="${ respMessage }"></c:out><br>
	<c:choose>
			<c:when test="${fn:contains (sortingType, 'showUntreatedOrders')}">
				<button onclick="showUntreatedOrders()" > Ok! </button>
			</c:when>
			<c:when test="${fn:contains (sortingType, 'showActiveOrders')}">
				<button onclick="showActiveOrders()" > Ok! </button>
			</c:when>
			<c:when test="${fn:contains (sortingType, 'showClosedOrders')}">
				<button onclick="showClosedOrders()" > Ok! </button>
			</c:when>
			<c:when test="${fn:contains (sortingType, 'showRejectedOrders')}">
				<button onclick="showRejectedOrders()" > Ok! </button>
			</c:when>
			<c:when test="${fn:contains (sortingType, 'showAllOrders')}">
				<button onclick="showAllOrders()" > Ok! </button>
			</c:when>
		</c:choose>
</c:if>

	

