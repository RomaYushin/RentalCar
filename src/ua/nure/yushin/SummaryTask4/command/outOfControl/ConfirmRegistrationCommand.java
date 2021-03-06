package ua.nure.yushin.SummaryTask4.command.outOfControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class ConfirmRegistrationCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6921355517664037983L;

	private static final Logger LOG = Logger.getLogger(ConfirmRegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws AppException {

		LOG.info("Start executing ConfirmRegistrationCommand");

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
		
		LOG.debug("Start executing ShowConfirmViewCommand.doPost");

		String userEmail = null;
		String userPassword = null;

		try {			
			userEmail = request.getParameter("userEmail");
			userPassword = request.getParameter("userPassword");

		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}

		// валидация входных данных
		ValidatorOfInputParameters.validateUserEmail(userEmail);
		ValidatorOfInputParameters.validateUserPassword(userPassword);
		
		//шифруем пароль
		userPassword = DigestUtils.md5Hex(request.getParameter("userPassword"));
		
		// проверка совпадает ли пароль		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		User user = iUserDAO.getUserByEmail(userEmail);
		ValidatorOfInputParameters.validate2Passwords(userPassword, user.getUserPassword());		
		
		// вытягиваем по email пользователя
		IUserDAO userDAO = daoFactory.getUserDAO();
		user = userDAO.getUserByEmailAndPassword(userEmail, userPassword);
		
		// поменять состояние поля подтверждения
		user.setUserConfirmation(true);
		userDAO.updateUserById (user);
		
		// сообщить, что регистрация прошла успешно
		HttpSession session = request.getSession(false);
		session.setAttribute("responseMessage", "welcomeAuthorization.jsp.successfullConfirm");
		

		return Path.COMMAND_REDIRECT_CONFIRM_REGISTRATION;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {

		LOG.debug("Start executing ConfirmRegistrationCommand.doGet");
		
		HttpSession session = request.getSession(false);
		String responseMessage = (String) session.getAttribute("responseMessage");
		session.removeAttribute("responseMessage");		
		request.setAttribute("responseMessage", responseMessage);
	
		return Path.PAGE_WELCOME_AUTHORIZATION;
	}
}
