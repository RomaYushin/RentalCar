<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.sql.Date"%>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Order"%>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Car"%>


<c:if test="${not empty order}">

		<div class="createOrderSuccess">
			<p>ORDER PARAMETRS</p>
			1. Order id: ${order.getId()}
			<br>
			2. Order creation date: ${order.getCreateOrderDate()}
			<br>
			
			3. Car: 
				${order.getOrderCar().getCarBrend()}
				${order.getOrderCar().getCarModel()}
				<%
					Order newOrder = (Order) request.getAttribute("order");
							GregorianCalendar gc = new GregorianCalendar();
							gc.setTime(newOrder.getOrderCar().getCarYearOfIssue());
				%>
				<%=gc.get(Calendar.YEAR)%>
			
			<br>
			4. Client:
				${order.getOrderClient().getUserPassSurname()}
				${order.getOrderClient().getUserPassName()}
				${order.getOrderClient().getUserPassPatronomic()}
			<br>
			5. Start date of order (inclusive): ${order.getOrderStartDate()} 
			<br>
			6. End date of order (inclusive):	${order.getOrderEndDate()}
			<br>
			
			7. The presence of the driver:
				<c:if test="${order.isOrderPresenceOfTheDriver()}"> yes </c:if>
				<c:if test="${not order.isOrderPresenceOfTheDriver()}"> no </c:if>
			<br>
			8. Account id: ${order.getOrderAccount().getId()}
			<br>
			9. Rental total price: ${order.getOrderAccount().getAccountForRent()}
			<br>
			10. Paid: 
				<c:if test="${order.getOrderAccount().isAccountRentPaid()}">yes</c:if>
				<c:if test="${not order.getOrderAccount().isAccountRentPaid()}">no</c:if>
			<br>
			11. Order status: ${order.getOrderStatus()}
			<br>


			<div class="createOrderSuccess_buttons">
				<form action="Controller" method="POST">
					<input type="hidden" name="comman" value="payOrde" /> <input
						type="hidden" name="orderId" value="${order.getId()}" /> <input
						type="submit" value="Approve" />
				</form>
				<form action="Controller" method="POST">
					<input type="hidden" name="comman" value="deleteOrde" /> <input
						type="hidden" name="orderId" value="${order.getId()}" /> <input
						type="submit" value="Reject" />
				</form>
			</div>
		</div>


</c:if>
