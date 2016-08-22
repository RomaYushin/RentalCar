package ua.nure.yushin.SummaryTask4.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushinSummaryTask4.validators.ValidatorOfInputParameters;

public class ShowCarParamFormCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1530824088341463897L;
	
	private static final Logger LOG = Logger.getLogger(ShowCarParamFormCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) 
			throws AsyncResponseException {

		LOG.info ("Start executing ShowCarParamFormCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing ShowCarParamFormCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response) throws AsyncResponseException {	
		
		LOG.info ("Start executing ShowCarParamFormCommand.doGet");
		
		Car car = null;
		HttpSession session = request.getSession(false);
		try {
			car = (Car) session.getAttribute("EditCar");
		} catch (Exception e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		session.removeAttribute("EditCar");
		
		request.setAttribute("carForEdit", car);
		return Path.COMMAND_REDIRECT_ADMIN_SHOW_CAR_PARAM_FORM;
		
		
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response) throws AsyncResponseException {	
		
		LOG.info ("Start executing ShowCarParamFormCommand.doPost");
		
		Car car = null;
		String carId_s = null;
		int carId = 0;
		
		try {
			carId_s = request.getParameter("carId");
			LOG.info("carId_s : " + carId_s );
		} catch (Exception e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		carId = Integer.valueOf(carId_s);
		try {
			ValidatorOfInputParameters.validateId(carId);
		} catch (ValidationException vExcep) {
			throw new AsyncResponseException(vExcep.getMessage());
		}
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		try {
			car = iCarDAO.getCarById(carId);
		} catch (DBException e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_CAR_BY_ID, e);
		}
		
		HttpSession session = request.getSession(false);
		session.setAttribute("EditCar", car);
		
		return Path.COMMAND_REDIRECT_ADMIN_SHOW_CAR_PARAM_FORM;
	}

}
