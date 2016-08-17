package ua.nure.yushin.SummaryTask4.command.registration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;

public class ClientPersonalAreaCommand extends AbstractCommand {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6657619538750393374L;
	
	private static final Logger LOG = Logger.getLogger(ClientPersonalAreaCommand.class);

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

	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		return null;		
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
	}

}
