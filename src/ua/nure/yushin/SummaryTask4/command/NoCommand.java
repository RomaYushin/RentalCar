package ua.nure.yushin.SummaryTask4.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;

public class NoCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2505112814223025427L;
	
	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {

		LOG.info ("Start executing NoCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing NoCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response) throws AsyncResponseException {	
		return Path.PAGE_FORWARD_ERROR;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response) throws AsyncResponseException {	
		return Path.COMMAND_NO_COMMAND;
	}
	

}
