package ua.nure.yushin.SummaryTask4.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;

public class OrderCarAsyncCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4152834749976920215L;
	
	private static final Logger LOG = Logger.getLogger(OrderCarAsyncCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {
		LOG.info("Start executing OrderCarAsyncCommand.execute");
		return Path.PAGE_FORWARD_CLIENT_ORDER_CAR_ASYNC;
	}
}
