package ua.nure.yushin.SummaryTask4.command.registration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.entity.UserRole;
import ua.nure.yushinSummaryTask4.validators.ValidatorOfInputParameters;

public class UserAuthorizationCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 359497482231227417L;
	
	private static final Logger LOG = Logger.getLogger(UserAuthorizationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		LOG.info("Start executing UserAuthorizationCommand.execute");
		String result = null;
		
		if (requestMethodType.equals(ActionType.POST)) {
			result = doPost (request, response);
		} else {
			result = null;
		}
		
		LOG.info("End executing UserAuthorizationCommand.execute");
		return result;
	}
	
	private String doPost (HttpServletRequest request, HttpServletResponse response) {
		
		LOG.info("Start executing UserAuthorizationCommand");
		
		// получение email и  password
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		
		LOG.info("userEmail: " + userEmail);
		LOG.info("userPassword: " + userPassword);
		
		// валидация email и password
		if (!ValidatorOfInputParameters.validateUserEmail(userEmail)) {
			request.setAttribute("errorMessage", "Email is not valid. Check your email!");
			LOG.error("error: invalid email at the time of authorization");
			return null;
		} else if (!ValidatorOfInputParameters.validateUserPassword(userPassword)) {
			request.setAttribute("errorMessage", "Password is not valid. Check your password!");
			LOG.error("error: invalid password at the time of authorization");
			return null;
		}
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IUserDAO userDAO = daoFactory.getUserDAO();
		User userFromDB = userDAO.findUserByEmailAndPassword(userEmail, userPassword);
		
		// если user не найден, то перебросить на стартовую страницу
		if (userFromDB == null) {
			request.setAttribute("errorMessage", "Cannot find user with such login/password");
			LOG.error("error: Cannot find user with such login/password");
			return null;
		} 
		
		// если клиент заблокирован, перебросить на стартовую страницу
		if (userFromDB.isUserBlocking()) {
			request.setAttribute("errorMessage", "User is blocked");
			LOG.error("error: User is blocked");
			return null;
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

}
