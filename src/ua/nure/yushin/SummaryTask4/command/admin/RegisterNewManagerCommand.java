package ua.nure.yushin.SummaryTask4.command.admin;

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
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.util.LocaleUtil;
import ua.nure.yushin.SummaryTask4.util.MailSender;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class RegisterNewManagerCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7666651572684529133L;
	
	private static final Logger LOG = Logger.getLogger(RegisterNewManagerCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing RegisterNewManagerCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing RegisterNewManagerCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response) 
			throws DBException {
		
		HttpSession session = request.getSession(false);
		if ( session.getAttribute("responseMessage") != null) {
			request.setAttribute("responseMessage", (String) session.getAttribute("responseMessage"));
			session.removeAttribute("responseMessage");
			//return Path.PAGE_WELCOME_AUTHORIZATION;
		}		
		return Path.PAGE_FORWARD_ADMIN_PERSONAL_AREA;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response) 
			throws AppException {
		
		LOG.info ("Start executing RegisterNewManagerCommand.doPost");
		HttpSession session = request.getSession(false);
		String responseMessage = LocaleUtil.getValueByKey("adminPerArea.jsp.addNewManagerRespMessage", session);
		
		String userPassSeries = null;		
		int userPassNumber = 0;	
		String userPassSurname = null;
		String userPassName = null;
		String userPassPatronomic = null;		
		Date userPassDateOfBirth = null;
		Sex userSex = null;
		String userEmail = null;
		String userPassword = null;
		
		boolean userBlocking = false;
		UserRole userRole = UserRole.MANAGER;
		String userLanguage = null;
		boolean userConfirmation = true;
		
		try {
			
			userPassSeries = request.getParameter(FieldsInJSPPages.USER_PASS_SERIES);		
			userPassNumber = Integer.valueOf(request.getParameter(FieldsInJSPPages.USER_PASS_NUMBER));
			userPassSurname = request.getParameter(FieldsInJSPPages.USER_PASS_SURNAME);
			userPassName = request.getParameter(FieldsInJSPPages.USER_PASS_NAME);
			userPassPatronomic = request.getParameter(FieldsInJSPPages.USER_PASS_PATRONOMIC);			
			userPassDateOfBirth =  Date.valueOf(request.getParameter(FieldsInJSPPages.USER_PASS_DATE_OF_BIRTH));
			userSex = Sex.getByName(request.getParameter(FieldsInJSPPages.USER_PASS_SEX));
			userEmail = request.getParameter(FieldsInJSPPages.USER_EMAIL);			
			userPassword = request.getParameter(FieldsInJSPPages.USER_PASSWORD);
			userLanguage = request.getParameter(FieldsInJSPPages.USER_LANGUAGE);
			
			LOG.info("userPassSeries: " + userPassSeries);		
			LOG.info("userPassNumber_s: " + userPassNumber);
			LOG.info("userPassSurname: " + userPassSurname);
			LOG.info("userPassName: " + userPassName);
			LOG.info("userPassPatronomic: " + userPassPatronomic);
			LOG.info("userPassDateOfBirth: " + userPassDateOfBirth);
			LOG.info("userSex_s: " + userSex);
			LOG.info("userEmail: " + userEmail);
			//LOG.info("userPassword: " + userPassword);
			LOG.info("userLanguage: " + userLanguage);
			
		} catch (Exception e) {
			throw new AsyncResponseException (ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR, e);
		}
		
		ValidatorOfInputParameters.validateUserPassSeries(userPassSeries);
		ValidatorOfInputParameters.validateUserPassNumber(userPassNumber);
		ValidatorOfInputParameters.validateUserFIO(userPassName);
		ValidatorOfInputParameters.validateUserFIO(userPassSurname);
		ValidatorOfInputParameters.validateUserFIO(userPassPatronomic);
		ValidatorOfInputParameters.validateUserPassDateOfBirth(userPassDateOfBirth);
		ValidatorOfInputParameters.validateSex(userSex);
		ValidatorOfInputParameters.validateUserEmail(userEmail);
		ValidatorOfInputParameters.validateUserPassword(userPassword);			
		
		// шифрация пароля
		userPassword = DigestUtils.md5Hex(userPassword);
		
		User newUser = new User(userPassSeries, userPassNumber, userPassSurname, userPassName, userPassPatronomic,
				userPassDateOfBirth, userSex, userEmail, userPassword, userBlocking, userRole, userLanguage, userConfirmation);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);		
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		iUserDAO.checkIsUserAlreadyInDBByEmail(newUser.getUserEmail());		

		iUserDAO.insertNewUser(newUser);		
		LOG.info ("END executing RegisterNewManagerCommand.doPost");
		
		session.setAttribute("responseMessage", responseMessage);
		return Path.COMMAND_REDIRECT_ADMIN_REGISTER_NEW_MANAGER;	
	}

}
