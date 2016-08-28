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
import ua.nure.yushin.SummaryTask4.entity.UserRole;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class UserAuthorizationCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 359497482231227417L;
	
	private static final Logger LOG = Logger.getLogger(UserAuthorizationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws AppException {

		LOG.info("Start executing UserAuthorizationCommand.execute");
		String result = null;
		
		if (requestMethodType.equals(ActionType.POST)) {
			result = doPost (request, response);
		} else {
			result = doGet (request, response);		}
		
		LOG.info("End executing UserAuthorizationCommand.execute");
		return result;
	}
	
	private String doPost (HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.info("Start executing UserAuthorizationCommand");
		
		// получение email и  password
		String userEmail = request.getParameter("userEmail");
		String userPassword = DigestUtils.md5Hex(request.getParameter("userPassword"));
		
		LOG.info("userEmail: " + userEmail);
		LOG.info("userPassword: " + userPassword);
		
		// валидация email и password
		ValidatorOfInputParameters.validateUserEmail(userEmail);
		ValidatorOfInputParameters.validateUserPassword(userPassword);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IUserDAO userDAO = daoFactory.getUserDAO();
		User userFromDB = userDAO.getUserByEmailAndPassword(userEmail, userPassword);
		
		// проверка на подтверждение почты
		if (!userFromDB.isUserConfirmation()) {
			throw new AppException(ExceptionMessages.EXCEPTION_UNCONFIRMED_USER);
		}
		
		// проверить заблокирован ли клиент
		if (userFromDB.isUserBlocking()) {
			throw new AppException(ExceptionMessages.EXCEPTION_BLOCKED_USER);
		}
		/*
		LOG.info("userFromDB.getId: " + userFromDB.getId());
		LOG.info("userFromDB.getUserPassSeries: " + userFromDB.getUserPassSeries());
		LOG.info("userFromDB.getUserPassNumber: " + userFromDB.getUserPassNumber());
		LOG.info("userFromDB.getUserPassSurname: " + userFromDB.getUserPassSurname());
		LOG.info("userFromDB.getUserPassName: " + userFromDB.getUserPassName());
		LOG.info("userFromDB.getUserPassPatronomic: " + userFromDB.getUserPassPatronomic());
		LOG.info("userFromDB.getUserPassDateOfBirth: " + userFromDB.getUserPassDateOfBirth().toString());
		LOG.info("userFromDB.getUserSex: " + userFromDB.getUserSex().toString());
		LOG.info("userFromDB.getUserEmail: " + userFromDB.getUserEmail());
		LOG.info("userFromDB.getUserPassword: " + userFromDB.getUserPassword());
		LOG.info("userFromDB.getUserBlocking: " + userFromDB.isUserBlocking());
		LOG.info("userFromDB.getUserRole: " + userFromDB.getUserRole().toString());
		LOG.info("userFromDB.getUserLanguage: " + userFromDB.getUserLanguage());		
		*/
		
		HttpSession session = request.getSession(true);
		session.setAttribute("user", userFromDB);
		session.setAttribute("userEmail", userFromDB.getUserEmail());
		session.setAttribute("userLanguage", userFromDB.getUserLanguage());
		session.setAttribute("userRole", userFromDB.getUserRole());
		
		LOG.info("info: user " + userFromDB.getUserPassName() + " with email: "
		+ userFromDB.getUserEmail() + " logged as " + userFromDB.getUserRole().toString());
		
		if (userFromDB.getUserRole().equals(UserRole.ADMIN)) {
			return Path.COMMAND_REDIRECT_ADMIN_PERSONAL_AREA;
		} else if (userFromDB.getUserRole().equals(UserRole.MANAGER)) {
			return Path.COMMAND_REDIRECT_MANAGER_PERSONAL_AREA;
		} else if (userFromDB.getUserRole().equals(UserRole.CLIENT)) {
			return Path.COMMAND_REDIRECT_CLIENT_PERSONAL_AREA;
		} else {
			return null;
		}
	}
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) {
		return Path.PAGE_WELCOME_AUTHORIZATION;
	}

}
