<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>clientSignUp</title>
<link href="css/confirmRegistrationViewStyle.css" type="text/css"
	rel="stylesheet" />
<!-- <script src="js/jquery.js" type="text/javascript"></script> -->
<!-- <script src="js/authorizationScripts.js" type="text/javascript"></script> -->
</head>
<body>
	<div class="wrapper">
		<%@ include file="/WEB-INF/view/jspf/header.jspf"%>
		<div class="mainContent">
			<fmt:message key="confirmRegistrationView.jsp.mainBlockName" />
			<form id="confirmationForm" action="Controller" method="POST">
				<input type="hidden" name="command" value="confirmRegistration" />
				<input type="hidden" name="userLanguage" value="${language}">
				<input type="hidden" name="userEmail" value="${userEmail}">
				<div class="field">					
					<label><fmt:message key="confirmRegistrationView.jsp.enterPassword" />:</label> 
					<input type="password" name="userPassword" size=20 />
				</div>
				<input id = "submitConfirmationForm" type="submit" value = "<fmt:message key="confirmRegistrationView.jsp.submit" />">				
			</form>
		</div>
		<%@ include file="/WEB-INF/view/jspf/footer.jspf"%>
	</div>
</body>
</html>