package ua.nure.yushin.SummaryTask4.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;

public class ShowAddNewCarFormCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6044895143429015299L;
	
	private static final Logger LOG = Logger.getLogger(ShowAddNewCarFormCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		LOG.info ("Start executing ShowAddNewCarFormCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing ShowAddNewCarFormCommand.execute");
		return result;
	}

	private static String doGet (HttpServletRequest request, HttpServletResponse response) {
		return Path.PAGE_FORWARD_ADMIN_SHOW_ADD_CAR_FORM;
	}

	private static String doPost (HttpServletRequest request, HttpServletResponse response) {		
		return Path.COMMAND_REDIRECT_ADMIN_SHOW_ADD_CAR_FORM;
	}

}
