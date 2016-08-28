<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<%@page import="ua.nure.yushin.SummaryTask4.entity.Car"%>

<table id = "clientNotClosedOrders_tbl" border = "1">
	<caption> <fmt:message key="cliPerArea.jsp.myOrdTableName" /> </caption>
	<tr>
		<th> <fmt:message key="cliPerArea.jsp.myOrdConsicutiveNumber" /> </th>
		<th> <fmt:message key="cliPerArea.jsp.myOrdId" /> </th>
		<th> <fmt:message key="cliPerArea.jsp.myOrdCreationDate" /> </th>
		<th> <fmt:message key="cliPerArea.jsp.myOrdStatus" /> </th>
		<th> <fmt:message key="cliPerArea.jsp.myOrdRejectionReason" /> </th>
		<th> <fmt:message key="cliPerArea.jsp.myOrdOpen" /> </th>
	</tr>

	<% int i = 0; %>
	<c:forEach var = "order" items = "${clientNotClosedOrders}">
		<tr>
			<td> <%= ++i %> </td>
			<td> <c:out value="${ order.getId() }"></c:out> </td>
			<td> <c:out value="${ order.getCreateOrderDate() }"></c:out> </td>
			<td> 			
				<c:choose>
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
	
				</c:choose>
			</td>
			<td> <c:out value="${ order.getOrderRejectionReason().toString() }"></c:out> </td>
			<td> <button onclick ="openOrder(${ order.getId() });"> <fmt:message key="cliPerArea.jsp.myOrdOpen" /> </button> </td>
		</tr>
	</c:forEach>
</table>

