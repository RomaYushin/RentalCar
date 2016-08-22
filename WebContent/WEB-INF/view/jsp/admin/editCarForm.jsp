<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<%@page import="ua.nure.yushin.SummaryTask4.entity.CarQualityClass" %>
<%@page import="ua.nure.yushin.SummaryTask4.entity.CarStatus" %>


Edit car form

	<div class="existingCarParam_left">
		<br>
		Existing car parametrs:
		<div class="field">
			<label>1. Brend : </label> 
			<c:out value="${ carForEdit.getCarBrend() }"></c:out>
		</div>

		<div class="field">
			<label>2. Model: </label> 
			<c:out value="${ carForEdit.getCarModel() }"></c:out>
		</div>

		<div class="field">
			<label>3. Year of issue: </label> 
			<c:out value="${ carForEdit.getCarYearOfIssue() }"></c:out>
		</div>

		<div class="field">
			<label>4. Quality class: </label> 
			<c:out value="${ carForEdit.getCarQualityClass() }"></c:out>
		</div>

		<div class="field">
			<label>5. Rental cost: </label>
			<c:out value="${ carForEdit.getCarRentalCost() }"></c:out>
		</div>
		<div class="field">
			<label>6. Car status: </label>
			<c:out value="${ carForEdit.getCarStatus() }"></c:out>
		</div>
	</div>	
	
	<div class="newCarParam_right">
		<br>
		New car parametrs:
		<input type = "hidden" id = "carId" value = "<c:out value="${ carForEdit.getId() }"></c:out>" />
		<div class="field">
			<label>1. New brend: </label>
			<input type="text" name="newCarBrend" size=20 />			
		</div>

		<div class="field">
			<label>2. New model: </label> 
			<input type="text" name="newCarModel" size=20 />
		</div>

		<div class="field">
			<label>3. New year of issue:</label>
			<input type="date" name="newCarYearOfIssue" />
		</div>

		<div class="field">
			<label>4. New quality class </label>
			<select id="newCarQualityClass">
				<option> New car quality class </option>
				<%	for (CarQualityClass c: CarQualityClass.values()) { %>
					<option value="<%= c.toString() %>" > <%= c.toString() %> </option>
				<% } %>	
			</select>
		</div>
		
		<div class="field">
			<label>5. New rental cost:</label>
			<input type="text" name="newCarRentalCost" />
		</div>
		
		<div class="field">
			<label>6. New staus:</label>
			<select id="newCarStatus">
				<option> New car status </option>
				<%	for (CarStatus s: CarStatus.values()) { %>
					<option value="<%= s.toString() %>" > <%= s.toString() %> </option>
				<% } %>
			</select>
		</div>
		<button onclick = "saveNewCarParametrs()"> Save new car parametrs </button>
	</div>


