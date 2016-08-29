<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> <fmt:message key="adminPerArea.jsp.title" /> </title>	
		<!-- <link href = "css/welcomeAuthorizationStyle.css" type = "text/css" rel = "stylesheet" /> -->		
		<script src = "js/jquery-3.1.0.js" type = "text/javascript"></script>
		<!-- <script src = "js/jquery-3.1.0.min.js" type = "text/javascript"></script>  -->
		<script src = "js/adminPersonalAreaScript.js" type = "text/javascript"></script>
	</head>
	<body>
		<div class = "wrapper">
			<%@ include file = "/WEB-INF/view/jspf/header.jspf" %>
			<div class = "mainContent" >
				<!--<fmt:message key="welcomeAuthorization.jsp.mainBlockName" /> -->
				<h1> <fmt:message key="adminPerArea.jsp.mainContent" /> </h1>
				<div class = "mainButtons">
					<!-- ajax submin in js file -->
					<button id = "addNewCarButton" onclick ="showAddNewCarForm()"> <fmt:message key="adminPerArea.jsp.addNewCar_btn" /> </button>
					<button id = "blockingUser" onclick ="showAllCarsForm()" > <fmt:message key="adminPerArea.jsp.editRemoveCar_btn" /> </button>
					<button id = "blockingUser" onclick ="showBlockUserForm()" > <fmt:message key="adminPerArea.jsp.blockUnblockUser_btn" /> </button>
					<button id = "registerManager" onclick ="showRegisterManagerForm()" > <fmt:message key="adminPerArea.jsp.registerNewManager_btn" /> </button>
				</div>	
				<div class = "mainWindow">
					<c:if test="${not empty responseMessage }">
						<c:out value="${ responseMessage }"></c:out>
					</c:if>
				</div>					
			</div>
		</div>
	</body>
</html>