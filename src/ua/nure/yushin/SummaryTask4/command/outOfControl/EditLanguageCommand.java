package ua.nure.yushin.SummaryTask4.command.outOfControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.AppException;

public class EditLanguageCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291692835953167605L;

	private static final Logger LOG = Logger.getLogger(EditLanguageCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {

		LOG.info("Start executing EditLanguageCommand");

		String currentCommand = null;
		String language = null;
		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");
		currentCommand = request.getParameter("currentCommand");
		language = request.getParameter("language");

		LOG.info("user from session: " + user);
		LOG.info("currentCommand: " + currentCommand);
		LOG.info("language: " + language);
	
		session.setAttribute("userLanguage", language);

		if (currentCommand.equals("") || (currentCommand == null) || currentCommand.equals("editLanguage")) {
			return Path.PAGE_WELCOME_AUTHORIZATION;
		} else {
			return Path.COMMAND_REDIRECT_TO_COMMAND + currentCommand;
		}		
	}
}
