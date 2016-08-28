<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.sql.Date"%>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Order"%>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Car"%>

<div class="createOrderSuccess">
	<table border="1" cellpadding="4" width="50%">
		<caption>caption</caption>
		<tr>
			<th colspan="2"><fmt:message key="showSpecifiedOrder.jsp.mainBlockName" /></th>
		</tr>
		<tr>
			<td>1. <fmt:message key="showSpecifiedOrder.jsp.orderId_lbl" />:
			</td>
			<td>${order.getId()}</td>
		</tr>
		<tr>
			<td>2. <fmt:message	key="showSpecifiedOrder.jsp.orderCreationDate_lbl" />:
			</td>
			<td>${order.getCreateOrderDate()}</td>
		</tr>
		<tr>
			<td>3. <fmt:message key="showSpecifiedOrder.jsp.car_lbl" />:
			</td>
			<%
				Order newOrder = (Order) request.getAttribute("order");
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(newOrder.getOrderCar().getCarYearOfIssue());
			%>
			<td>${order.getOrderCar().getCarBrend()}
				${order.getOrderCar().getCarModel()} 
				<%=gc.get(Calendar.YEAR)%>
			</td>
		</tr>
		<tr>
			<td>4. <fmt:message key="showSpecifiedOrder.jsp.client_lbl" />:
			</td>
			<td>${order.getOrderClient().getUserPassSurname()}
				${order.getOrderClient().getUserPassName()}
				${order.getOrderClient().getUserPassPatronomic()}</td>

		</tr>
		<tr>
			<td>5. <fmt:message key="showSpecifiedOrder.jsp.startDate_lbl" />:
			</td>
			<td>${order.getOrderStartDate()}</td>
		</tr>
		<tr>
			<td>6. <fmt:message key="showSpecifiedOrder.jsp.endDate_lbl" />:
			</td>
			<td>${order.getOrderEndDate()}</td>
		</tr>
		<tr>
			<td>7. <fmt:message key="showSpecifiedOrder.jsp.driver_lbl" />:
			</td>
			<td><c:if test="${order.isOrderPresenceOfTheDriver()}">
					<fmt:message key="showSpecifiedOrder.jsp.yes_lbl" />
				</c:if>
				 <c:if test="${not order.isOrderPresenceOfTheDriver()}">
					<fmt:message key="showSpecifiedOrder.jsp.no_lbl" />
				</c:if></td>
		<tr>
			<td>8. <fmt:message key="showSpecifiedOrder.jsp.accountId_lbl" />:
			</td>
			<td>${order.getOrderAccount().getId()}</td>
		</tr>
		<tr>
			<td>9. <fmt:message	key="showSpecifiedOrder.jsp.rentalTotalPrice_lbl" />:
			</td>
			<td>${order.getOrderAccount().getAccountForRent()}</td>
		</tr>
		<tr>
			<td>10. <fmt:message key="showSpecifiedOrder.jsp.paid_lbl" />:
			</td>
			<td><c:if test="${order.getOrderAccount().isAccountRentPaid()}">
					<fmt:message key="showSpecifiedOrder.jsp.yes_lbl" />
				</c:if> 
				<c:if test="${not order.getOrderAccount().isAccountRentPaid()}">
					<fmt:message key="showSpecifiedOrder.jsp.no_lbl" />
				</c:if></td>
		</tr>
		<tr>
			<td>11. <fmt:message key="showSpecifiedOrder.jsp.orderStatus_lbl" />:
			</td>
			<td><c:choose>
					<c:when
						test="${fn:contains (order.getOrderStatus().toString(), 'UNTREATED')}">
						<fmt:message key="showSpecifiedOrder.jsp.UNTREATED" />
					</c:when>
					<c:when
						test="${fn:contains (order.getOrderStatus().toString(), 'ACTIVE')}">
						<fmt:message key="showSpecifiedOrder.jsp.ACTIVE" />
					</c:when>
					<c:when
						test="${fn:contains (order.getOrderStatus().toString(), 'CLOSE')}">
						<fmt:message key="showSpecifiedOrder.jsp.CLOSE" />
					</c:when>
					<c:when
						test="${fn:contains (order.getOrderStatus().toString(), 'REJECTED')}">
						<fmt:message key="showSpecifiedOrder.jsp.REJECTED" />
					</c:when>

				</c:choose></td>
		</tr>
	</table>
</div>