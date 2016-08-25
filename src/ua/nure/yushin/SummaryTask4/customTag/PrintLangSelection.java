//package ua.nure.yushin.SummaryTask4.customTag;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.SimpleTagSupport;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import org.apache.log4j.Logger;
//
//import ua.nure.yushin.SummaryTask4.controller.Path;
//import ua.nure.yushin.SummaryTask4.entity.User;
//
//public class PrintLangSelection extends TagSupport {
//	
//	private static final Logger LOG = Logger.getLogger(PrintLangSelection.class);
//
//	private String command;
//
//	public String getCommand() {
//		return command;
//	}
//
//	public void setCommand(String command) {
//		this.command = command;
//	}
//
//	
//	
//	@Override
//	public int doStartTag() throws JspException {
//		JspWriter out = pageContext.getOut();
//		HttpSession session = pageContext.getSession();
//		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
//		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
//		
//		User user = (User) session.getAttribute("user");
//		LOG.info ("user: " + user);
//		
//		if (user == null) {
//			String language = request.getParameter("language");
//			LOG.info ("language: " + language);
//			
//			session.setAttribute("userLanguage", language);
//			
//			if(command != null) {
//				out.println("<form action=\"Controller\" method=\"GET\">"+
//				"<input type=\"hidden\" name=\"command\" value=\"editLanguage\">" + 
//				"<input type=\"hidden\" name = \"currentCommand\" value=\"" + command + "\"></input>" +
//				"<select	id=\"selectLanguage\" name=\"language\" onchange=\"this.form.submit()\">"
//					"<option ><fmt:message key="header.jspf.chooseLanguge" /></option>
//					<option value="ru"><fmt:message	key="header.jspf.ru_language" /></option>
//					<option value="en"><fmt:message	key="header.jspf.en_language" /></option>
//				</select>
//			</form>
//			} else {
//				
//				return SKIP_BODY;
//			}
//	}
//		
//		
//		return SKIP_BODY;
//	}
//
//		
//
//}
