package ua.nure.yushin.SummaryTask4.command.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.command.registration.ClientPersonalAreaCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.entity.User;

public class EditLanguage extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291692835953167605L;
	
	private static final Logger LOG = Logger.getLogger(EditLanguage.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		LOG.info ("Start executing ClientPersonalAreaCommand");
		String result = null;
		
		if (requestMethodType == ActionType.POST) {
			result = doPost(request, response);
		} else if (requestMethodType == ActionType.GET) {
			result = doGet(request, response);
		} else {
			result = null;
		}

		LOG.info ("End executing ClientPersonalAreaCommand");
		return result;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		
		String result = null;
		HttpSession session = request.getSession(false);
		
		//request.getRequestURI()
		//String uriPath = request.getRequestURI().substring(request.getContextPath().length());      // Получаем путь до страницы типа /index.jsp или /login.jsp
		
		//LOG.info("getRequestURI(): " + request.getRequestURI());
		//LOG.info("getRequestURL(): " + request.getRequestURL());
		//LOG.info("uriPath: " + request.getRequestURI().substring(request.getContextPath().length()));
		//request.
		
		User user = (User) session.getAttribute("user");
		LOG.info ("user: " + user);
		
		if (user == null) {
			String language = request.getParameter("language");
			LOG.info ("language: " + language);
			
			session.setAttribute("userLanguage", language);
			result = Path.PAGE_WELCOME_AUTHORIZATION;
		}
		
		return result;
	}

	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		return result;		
	}

}
