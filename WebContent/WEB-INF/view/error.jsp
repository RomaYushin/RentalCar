<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> ERROR </title>
	<!--<link href = "css/errorStyle.css" type = "text/css" rel = "stylesheet" /> -->
	
	<script src="js/jquery-3.1.0.js" type="text/javascript"></script>
	<!-- <script src = "js/jquery-3.1.0.min.js" type = "text/javascript"></script>  	-->
	<script src = "js/errorScript.js" type = "text/javascript"></script>
	</head>
	<body>
		<div class="wrapper">
			<div class = "mainContent">
				<div class = "mainContentName">
					ERROR!
				</div>
				<div class = "message">
					ERROR MESSAGE:
					
				<c:if test="${empty errorMessage}">
					no error message
				</c:if>
				
				<c:if test="${not empty errorMessage}">
					${errorMessage}
				</c:if>
				</div>	
			</div>					
		</div>
	</body>
</html>