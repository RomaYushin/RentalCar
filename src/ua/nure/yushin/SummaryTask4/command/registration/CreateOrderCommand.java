package ua.nure.yushin.SummaryTask4.command.registration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;

public class CreateOrderCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7521929337525097400L;
	
	private static final Logger LOG = Logger.getLogger(CreateOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		LOG.info ("Start executing CreateOrderCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing CreateOrderCommand.execute");
		return result;
	}
	
	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		
		LOG.info ("Start executing CreateOrderCommand.doPost");
		
		int carId = Integer.parseInt(request.getParameter(""));
		int userId = Integer.parseInt(request.getParameter(""));
		String driver = request.getParameter("");
		//Date 
		//Date 
		return null;
		
	}
	
	private String doGet(HttpServletRequest request, HttpServletResponse response) {
	
	return null;	
	}
	
	

}
