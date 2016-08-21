<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>adminPersonalArea</title>	
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
				<h1>Admin personal Area</h1>
				<div class = "mainButtons">
					<!-- ajax submin in js file -->
					<button id = "addNewCarButton" onclick ="showAddNewCarForm()"> Add new car </button>
					<!-- <button id = "removeCarButton" onclick ="showRemoveCarForm()" > Remove car </button>
					<button id = "editCarButton" onclick ="showEditCarForm()" > Edit car </button> -->
					<button id = "blockingUser" onclick ="showAllCarsForm()" > Editing (removal) cars </button>
					<button id = "blockingUser" onclick ="showBlockUserForm()" > Blocking/Unblocking user </button>
					<button id = "registerManager" onclick ="showRegisterManagerForm()" > Register new manager </button>
				</div>	
				<div class = "mainWindow">
					<!-- 
					<c:if test="${not empty messageForAdmin}">
						<c:out value="${messageForAdmin}"></c:out>
					</c:if>
					-->
				</div>			
				
			</div>
		</div>
	</body>
</html>