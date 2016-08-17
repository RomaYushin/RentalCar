<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>clientPersonalArea</title>	
		<!-- <link href = "css/welcomeAuthorizationStyle.css" type = "text/css" rel = "stylesheet" /> -->
		
		<script src = "js/jquery-3.1.0.js" type = "text/javascript"></script>
		<!-- <script src = "js/jquery-3.1.0.min.js" type = "text/javascript"></script>  -->
		<script src = "js/clientPersonalAreaScript.js" type = "text/javascript"></script>	
	</head>
	<body>
		<div class = "wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class = "mainContent" >
				<!--<fmt:message key="welcomeAuthorization.jsp.mainBlockName" /> -->
				<h1>Client personal Area</h1>
				<div class = "mainButtons">
					<!-- ajax submin in js file -->
					<button id = "orderCarButton" > Order Car </button>
					<button id = "checkOrderStatus" > Check order status </button>
				</div>
				
				<div class = "mainWindow">					
				</div>								
			</div>
		</div>
		
	</body>
</html>