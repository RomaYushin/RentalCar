<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>welcomeAuthorization</title>	
		<link href = "css/welcomeAuthorizationStyle.css" type = "text/css" rel = "stylesheet" />
		<script src = "js/jquery.js" type = "text/javascript"></script>	
		<script src = "js/authorizationScripts.js" type = "text/javascript"></script>	
	</head>
	<body>
		<div class="wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class = "mainContent" >
				<fmt:message key="authorization.jsp.mainBlockName" />
				<form id = "authorizationForm" action="ControllerServlet" method="POST">
					<input type = "hidden" name = "command" value = "edit ">
				
					<label for = "login"><fmt:message key="authorization.jsp.login" /></label>
					<input id = "login" type = "text" name = "login" size = 100;><br>
					
					<label for = "password"><fmt:message key="authorization.jsp.password" /></label>
					<input id = "password" type = "text" name = "password" size = 100;><br>
					
					<input id = "signIn" type = "submit" value = "<fmt:message key="authorization.jsp.signIn" />">	
				</form>
				
				<form id = "registerForm" action="controller" method="POST">
					<input id = "register" type = "submit" value = "<fmt:message key="authorization.jsp.signUp" />">	
				</form>
			</div>
		</div>
	</body>
</html>