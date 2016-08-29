<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<%@page import="ua.nure.yushin.SummaryTask4.entity.CarQualityClass" %>
<%@page import="ua.nure.yushin.SummaryTask4.entity.CarStatus" %>

<br>
<fmt:message key="adminPerArea.jsp.editCarForm" />
	<div class="existingCarParam_left">
		<br>
		<fmt:message key="adminPerArea.jsp.existingCarParametrs" />
		<div class="field">
			<label>1. <fmt:message key="adminPerArea.jsp.exCarBrend" />: </label> 
			<c:out value="${ carForEdit.getCarBrend() }"></c:out>
		</div>

		<div class="field">
			<label>2. <fmt:message key="adminPerArea.jsp.exCarModel" />: </label> 
			<c:out value="${ carForEdit.getCarModel() }"></c:out>
		</div>

		<div class="field">
			<label>3. <fmt:message key="adminPerArea.jsp.exCarYearOfIssue" />: </label> 
			<c:out value="${ carForEdit.getCarYearOfIssue() }"></c:out>
		</div>

		<div class="field">
			<label>4. <fmt:message key="adminPerArea.jsp.exQualityClass" />: </label> 
			<c:out value="${ carForEdit.getCarQualityClass() }"></c:out>
		</div>

		<div class="field">
			<label>5. <fmt:message key="adminPerArea.jsp.exRentalCost" />: </label>
			<c:out value="${ carForEdit.getCarRentalCost() }"></c:out>
		</div>
		<div class="field">
			<label>6. <fmt:message key="adminPerArea.jsp.exCarStatus" />: </label>
			<c:out value="${ carForEdit.getCarStatus() }"></c:out>
		</div>
	</div>	
	
	<div class="newCarParam_right">
		<br>
		<form action = "Controller" method = "POST" >
			<fmt:message key="adminPerArea.jsp.NewCarParametrs" />
			<input type="hidden" name="command" value = "editCar" />
			<input type = "hidden" id = "carId" name = "carId" 
				value = "<c:out value="${ carForEdit.getId() }"></c:out>" />
			<div class="field">
				<label>1. <fmt:message key="adminPerArea.jsp.newCarBrend" />: </label>
				<input type="text" name="newCarBrend" size=20  autofocus value ="${ carForEdit.getCarBrend() }"/>			
			</div>
	
			<div class="field">
				<label>2. <fmt:message key="adminPerArea.jsp.newCarModel" />: </label> 
				<input type="text" name="newCarModel" size=20 value ="${ carForEdit.getCarModel() }" />
			</div>
	
			<div class="field">
				<label>3. <fmt:message key="adminPerArea.jsp.newCarYearOfIssue" />:</label>
				<input type="date" name="newCarYearOfIssue" value ="${ carForEdit.getCarYearOfIssue() }" />
			</div>
	
			<div class="field">
				<label>4. <fmt:message key="adminPerArea.jsp.newQualityClass" /> </label>
				<select id="newCarQualityClass" name = "newCarQualityClass">
					<option> <fmt:message key="adminPerArea.jsp.chooseNewQualityClass" /> </option>
					<%	for (CarQualityClass c: CarQualityClass.values()) { %>
						<option value="<%= c.toString() %>" > <%= c.toString() %> </option>
					<% } %>	
				</select>
			</div>
			
			<div class="field">
				<label>5. <fmt:message key="adminPerArea.jsp.newRentalCost" />:</label>
				<input type="text" name="newCarRentalCost" value ="${ carForEdit.getCarRentalCost() }" />
			</div>
			
			<div class="field">
				<label>6. <fmt:message key="adminPerArea.jsp.newCarStatus" />:</label>
				<select id="newCarStatus" name = "newCarStatus" >
					<option> <fmt:message key="adminPerArea.jsp.chooseNewCarStatus" /> </option>
					<%	for (CarStatus s: CarStatus.values()) { %>
						<option value="<%= s.toString() %>" > <%= s.toString() %> </option>
					<% } %>
				</select>
			</div>
			<!-- <button onclick = "saveNewCarParametrs()"> <fmt:message key="adminPerArea.jsp.saveParam_btn" /> </button>  -->
			<input type = "submit" value = " <fmt:message key="adminPerArea.jsp.saveParam_btn" />"/>
		</form>
	</div>


