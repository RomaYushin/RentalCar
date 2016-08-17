package ua.nure.yushin.SummaryTask4.command.async;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;

public class CheckOrderStatusAsyncCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4733777027506933627L;
	
	private static final Logger LOG = Logger.getLogger(CheckOrderStatusAsyncCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		LOG.info("Start executing CheckOrderStatusAsyncCommand.execute");
		return Path.PAGE_FORWARD_CLIENT_CHECK_ORDER_STATUS_ASYNC;
	}
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	private String doPost (HttpServletRequest request, HttpServletResponse response) {		
		return null;
	}

}
