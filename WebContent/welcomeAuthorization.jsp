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
	
		<c:if test="${not empty sessionScope.user }">
			<c:redirect url ="Controller?command=clientPersonalArea"></c:redirect>
		</c:if>
		<div class="wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class = "mainContent" >			
				<div class="mainBlockName">
					<fmt:message key="welcomeAuthorization.jsp.mainBlockName" />
				</div>
				<form id = "authorizationForm" action="Controller" method="POST" >
					<input type = "hidden" name = "command" value = "userAuthorization">
					<input type = "hidden" id = "language" value ="${language}">
				
					<label><fmt:message key="welcomeAuthorization.jsp.email" /></label>
					<input id = "login" type = "text" name = "userEmail" size = 30 
					onkeyup ="validateEmail(this.value)" onclick ="validateEmail(this.value)">
					<span id="validEmail"></span><br>
					
					<label><fmt:message key="welcomeAuthorization.jsp.password" /></label>
					<input id = "password" type = "password" name = "userPassword" size = 30; 
						onkeyup = "validatePassword(this.value.length)" onchange = "validatePassword(this.value.length)">
					<span id="validPassword"></span><br>
					
					<div class="signInBlock">
						<input id = "signIn" type = "submit" value = "<fmt:message key="welcomeAuthorization.jsp.signIn" />">								
					</div>
				</form>
				
				<form class="registerForm" action="Controller" method = "GET">
					<input type = "hidden" name = "command" value = "clientRegistration">
					<button id = "register"><fmt:message key="welcomeAuthorization.jsp.signUp" /></button>
				</form>
				
				
			</div>
			
			<c:if test="${not empty responseMessage }">
				<div class = "responseMessageBlock">
					<fmt:message key="${ responseMessage }" />
				</div>
			</c:if>
			<%@ include file = "/WEB-INF/view/jspf/footer.jspf" %>			
		</div>
	</body>
	
</html>