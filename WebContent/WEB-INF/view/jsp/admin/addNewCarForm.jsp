<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<%@page import="ua.nure.yushin.SummaryTask4.entity.CarQualityClass" %>
<%@page import="ua.nure.yushin.SummaryTask4.entity.CarStatus" %>

<form id="addNewCarForm" action="Controller" method="POST">
	<input type="hidden" name="command" value="registerNewCar" /> 

	<div class="field">
		<label>1. <fmt:message key="adminPerArea.jsp.carBrend" />: </label> 
		<input type="text" name="carBrend" size=20 />
	</div>

	<div class="field">
		<label>2. <fmt:message key="adminPerArea.jsp.carModel" />: </label> 
		<input type="text" name="carModel" size=20 />
	</div>

	<div class="field">
		<label>3. <fmt:message key="adminPerArea.jsp.carYearOfIssue" />: </label> 
		<input type="date" name="carYearOfIssue" size=20 />
	</div>
	
	<div class="field">
		<label>4. <fmt:message key="adminPerArea.jsp.carQualityClass" />: </label> 
		<select name = "carQualityClass">
			<option> <fmt:message key="adminPerArea.jsp.chooseCarQualityClass" /> </option>
			<%	for (CarQualityClass c: CarQualityClass.values()) { %>
				<option value="<%= c.toString() %>" > <%= c.toString() %> </option>
			<% } %>	
		</select>
	</div>

	<div class="field">
		<label>5. <fmt:message key="adminPerArea.jsp.carRentalCost" />: </label>
		<input type="text" name="carRentalCost" size=20 />
	</div>

	<input id="signUp" type="submit" value="<fmt:message key="adminPerArea.jsp.addNewCar_btn" />" />
</form>