<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>


<fmt:message key="clientRegistration.jsp.mainBlockName" />
<form id="registerManagerForm" action="Controller" method="POST" onsubmit="registerNewManagerForm()">
	<input type = "hidden" name = "userLanguage" value = "${language}">
					
	<div class="registrationForm_left">
		<div class="field">
			<label>1. <fmt:message key="clientRegistration.jsp.userPassSeries" /></label>
			<input type="text" name="userPassSeries" size=20 />
		</div>	
											
		<div class="field">
			<label>2. <fmt:message key="clientRegistration.jsp.userPassNumber" /> </label> 
			<input type="text" name="userPassNumber" size=20 />
		</div>
							
		<div class="field">
			<label>3. <fmt:message key="clientRegistration.jsp.userPassSurname" /></label> 
			<input type="text" name="userPassSurname" size=20 />
		</div>
							
		<div class="field">
			<label>4. <fmt:message key="clientRegistration.jsp.userPassName" /></label> 
			<input type="text" name="userPassName" size=20 />
		</div>
							
		<div class="field">
			<label>5. <fmt:message key="clientRegistration.jsp.userPassPatronomic" /> </label>
			<input type="text" name="userPassPatronomic" size=20 />
		</div>
						
	</div>
	<div class="registrationForm_right">
		<div class="field">
			<label>6. <fmt:message key="clientRegistration.jsp.userPassDateOfBirth" /> </label> 
			<input type="date" name="userPassDateOfBirth" />
		</div>
						
		<div class="field">
			<label>7. <fmt:message key="clientRegistration.jsp.userSex" /> </label> 
			<select name = "userSex">
				<option > Choose sex </option>
				<option value = "MALE">MALE</option>
				<option value = "FEMALE">FEMALE</option>
			</select>
		</div>
						
		<div class="field">
			<label>8. <fmt:message key="clientRegistration.jsp.userEmail" /> </label> 
			<input type="text" name="userEmail" size=20 />
		</div>
	
		<div class="field">
			<label>9. <fmt:message key="clientRegistration.jsp.userPassword" /> </label> 
			<input type="password" name="userPassword" size=20 />
			</div> 
		</div>					
		<input id = "signUp" type="submit" value="<fmt:message key="clientRegistration.jsp.signUp" />">					
</form>
