<%@ include file="/WEB-INF/view/jspf/pageDirective.jspf"%>
<%@ include file="/WEB-INF/view/jspf/taglibDirective.jspf"%>


<param id ="totalPrice" value = " <%= request.getAttribute("totalPrice") %>">
4. <fmt:message key="cliPerArea.jsp.totalPrice" />:  <%= request.getAttribute("totalPrice") %>


