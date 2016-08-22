package ua.nure.yushin.SummaryTask4.command.admin;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class RegisterNewCarCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2665791906089256538L;
	
	private static final Logger LOG = Logger.getLogger(RegisterNewCarCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws DBException, AsyncResponseException {

		LOG.info ("Start executing RegisterNewCarCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing RegisterNewCarCommand.execute");
		return result;
	}

	private static String doGet (HttpServletRequest request, HttpServletResponse response) throws DBException {
		request.setAttribute("successfullAddCar", "Add new car was successfull!");		
		return Path.PAGE_FORWARD_ADMIN_ADD_CAR_RESPONSE;
	}

	private static String doPost (HttpServletRequest request, HttpServletResponse response) throws DBException, AsyncResponseException {
		
		LOG.info ("Start executing RegisterNewCarCommand.doPost");
		
		String carBrend = null;
		String carModel = null;
		Date carYearOfIssue = null;
		String carQualityClass = null;
		int carRentalCost = 0;
		
		try {
			carBrend = request.getParameter("carBrend");
			carModel = request.getParameter("carModel");
			//String carYearOfIssue_s = request.getParameter("carYearOfIssue");
			carYearOfIssue = Date.valueOf(request.getParameter("carYearOfIssue"));
			carQualityClass = request.getParameter("carQualityClass");
			carRentalCost = Integer.valueOf(request.getParameter("carRentalCost"));
			//String carRentalCost_s = request.getParameter("carRentalCost");
			
			LOG.info("carBrend: " + carBrend);
			LOG.info("carModel: " + carModel);
			LOG.info("carYearOfIssue: " + carYearOfIssue);
			LOG.info("carQualityClass: " + carQualityClass);
			LOG.info("carRentalCost: " + carRentalCost);
		} catch (Exception e) {
			throw new AsyncResponseException (ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR, e);
		}
		
		/*
		Date carYearOfIssue = Date.valueOf(carYearOfIssue_s);
		int carRentalCost = Integer.valueOf(carRentalCost_s);
		*/
		
		try {
			ValidatorOfInputParameters.validateCarBrend(carBrend);
			ValidatorOfInputParameters.validateCarModel(carModel);
			ValidatorOfInputParameters.validateCarYearOfIssue(carYearOfIssue);
			ValidatorOfInputParameters.validateQualityCarClass(carQualityClass);
			ValidatorOfInputParameters.validateCarRentalCost(carRentalCost);
		} catch (ValidationException vExcep) {
			throw new AsyncResponseException(vExcep.getMessage());
		}
		
		Car newCar = new Car (carBrend, carModel, CarQualityClass.valueOf(carQualityClass),
				carRentalCost, CarStatus.FREE, carYearOfIssue);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		try {
			iCarDAO.insertNewCar(newCar);
		} catch (DBException e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_CAR, e);
		}		
		
		return Path.COMMAND_REDIRECT_ADMIN_REGISTER_NEW_CAR;
	}
}
