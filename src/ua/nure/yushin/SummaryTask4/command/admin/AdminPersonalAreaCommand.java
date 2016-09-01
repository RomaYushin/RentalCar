package ua.nure.yushin.SummaryTask4.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;

public class AdminPersonalAreaCommand extends AbstractCommand {

	private static final long serialVersionUID = 2675712153083154954L;

	private static final Logger LOG = Logger.getLogger(AdminPersonalAreaCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {
		LOG.debug("Start executing AdminPersonalAreaCommand");
		String result = null;
		
		if (requestMethodType == ActionType.POST) {
			result = doPost(request, response);
		} else if (requestMethodType == ActionType.GET) {
			result = doGet(request, response);
		} else {
			result = null;
		}

		LOG.debug("End executing AdminPersonalAreaCommand");
		return result;
		
	}

	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		return null;		
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		return Path.PAGE_FORWARD_ADMIN_PERSONAL_AREA;
	}

}
