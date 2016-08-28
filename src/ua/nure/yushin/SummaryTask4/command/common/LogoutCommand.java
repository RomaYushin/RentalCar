package ua.nure.yushin.SummaryTask4.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;

public class LogoutCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8872666806119826134L;
	
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		LOG.info("Start executing LogoutCommand.execute");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		LOG.info("End executing LogoutCommand.execute");
		return Path.PAGE_WELCOME_AUTHORIZATION;
	}

}
