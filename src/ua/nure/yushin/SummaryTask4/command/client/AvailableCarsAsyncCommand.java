package ua.nure.yushin.SummaryTask4.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;

public class AvailableCarsAsyncCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7041355534487078565L;

	private static final Logger LOG = Logger.getLogger(AvailableCarsAsyncCommand.class);
	
	//private List <Car> availableCars = null;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {
		
		LOG.info("availableCars:" + request.getAttribute("availableCars"));
		return Path.PAGE_FORWARD_CLIENT_AVAILABLE_CARS_ASYNC;
		
	}

}
