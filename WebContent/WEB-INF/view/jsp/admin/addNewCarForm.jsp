<%@page import="ua.nure.yushin.SummaryTask4.entity.CarQualityClass" %>
<%@page import="ua.nure.yushin.SummaryTask4.entity.CarStatus" %>

<form id="addNewCarForm" action="Controller" method="POST">
	<input type="hidden" name="command" value="registerNewCar" /> 

	<div class="field">
		<label>1. Car brend: </label> 
		<input type="text" name="carBrend" size=20 />
	</div>

	<div class="field">
		<label>2. Car model: </label> 
		<input type="text" name="carModel" size=20 />
	</div>

	<div class="field">
		<label>3. Car year of issue: </label> 
		<input type="date" name="carYearOfIssue" size=20 />
	</div>
	
	<div class="field">
		<label>4. Car quality class: </label> 
		<select name = "carQualityClass">
			<option> choose quality class </option>
			<%	for (CarQualityClass c: CarQualityClass.values()) { %>
				<option value="<%= c.toString() %>" > <%= c.toString() %> </option>
			<% } %>	
		</select>
	</div>

	<div class="field">
		<label>5. Rental cost for one day </label>
		<input type="text" name="carRentalCost" size=20 />
	</div>

	<input id="signUp" type="submit" value="Create new car" />
</form>