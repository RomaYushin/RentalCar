package ua.nure.yushin.SummaryTask4.command.outOfControl;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
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
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.util.MailSender;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class ClientRegistrationCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4078417844164011714L;

	private static final Logger LOG = Logger.getLogger(ClientRegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws AppException {

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

	private String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.debug("Start executing ClientRegistrationCommand.doPost");	

		String userPassSeries = null;	
		int userPassNumber = 0;
		String userPassSurname = null;
		String userPassName = null;
		String userPassPatronomic = null;
		Date userPassDateOfBirth = null;
		Sex userSex = null;
		String userEmail = null;
		String userPassword = null;
		String userPassword2 = null;
		boolean userBlocking = false;
		UserRole userRole = null;
		String userLanguage = null;
		boolean userConfirmation = false;
		
		try {				
			userPassSeries = request.getParameter(FieldsInJSPPages.USER_PASS_SERIES);		
			userPassNumber = Integer.parseInt(request.getParameter(FieldsInJSPPages.USER_PASS_NUMBER));
			userPassSurname = request.getParameter(FieldsInJSPPages.USER_PASS_SURNAME);
			userPassName = request.getParameter(FieldsInJSPPages.USER_PASS_NAME);
			userPassPatronomic = request.getParameter(FieldsInJSPPages.USER_PASS_PATRONOMIC);
			userPassDateOfBirth = Date.valueOf(request.getParameter(FieldsInJSPPages.USER_PASS_DATE_OF_BIRTH));
			userSex = Sex.getByName(request.getParameter(FieldsInJSPPages.USER_PASS_SEX));
			userEmail = request.getParameter(FieldsInJSPPages.USER_EMAIL).trim().toLowerCase();
			// сразу шифруем пароль для базы данных
			userPassword = DigestUtils.md5Hex(request.getParameter(FieldsInJSPPages.USER_PASSWORD));
			userPassword2 = DigestUtils.md5Hex(request.getParameter(FieldsInJSPPages.USER_PASSWORD));
			
			userBlocking = false;
			userRole = UserRole.CLIENT;
			userLanguage = request.getParameter(FieldsInJSPPages.USER_LANGUAGE);
			userConfirmation = false;
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}			

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
		LOG.info("userConfirmation: " + userConfirmation);
		
		// валидация входных данных
		ValidatorOfInputParameters.validateUserPassSeries(userPassSeries);
		ValidatorOfInputParameters.validateUserPassNumber(userPassNumber);
		ValidatorOfInputParameters.validateUserFIO(userPassName);
		ValidatorOfInputParameters.validateUserFIO(userPassSurname);
		ValidatorOfInputParameters.validateUserFIO(userPassPatronomic);
		ValidatorOfInputParameters.validateUserPassDateOfBirth(userPassDateOfBirth);
		ValidatorOfInputParameters.validateSex(userSex);
		ValidatorOfInputParameters.validateUserEmail(userEmail);
		ValidatorOfInputParameters.validateUserPassword(userPassword);
		ValidatorOfInputParameters.validateUserRole(userRole);		
		ValidatorOfInputParameters.validate2Passwords(userPassword, userPassword2);
		
		//создание пользователя
		User newUser = new User(userPassSeries, userPassNumber, userPassSurname, userPassName, userPassPatronomic,
				userPassDateOfBirth, userSex, userEmail, userPassword, userBlocking, userRole, userLanguage);
		 
		// проверка на наличие такого же пользователя в базе
		// проверяем по email
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);		
		IUserDAO userDAO = daoFactory.getUserDAO();		
		userDAO.checkIsUserAlreadyInDBByEmail(newUser.getUserEmail());
		
		// добавление пользователя в базу
		userDAO.insertNewUser(newUser);
			
		// выслать подтверждение на почту
		try {
			MailSender.sendConfirmationMessage (newUser);
		} catch ( Exception e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_SEND_EMAIL, e);
			userDAO.removeParticularUser(newUser);
			throw new AppException(ExceptionMessages.EXCEPTION_CAN_NOT_SEND_EMAIL, e);
		}
		
		//вывести сообщение об успешной регистрации и просьбе подтвердить почту\
		// ???
		
		return Path.PAGE_WELCOME_AUTHORIZATION;

	} 
}
