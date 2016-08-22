package ua.nure.yushin.SummaryTask4.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class RemoveCarCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2184887732377625138L;
	
	private static final Logger LOG = Logger.getLogger(RemoveCarCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing RemoveCarCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing RemoveCarCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response) {		
		
		request.setAttribute("successfullRemoveCar", "Removal car was successfull!");
		return Path.PAGE_FORWARD_ADMIN_REMOVE_CAR_RESPONSE;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response) throws AsyncResponseException {
		
		LOG.info ("Start executing RemoveCarCommand.doPost");
		int removeCarId = 0;
		String removeCarId_s = null;
		
		try {
			removeCarId_s = request.getParameter("removeCarId");
			LOG.info("removeCarId: " + removeCarId_s);
		} catch (Exception e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		removeCarId = Integer.valueOf(removeCarId_s);
		try {
			ValidatorOfInputParameters.validateId(removeCarId);
		} catch (ValidationException vExcep) {
			throw new AsyncResponseException(vExcep.getMessage());
		}
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		try {
			iCarDAO.removeParticularCarById(removeCarId);
		} catch (DBException e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_REMOVE_CAR, e);
		}
		
		return Path.COMMAND_REDIRECT_ADMIN_REMOVE_CAR;
	}

}//removeCarId
