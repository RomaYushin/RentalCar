package ua.nure.yushin.SummaryTask4.command.registration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;

public class ConfirmRegistrationCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6921355517664037983L;
	
	private static final Logger LOG = Logger.getLogger(ConfirmRegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {
		
		LOG.info("Start executing Command");

		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = null;
		}

		LOG.info("Finished executing Command");

		return result;
	}
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) {
		
		String encryptedEmail = request.getParameter("MessageId");
		LOG.info("MessageId= " + encryptedEmail);
		
		//String decodedMessage = new String ()
		return null;
		
	}

}
