package ua.nure.yushin.SummaryTask4.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class ShowBlockUserFormCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4138056097699721927L;
	
	private static final Logger LOG = Logger.getLogger(ShowBlockUserFormCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing ShowBlockUserFormCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing ShowBlockUserFormCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response)
			throws AsyncResponseException {	
		
		List <User> allUsers = null;
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		try {
			allUsers = iUserDAO.getAllUsersFromDB();
		} catch (DBException dbExcep) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_USERS, dbExcep);		
		}
		
		request.setAttribute("allUsers", allUsers);
		return Path.PAGE_FORWARD_ADMIN_SHOW_BLOCKING_USER_FORM;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response)
			throws AsyncResponseException {			
		return Path.COMMAND_REDIRECT_ADMIN_SHOW_BLOCKING_USER_FORM;		
	}


}
