<%@ include file = "/WEB-INF/view/jspf/pageDirective.jspf" %>
<%@ include file = "/WEB-INF/view/jspf/taglibDirective.jspf" %>

<div class = "selectDate">
	1. <fmt:message key="cliPerArea.jsp.selectOrderDate" />: 	
	<label> <fmt:message key="cliPerArea.jsp.startRent_lbl" />: </label>
	<input id = "orderStartDate" type = "date" name = "orderStartDate" onchange = "submitDates()"/>
					
	<label> <fmt:message key="cliPerArea.jsp.endRent_lbl" />: </label>
	<input id = "orderEndDate" type = "date" name = "orderEndDate" onchange = "submitDates()"/>
</div>

<div class = "availableCars">
</div>

