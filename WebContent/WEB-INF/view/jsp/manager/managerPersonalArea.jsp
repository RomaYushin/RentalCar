<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> <fmt:message key="managerPersonalArea.jsp.title" />	 </title>
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
				<h1> <fmt:message key="managerPersonalArea.jsp.mainBlockName" /> </h1>
				<div class = "mainButtons">
					<!-- ajax submin in js file -->
					<button id = "showUntreatedOrders_btn" onclick ="showUntreatedOrders()">
						<fmt:message key="managerPersonalArea.jsp.untreatedOrders_btn" /> </button>
						
					<button id = "showActiveOrders_btn" onclick ="showActiveOrders()"> 
						<fmt:message key="managerPersonalArea.jsp.activeOrders_btn" /> </button>
						
					<button id = "showClosedOrders_btn" onclick ="showClosedOrders()"> 
						<fmt:message key="managerPersonalArea.jsp.closedOrders_btn" /> </button>
						
					<button id = "showRejectedOrders_btn" onclick ="showRejectedOrders()"> 
						<fmt:message key="managerPersonalArea.jsp.rejectedOrders_btn" /> </button>
						
					<button id = "showAllOrders_btn" onclick ="showAllOrders()">
						 <fmt:message key="managerPersonalArea.jsp.allOrders_btn" /> </button>
				</div>	
				<div class = "mainWindow">
					
				</div>			
				<div class = "mainWindow2">
					
				</div>
				
			</div>
		</div>
	</body>
</html>