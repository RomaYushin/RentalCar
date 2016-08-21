<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>welcomeAuthorization</title>	
		<link href = "css/welcomeAuthorizationStyle.css" type = "text/css" rel = "stylesheet" /> 
		<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> -->
		<script src = "js/jquery-3.1.0.js" type = "text/javascript"></script>
		<!-- <script src = "js/jquery-3.1.0.min.js" type = "text/javascript"></script>  -->
		<script src = "js/welcomeAuthorizationScript.js" type = "text/javascript"></script>
	</head>
	<body>
		<div class="wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class = "mainContent" >
				<fmt:message key="welcomeAuthorization.jsp.mainBlockName" />
				<form id = "authorizationForm" action="Controller" method="POST" >
					<input type = "hidden" name = "command" value = "userAuthorization">
				
					<label><fmt:message key="welcomeAuthorization.jsp.email" /></label>
					<input id = "login" type = "text" name = "userEmail" size = 30; onblur = "validateEmail(this.value)"><br>
					
					<label><fmt:message key="welcomeAuthorization.jsp.password" /></label>
					<input id = "password" type = "password" name = "userPassword" size = 30; onblur = "validatePassword(this.value.length)"><br>
					
					<input id = "signIn" type = "submit" value = "<fmt:message key="welcomeAuthorization.jsp.signIn" />" >									
				</form>
				
				<form action="Controller" method = "GET">
					<input type = "hidden" name = "command" value = "clientRegistration">
					<button id = "register"><fmt:message key="welcomeAuthorization.jsp.signUp" /></button>
				</form>
			</div>
			<%@ include file = "/WEB-INF/view/jspf/footer.jspf" %>
			
		</div>
	</body>
	
</html>