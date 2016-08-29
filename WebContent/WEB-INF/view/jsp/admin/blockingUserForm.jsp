<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customTagsLibrary.tld" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<ct:printAllUsers allUsers = "${allUsers}" />