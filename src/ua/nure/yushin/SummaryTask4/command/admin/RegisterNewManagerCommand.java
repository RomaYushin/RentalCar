package ua.nure.yushin.SummaryTask4.command.admin;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		request.setAttribute("asyncResponse", "Add new manager was successfull!");		
		return Path.PAGE_FORWARD_ADMIN_ASYNC_RESPONSE;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response) 
			throws DBException, AsyncResponseException {
		
		LOG.info ("Start executing RegisterNewManagerCommand.doPost");
		
		String userPassSeries = null;		
		String userPassNumber_s = null;	
		String userPassSurname = null;
		String userPassName = null;
		String userPassPatronomic = null;		
		String userPassDateOfBirth_s = null;
		String userSex_s = null;
		String userEmail = null;
		String userPassword = null;
		String userLanguage = null;
		
		try {
			
			userPassSeries = request.getParameter(FieldsInJSPPages.USER_PASS_SERIES);		
			userPassNumber_s = request.getParameter(FieldsInJSPPages.USER_PASS_NUMBER);
			userPassSurname = request.getParameter(FieldsInJSPPages.USER_PASS_SURNAME);
			userPassName = request.getParameter(FieldsInJSPPages.USER_PASS_NAME);
			userPassPatronomic = request.getParameter(FieldsInJSPPages.USER_PASS_PATRONOMIC);			
			userPassDateOfBirth_s = request.getParameter(FieldsInJSPPages.USER_PASS_DATE_OF_BIRTH);
			userSex_s = request.getParameter(FieldsInJSPPages.USER_PASS_SEX);
			userEmail = request.getParameter(FieldsInJSPPages.USER_EMAIL);
			userPassword = request.getParameter(FieldsInJSPPages.USER_PASSWORD);
			userLanguage = request.getParameter(FieldsInJSPPages.USER_LANGUAGE);
			
			LOG.info("userPassSeries: " + userPassSeries);		
			LOG.info("userPassNumber_s: " + userPassNumber_s);
			LOG.info("userPassSurname: " + userPassSurname);
			LOG.info("userPassName: " + userPassName);
			LOG.info("userPassPatronomic: " + userPassPatronomic);
			LOG.info("userPassDateOfBirth_s: " + userPassDateOfBirth_s);
			LOG.info("userSex_s: " + userSex_s);
			LOG.info("userEmail: " + userEmail);
			LOG.info("userPassword: " + userPassword);
			LOG.info("userLanguage: " + userLanguage);
			
		} catch (Exception e) {
			throw new AsyncResponseException (ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR, e);
		}
		
		try {
			
			ValidatorOfInputParameters.validateUserPassSeries(userPassSeries);
			ValidatorOfInputParameters.validateUserPassNumber(userPassNumber_s);
			ValidatorOfInputParameters.validateUserFIO(userPassName);
			ValidatorOfInputParameters.validateUserFIO(userPassSurname);
			ValidatorOfInputParameters.validateUserFIO(userPassPatronomic);
			ValidatorOfInputParameters.validateUserPassDateOfBirth(userPassDateOfBirth_s);
			ValidatorOfInputParameters.validateSex(userSex_s);
			ValidatorOfInputParameters.validateUserEmail(userEmail);
			ValidatorOfInputParameters.validateUserPassword(userPassword);			
			
		}  catch (ValidationException vExcep) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_SEX, vExcep);
			throw new AsyncResponseException(vExcep.getMessage());
		}
		
		int userPassNumber = Integer.valueOf(userPassNumber_s);
		Date userPassDateOfBirth = Date.valueOf(userPassDateOfBirth_s);
		Sex userSex = Sex.getByName(userSex_s);
		boolean userBlocking = false;
		UserRole userRole = UserRole.MANAGER;
		
		User newUser = new User(userPassSeries, userPassNumber, userPassSurname, userPassName, userPassPatronomic,
				userPassDateOfBirth, userSex, userEmail, userPassword, userBlocking, userRole, userLanguage);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);		
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		
		try {
			iUserDAO.insertNewUser(newUser);
		} catch (DBException e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_USER, e);
		}		
		
		LOG.info ("END executing RegisterNewManagerCommand.doPost");
		
		return Path.COMMAND_REDIRECT_ADMIN_REGISTER_NEW_MANAGER;	
	}

}
