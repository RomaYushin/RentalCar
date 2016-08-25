package ua.nure.yushin.SummaryTask4.command.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.command.ICommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class ShowConfirmViewCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3989467399873806252L;
	
	private static final Logger LOG = Logger.getLogger(ShowConfirmViewCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info("Start executing ShowConfirmViewCommand");

		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}

		LOG.info("Finished executing Command");

		return result;
	}
	
	private String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {		
		return null;		
	}
	
	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		
		LOG.debug("Start executing ShowConfirmViewCommand.doGet");
		
		String userEmail = request.getParameter("userEmail");
		LOG.info("userEmail (from email):" + userEmail );
		
		request.setAttribute("userEmail", userEmail);
		return Path.PAGE_FORWARD_CLIENT_CONFIRM_REGISTRATION;
	}

}
