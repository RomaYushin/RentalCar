package ua.nure.yushin.SummaryTask4.command.registration;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.FieldsInJSPPages;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.entity.UserRole;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.util.MailSender;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class ClientRegistrationCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4078417844164011714L;

	private static final Logger LOG = Logger.getLogger(ClientRegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws DBException {

		LOG.debug("Start executing ClientRegistrationCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.debug("End executing ClientRegistrationCommand.execute");
		return result;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start executing ClientRegistrationCommand.doGet");
		return Path.PAGE_FORWARD_CLIENT_REGISTRATION;
	}

	private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
		
		LOG.debug("Start executing ClientRegistrationCommand.doPost");
		String result = null;
		boolean valid = false;		

		String userPassSeries = request.getParameter(FieldsInJSPPages.USER_PASS_SERIES);		
		int userPassNumber = Integer.parseInt(request.getParameter(FieldsInJSPPages.USER_PASS_NUMBER));
		String userPassSurname = request.getParameter(FieldsInJSPPages.USER_PASS_SURNAME);
		String userPassName = request.getParameter(FieldsInJSPPages.USER_PASS_NAME);
		String userPassPatronomic = request.getParameter(FieldsInJSPPages.USER_PASS_PATRONOMIC);
		
		Date userPassDateOfBirth = Date.valueOf(request.getParameter(FieldsInJSPPages.USER_PASS_DATE_OF_BIRTH));
		
		//String userPassDateOfBirth = request.getParameter(FieldsInJSPPages.USER_PASS_DATE_OF_BIRTH);
		Sex userSex = Sex.getByName(request.getParameter(FieldsInJSPPages.USER_PASS_SEX));
		String userEmail = request.getParameter(FieldsInJSPPages.USER_EMAIL);
		String userPassword = request.getParameter(FieldsInJSPPages.USER_PASSWORD);
		boolean userBlocking = false;
		UserRole userRole = UserRole.CLIENT;
		String userLanguage = request.getParameter(FieldsInJSPPages.USER_LANGUAGE);		

		LOG.info("userPassSeries: " + userPassSeries);		
		LOG.info("userPassNumber: " + userPassNumber);
		LOG.info("userPassSurname: " + userPassSurname);
		LOG.info("userPassName: " + userPassName);
		LOG.info("userPassPatronomic: " + userPassPatronomic);
		LOG.info("userPassDateOfBirth: " + userPassDateOfBirth);
		LOG.info("userSex: " + userSex);
		LOG.info("userEmail: " + userEmail);
		LOG.info("userPassword: " + userPassword);
		LOG.info("userBlocking: " + userBlocking);
		LOG.info("userRole: " + userRole);
		LOG.info("userLanguage: " + userLanguage);
		
		
		
		// валидация входных данных
		// *************************!!!!!!!!!
		valid = ValidatorOfInputParameters.validateClientRegistrationParametrs();
		LOG.trace("validateClientRegistrationParametrs:" + valid);		
		if (!valid) {
			request.setAttribute("errorMessage", "Something wrong");
			LOG.error("error: wrong validation");
			return Path.COMMAND_REDIRECT_CLIENT_REGISTRATION;
		}
		
		//создание пользователя
		//long time = System.currentTimeMillis();
		User newUser = new User(userPassSeries, userPassNumber, userPassSurname, userPassName, userPassPatronomic,
				userPassDateOfBirth, userSex, userEmail, userPassword, userBlocking, userRole, userLanguage);
		 
		
		// проверка на наличие такого же пользователя в базе
		// проверяем по серии номеру паспорта и по email
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);		
		IUserDAO userDAO = daoFactory.getUserDAO();
		valid = userDAO.findSpecifiedUserInDB(newUser);
		
		if (!valid) {
			request.setAttribute("errorMessage", "Something wrong");
			LOG.error("error: User is already in DB");
			return Path.COMMAND_REDIRECT_CLIENT_REGISTRATION;
		}

		if (valid) {			
			//DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
			
			//IUserDAO userDAO = daoFactory.getUserDAO();
			userDAO.insertNewUser(newUser);
			LOG.trace("New user added to database" + newUser);
			
			// выслать подтверждение на почту
			//request.setAttribute("message", arg1);
			MailSender.sendConfirmationMessage (newUser);
			result = Path.PAGE_WELCOME_AUTHORIZATION;
		}
		
		return result;

	} 
}
