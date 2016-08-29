<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.sql.Date"%>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Order"%>
<%@page import="ua.nure.yushin.SummaryTask4.entity.Car"%>

			<div class="createOrderSuccess">
				<table border="1" cellpadding="4" width="50%">
				<!-- <caption> caption </caption> -->
				<tr>
					<th colspan="2">  <fmt:message key="showSpecifiedOrder.jsp.mainBlockName" />  </th>
				</tr>
				<tr>
					<td>1. <fmt:message key="showSpecifiedOrder.jsp.orderId_lbl" />: </td>
					<td> ${order.getId()} </td>
				</tr>
				<tr>
					<td>2. <fmt:message key="showSpecifiedOrder.jsp.accountId_lbl" />: </td>
					<td> ${order.getOrderAccount().getId()} </td>
				</tr>				
				<tr>
					<td>3. <fmt:message key="showSpecifiedOrder.jsp.orderCreationDate_lbl" />: </td>
					<td> ${order.getCreateOrderDate()} </td>
				</tr>
				<tr>
					<td>4. <fmt:message key="showSpecifiedOrder.jsp.car_lbl" />:  </td>
					<%
						Order newOrder = (Order) request.getAttribute("order");
						GregorianCalendar gc = new GregorianCalendar();
						gc.setTime(newOrder.getOrderCar().getCarYearOfIssue());
					%>
					<td> ${order.getOrderCar().getCarBrend()}  ${order.getOrderCar().getCarModel()} <%=gc.get(Calendar.YEAR)%></td>
				</tr>
				<tr>
					<td>5. <fmt:message key="showSpecifiedOrder.jsp.startDate_lbl" />: </td>
					<td> ${order.getOrderStartDate()}  </td>
				</tr>
				<tr>
					<td>6. <fmt:message key="showSpecifiedOrder.jsp.endDate_lbl" />: </td>
					<td> ${order.getOrderEndDate()} </td>
				</tr>
				<tr>
					<td>7. <fmt:message key="showSpecifiedOrder.jsp.driver_lbl" />: </td>
					<td> 
						<c:if test="${order.isOrderPresenceOfTheDriver()}"> <fmt:message key="showSpecifiedOrder.jsp.yes_lbl" /> </c:if>
						<c:if test="${not order.isOrderPresenceOfTheDriver()}"> <fmt:message key="showSpecifiedOrder.jsp.no_lbl" /> </c:if>
					</td>
				
				<tr>
					<td>8. <fmt:message key="showSpecifiedOrder.jsp.accountForRent_lbl" />: </td>
					<td> ${order.getOrderAccount().getAccountForRent()} </td>
				</tr>
				<tr>
					<td>9. <fmt:message key="showSpecifiedOrder.jsp.accountForRepair_lbl" />: </td>
					<td> ${order.getOrderAccount().getAccountForRepair()} </td>
				</tr>
				<tr>
					<td>10. <fmt:message key="showSpecifiedOrder.jsp.accountRentPaid_lbl" />: </td>
					<td>
						<c:if test="${order.getOrderAccount().isAccountRentPaid()}"><fmt:message key="managerPersonalArea.jsp.orderPaymentYes" /></c:if>
						<c:if test="${not order.getOrderAccount().isAccountRentPaid()}"> <fmt:message key="managerPersonalArea.jsp.orderPaymentNo" /></c:if>
					 </td>
				</tr>
				<tr>
					<td>11. <fmt:message key="showSpecifiedOrder.jsp.accountRepairPaid_lbl" />: </td>
					<td>
						<c:if test="${order.getOrderAccount().isAccountRepairPaid()}"><fmt:message key="managerPersonalArea.jsp.orderPaymentYes" /></c:if>
						<c:if test="${not order.getOrderAccount().isAccountRepairPaid()}"> <fmt:message key="managerPersonalArea.jsp.orderPaymentNo" /></c:if>
					 </td>
				</tr>
				<tr>
					<td>12. <fmt:message key="showSpecifiedOrder.jsp.orderStatus_lbl" />: </td>
					<td>
						<c:choose>
							<c:when test="${fn:contains (order.getOrderStatus().toString(), 'UNTREATED')}">
								<fmt:message key="showSpecifiedOrder.jsp.UNTREATED" />
							</c:when>
							<c:when test="${fn:contains (order.getOrderStatus().toString(), 'ACTIVE')}">
								<fmt:message key="showSpecifiedOrder.jsp.ACTIVE" />
							</c:when>
							<c:when test="${fn:contains (order.getOrderStatus().toString(), 'CLOSE')}">
								<fmt:message key="showSpecifiedOrder.jsp.CLOSE" />
							</c:when>
							<c:when test="${fn:contains (order.getOrderStatus().toString(), 'REJECTED')}">
								<fmt:message key="showSpecifiedOrder.jsp.REJECTED" />
							</c:when>
						</c:choose>
					 </td>
				</tr>
				<tr>
					<td>13. <fmt:message key="showSpecifiedOrder.jsp.rejectReason_lbl" />: </td>
					<td> ${order.getOrderRejectionReason()} </td>
				</tr>
				
				<c:if test="${fn:contains (order.getOrderStatus().toString(), 'CLOSE')}">
					<tr>
						<td>14. <fmt:message key="showSpecifiedOrder.jsp.managerWhoClosedOrder" />: </td>
						<td> ${order.getManagerNameWhoClosedOrder()} </td>
					</tr>
				</c:if>				
								
			</table>
	
		<br>
		<c:if test="${ not order.getOrderAccount().isAccountRentPaid()}">
			<div id = "rentPayment">
				<p> <fmt:message key="cliPerArea.jsp.payment" /> ( ${order.getOrderAccount().getAccountForRent()} )</p>
				<form action="Controller" method = "POST">
					<input type = "hidden" name = "command" value = "payOrder"/>
					<input type = "hidden" name = "orderId" value = "${order.getId()}"/>
					<input type = "number" name = "rentPayment" />	
					<input type = "submit" value = "<fmt:message key="cliPerArea.jsp.payOrder" />" />
				</form>
			</div>			
		</c:if>
		
		<c:if test="${ not order.getOrderAccount().isAccountRepairPaid()}">
			<div id = "repairPayment">
				<p> <fmt:message key="cliPerArea.jsp.payment" /> ( ${order.getOrderAccount().getAccountForRepair()} )</p>
				<form action="Controller" method = "POST">
					<input type = "hidden" name = "command" value = "payRepair"/>
					<input type = "hidden" name = "orderId" value = "${order.getId()}"/>
					<input type = "number" name = "repairPayment" />
					<input type = "submit" value = " <fmt:message key="cliPerArea.jsp.payRepair" />" />
				</form>
			</div>
		</c:if>
		
		
</div>