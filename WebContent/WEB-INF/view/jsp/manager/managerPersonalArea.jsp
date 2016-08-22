<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>managerPersonalArea</title>	
		<!-- <link href = "css/welcomeAuthorizationStyle.css" type = "text/css" rel = "stylesheet" /> -->		
		<script src = "js/jquery-3.1.0.js" type = "text/javascript"></script>
		<!-- <script src = "js/jquery-3.1.0.min.js" type = "text/javascript"></script>  -->
		<script src = "js/managerPersonalAreaScript.js" type = "text/javascript"></script>
	</head>
	<body>
		<div class = "wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class = "mainContent" >
				<!--<fmt:message key="welcomeAuthorization.jsp.mainBlockName" /> -->
				<h1> Manager personal Area</h1>
				<div class = "mainButtons">
					<!-- ajax submin in js file -->
					<button id = "showUntreatedOrders_btn" onclick ="showUntreatedOrders()"> Untreated orders </button>
					<button id = "showActiveOrders_btn" onclick ="showActiveOrders()"> Active orders </button>
					<button id = "showClosedOrders_btn" onclick ="showClosedOrders()"> Closed orders </button>
					<button id = "showRejectedOrders_btn" onclick ="showRejectedOrders()"> Rejected orders </button>
					<button id = "showAllOrders_btn" onclick ="showAllOrders()"> All orders </button>
					
				</div>	
				<div class = "mainWindow">
					
				</div>			
				
			</div>
		</div>
	</body>
</html>