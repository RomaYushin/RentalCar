<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ERROR</title>
	<!-- <link href = "css/welcomeAuthorizationStyle.css" type = "text/css" rel = "stylesheet" /> -->
	
	<script src="js/jquery-3.1.0.js" type="text/javascript"></script>
	<!-- <script src = "js/jquery-3.1.0.min.js" type = "text/javascript"></script>  
			<script src = "js/clientPersonalAreaScript.js" type = "text/javascript"></script>	-->
	</head>
	<body>
		<div class="wrapper">
			<h1>ERROR</h1>
			<c:choose>
				<c:when test="${empty errorMessage}"></c:when>
				<c:otherwise>
					<c:out value="${errorMessage}"></c:out>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>