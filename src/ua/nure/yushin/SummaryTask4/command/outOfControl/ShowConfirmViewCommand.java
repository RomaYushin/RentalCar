package ua.nure.yushin.SummaryTask4.command.outOfControl;

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
		return Path.COMMAND_REDIRECT_CONFIRM_REGISTRATION;		
	}
	
	private String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.debug("Start executing ShowConfirmViewCommand.doGet");
		
		String userEmail = null;
		String userPassword = null;
		
		try {
			LOG.info("userEmail (from email):" + request.getParameter("userEmail") );
			LOG.info("userPassword (from email):" + request.getParameter("userEmail") );
			
			userEmail = request.getParameter("userEmail");
			userPassword = request.getParameter("userPassword");
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		User user = iUserDAO.getUserByEmail(userEmail);
		
		ValidatorOfInputParameters.validateUserEmail(userEmail);
		ValidatorOfInputParameters.validate2Passwords(userPassword, user.getUserPassword());
		
		request.setAttribute("userEmail", userEmail);
		return Path.PAGE_FORWARD_CLIENT_CONFIRM_REGISTRATION;
	}

}
