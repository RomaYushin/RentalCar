<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customTagsLibrary.tld" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<div class="availableCarsInner">
	<ct:printAvailableCars availableCars_map = "${availableCars_map}" />
</div>
