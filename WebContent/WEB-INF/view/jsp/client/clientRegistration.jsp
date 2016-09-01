<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>clientSignUp</title>
		<link href = "css/userRegistrationStyle.css" type = "text/css" rel = "stylesheet" />
		<script src = "js/jquery-3.1.0.js" type = "text/javascript"></script>
		<script src = "js/userRegistrationScript.js" type = "text/javascript"></script>
		<!--  <link href = "css/userRegistrationStyle.css" type = "text/css" rel = "stylesheet" /> -->
		<!-- <script src="js/jquery.js" type="text/javascript"></script> -->
		<!-- <script src="js/authorizationScripts.js" type="text/javascript"></script> -->
	</head>
	<body class="clientRegistrationBody">
		<div class="wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class="mainContent">
				<div class="mainBlockName">
					<fmt:message key="clientRegistration.jsp.mainBlockName" />
				</div>
				<form id="registrationForm" action="Controller" method="POST">
					<input type = "hidden" name = "command" value = "clientRegistration" />
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
								<option value = "MALE">MALE</option>
								<option value = "FEMALE">FEMALE</option>
							</select>
							<!--  <input type="text" name="userSex" size=20 /> -->
						</div>
						
						<div class="field">
							<label>8. <fmt:message key="clientRegistration.jsp.userEmail" /> </label> 
							<input type="text" name="userEmail" size=20 />
						</div>
	
						<div class="field">
							<label>9. <fmt:message key="clientRegistration.jsp.userPassword" /> </label> 
							<input type="password" name="userPassword" size=20 />
						</div> 
						
						<div class="field">
							<label>10. <fmt:message key="clientRegistration.jsp.userPassword2" /> </label> 
							<input type="password" name="userPassword2" size=20 />
						</div> 
					</div>					
					<input id = "signUp" type="submit" value="<fmt:message key="clientRegistration.jsp.signUp" />">					
				</form>
				<div class = "exitForm">
					<form action="Controller" method="POST">
						<input type = "hidden" name = "command" value = "logout" />
						<input id = "exit" type="submit" value="<fmt:message key="clientRegistration.jsp.Exit" />">
					</form>
				</div>
			</div>
	
			<%@ include file = "/WEB-INF/view/jspf/footer.jspf" %>
		</div>
	</body>
</html>