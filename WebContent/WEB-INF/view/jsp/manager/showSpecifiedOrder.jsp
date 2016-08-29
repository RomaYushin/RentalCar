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
					<td>5. <fmt:message key="showSpecifiedOrder.jsp.client_lbl" />: </td>
					<td> 
						${order.getOrderClient().getUserPassSurname()}
						${order.getOrderClient().getUserPassName()}
						${order.getOrderClient().getUserPassPatronomic()}
					</td>
				
				</tr>
				<tr>
					<td>6. <fmt:message key="showSpecifiedOrder.jsp.startDate_lbl" />: </td>
					<td> ${order.getOrderStartDate()}  </td>
				</tr>
				<tr>
					<td>7. <fmt:message key="showSpecifiedOrder.jsp.endDate_lbl" />: </td>
					<td> ${order.getOrderEndDate()} </td>
				</tr>
				<tr>
					<td>8. <fmt:message key="showSpecifiedOrder.jsp.driver_lbl" />: </td>
					<td> 
						<c:if test="${order.isOrderPresenceOfTheDriver()}"> <fmt:message key="showSpecifiedOrder.jsp.yes_lbl" /> </c:if>
						<c:if test="${not order.isOrderPresenceOfTheDriver()}"> <fmt:message key="showSpecifiedOrder.jsp.no_lbl" /> </c:if>
					</td>
				
				<tr>
					<td>9. <fmt:message key="showSpecifiedOrder.jsp.accountForRent_lbl" />: </td>
					<td> ${order.getOrderAccount().getAccountForRent()} </td>
				</tr>
				<tr>
					<td>10. <fmt:message key="showSpecifiedOrder.jsp.accountForRepair_lbl" />: </td>
					<td> ${order.getOrderAccount().getAccountForRepair()} </td>
				</tr>
				<tr>
					<td>11. <fmt:message key="showSpecifiedOrder.jsp.accountRentPaid_lbl" />: </td>
					<td>
						<c:if test="${order.getOrderAccount().isAccountRentPaid()}"><fmt:message key="managerPersonalArea.jsp.orderPaymentYes" /></c:if>
						<c:if test="${not order.getOrderAccount().isAccountRentPaid()}"> <fmt:message key="managerPersonalArea.jsp.orderPaymentNo" /></c:if>
					 </td>
				</tr>
				<tr>
					<td>12. <fmt:message key="showSpecifiedOrder.jsp.accountRepairPaid_lbl" />: </td>
					<td>
						<c:if test="${order.getOrderAccount().isAccountRepairPaid()}"><fmt:message key="managerPersonalArea.jsp.orderPaymentYes" /></c:if>
						<c:if test="${not order.getOrderAccount().isAccountRepairPaid()}"> <fmt:message key="managerPersonalArea.jsp.orderPaymentNo" /></c:if>
					 </td>
				</tr>
				<tr>
					<td>13. <fmt:message key="showSpecifiedOrder.jsp.orderStatus_lbl" />: </td>
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
					<td>14. <fmt:message key="showSpecifiedOrder.jsp.rejectReason_lbl" />: </td>
					<td> ${order.getOrderRejectionReason()} </td>
				</tr>
				
				<c:if test="${fn:contains (order.getOrderStatus().toString(), 'CLOSE')}">
					<tr>
						<td>15. <fmt:message key="showSpecifiedOrder.jsp.managerWhoClosedOrder" />: </td>
						<td> ${order.getManagerNameWhoClosedOrder()} </td>
					</tr>
				</c:if>				
								
			</table>
			<br>
			<br>

			<div class="createOrderSuccess_buttons">
				<input id = "orderId" type = "hidden" value="${order.getId()}" />
				<input id = "userId" type = "hidden" value="${user.getId()}" />
				
				<c:if test="${order.getOrderAccount().isAccountRepairPaid() }">
					<c:choose>
						<c:when test="${fn:contains (order.getOrderStatus().toString(), 'UNTREATED')}">
							<c:if test="${order.getOrderAccount().isAccountRentPaid()}"> 
								<button onclick = "approveOrder()" ><fmt:message key="showSpecifiedOrder.jsp.approve_btn" /></button>	
							</c:if>								
							<button onclick = "showTextArea()" ><fmt:message key="showSpecifiedOrder.jsp.rejectReason_btn" /></button>			
							<button onclick = "closeOrder()" ><fmt:message key="showSpecifiedOrder.jsp.close_btn" /> </button>
						</c:when>
						<c:when test="${fn:contains (order.getOrderStatus().toString(), 'ACTIVE')}">	
							<button onclick = "showAreaForRepairPrice()" > <fmt:message key="showSpecifiedOrder.jsp.billForRepair" /> </button>			
							<button onclick = "closeOrder()" ><fmt:message key="showSpecifiedOrder.jsp.close_btn" /> </button>
						</c:when>
						<c:when test="${fn:contains (order.getOrderStatus().toString(), 'CLOSE')}">						
						</c:when>
						<c:when test="${fn:contains (order.getOrderStatus().toString(), 'REJECTED')}">
							<button onclick = "approveOrder()" ><fmt:message key="showSpecifiedOrder.jsp.approve_btn" /></button>
							<button onclick = "closeOrder()" ><fmt:message key="showSpecifiedOrder.jsp.close_btn" /> </button>	
						</c:when>
					</c:choose>
				</c:if>
				<c:if test="${not order.getOrderAccount().isAccountRepairPaid() }">
				</c:if>
			</div>
			
			<div class = "rejTextArea" >
				<textarea id = "ta" rows="5" cols="50"></textarea>
				<br>
				<button onclick = "rejectOrder()" > <fmt:message key="showSpecifiedOrder.jsp.reject_btn" /> </button>
			</div>
			
			<div class = "priceForRepair">
				<br>
				<input id = "priceForRepair" type = "number" />
				<br>
				<button onclick = "createBillForRepair()" > <fmt:message key="showSpecifiedOrder.jsp.createBill_btn" /> </button>
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

	

