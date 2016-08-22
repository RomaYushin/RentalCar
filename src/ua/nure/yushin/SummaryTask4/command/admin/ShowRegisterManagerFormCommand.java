package ua.nure.yushin.SummaryTask4.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;

public class ShowRegisterManagerFormCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6158832733758454787L;
	
	private static final Logger LOG = Logger.getLogger(ShowRegisterManagerFormCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info("Start executing ShowEditCarFormCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info("End executing ShowEditCarFormCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response)
			throws AsyncResponseException {
		return Path.PAGE_FORWARD_ADMIN_SHOW_REGISTER_MANAGER_FROM;		
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response)
			throws AsyncResponseException {
		return null;		
	}

}
