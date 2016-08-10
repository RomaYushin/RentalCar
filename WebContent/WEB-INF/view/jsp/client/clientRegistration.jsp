<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>clientSignUp</title>
<link href="css/welcomeAuthorizationStyle.css" type="text/css"
	rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/authorizationScripts.js" type="text/javascript"></script>
</head>
<body>
	<div class="wrapper">
		<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
		<div class="mainContent">
			<fmt:message key="clientRegistration.jsp.mainBlockName" />
			<form id="registrationForm" action="ControllerServlet" method="POST">
				<div class="registrationForm_left">
					<div class="field">
						<label>1. Pass Series </label><input id="passSeries" type="text"
							name="userPassSeries" size=20 />
					</div>
					<div class="field">
						<label>2. Pass Number </label> <input id="passNumber" type="text"
							name="userPassNumber" size=20 />
					</div>
					<div class="field">
						<label>3. Surname </label> <input id="surname" type="text"
							name="userPassSurname" size=20 />
					</div>
					<div class="field">
						<label>4. Name </label> <input id="name" type="text"
							name="userPassName" size=20 />
					</div>
					<div class="field">
						<label>5. Patronomic </label> <input id="patronomic" type="text"
							name="userPassPatronomic" size=20 />
					</div>
				</div>
				<div class="registrationForm_right">
					<div class="field">
						<label>6. Date of birth </label> <input id="dateOfBirth"
							type="text" name="userPassDateOfBirth" size=20 />
					</div>
					<div class="field">
						<label>7. Sex </label> <input id="sex" type="text" name="userSex"
							size=20 />
					</div>
					<div class="field">
						<label>8. Email </label> <input id="email" type="text"
							name="userEmail" size=20 />
					</div>
					<div class="field">
						<label>9. Login </label> <input id="login" type="text"
							name="userLogin" size=20 />
					</div>
					<div class="field">
						<label>10. Password </label> <input id="password" type="text"
							name="userPassword" size=20 />
					</div>
				</div>
				<input id="signUp" type="submit" value="Sign Up"> <input
					id="exit" type="submit" value="Exit">
			</form>
		</div>

		<div class="footer">
			<p>&copy;Car Rental (SummaryTask4, EPAM-KNURE Java Training),
				2016</p>
		</div>
	</div>
</body>
</html>