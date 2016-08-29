package ua.nure.yushin.SummaryTask4.command.admin;

import java.sql.Date;

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
import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.util.LocaleUtil;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class EditCarCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1052396791871431783L;
	
	private static final Logger LOG = Logger.getLogger(EditCarCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing EditCarCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing EditCarCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response)
			throws DBException {
		
		HttpSession session = request.getSession(false);
		String responseMessage = (String) session.getAttribute("responseMessage");
		session.removeAttribute("responseMessage");
		
		request.setAttribute("responseMessage", responseMessage);		
		return Path.PAGE_FORWARD_ADMIN_PERSONAL_AREA;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response)
			throws AsyncResponseException, ValidationException {

		LOG.info ("Start executing EditCarCommand.doPost");
		
		int carId = 0;
		String newCarBrend = null; 
		String newCarModel = null;
		Date newCarYearOfIssue = null;
		String newCarQualityClass_s = null;
		int newCarRentalCost = 0; 
		String newCarStatus_s = null;
		HttpSession session = request.getSession(false);
		String responseMessage = LocaleUtil.getValueByKey("adminPerArea.jsp.editCarRespMessage", session);
		
		try {
			
			carId = Integer.valueOf(request.getParameter("carId"));
			newCarBrend = request.getParameter("newCarBrend");
			newCarModel = request.getParameter("newCarModel");
			newCarYearOfIssue = Date.valueOf(request.getParameter("newCarYearOfIssue"));
			newCarQualityClass_s = request.getParameter("newCarQualityClass");
			newCarRentalCost = Integer.valueOf(request.getParameter("newCarRentalCost"));
			newCarStatus_s = request.getParameter("newCarStatus");
			
			LOG.info("carId: " + carId);
			LOG.info("newCarBrend: " + newCarBrend);
			LOG.info("newCarModel: " + newCarModel);
			LOG.info("newCarYearOfIssue: " + newCarYearOfIssue);
			LOG.info("newCarQualityClass_s: " + newCarQualityClass_s);
			LOG.info("newCarRentalCost: " + newCarRentalCost);
			LOG.info("newCarStatus_s: " + newCarStatus_s);
			
		} catch (Exception e) {
			throw new AsyncResponseException (ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR, e);
		}
		

		ValidatorOfInputParameters.validateId(carId);
		ValidatorOfInputParameters.validateCarBrend(newCarBrend);
		ValidatorOfInputParameters.validateCarModel(newCarModel);
		ValidatorOfInputParameters.validateCarYearOfIssue(newCarYearOfIssue);
		ValidatorOfInputParameters.validateQualityCarClass(newCarQualityClass_s);
		ValidatorOfInputParameters.validateCarRentalCost(newCarRentalCost);
		ValidatorOfInputParameters.validateCarStatus(newCarStatus_s);

		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		
		try {			
			Car updateCar = iCarDAO.getCarById(carId);
			updateCar.setCarBrend(newCarBrend);
			updateCar.setCarModel(newCarModel);
			updateCar.setCarYearOfIssue(newCarYearOfIssue);
			updateCar.setCarQualityClass(CarQualityClass.getByName(newCarQualityClass_s));
			updateCar.setCarRentalCost(newCarRentalCost);
			updateCar.setCarStatus(CarStatus.getByName(newCarStatus_s));
			
			iCarDAO.updateCar(updateCar);
			/*
			iCarDAO.updateCarBrend(newCarBrend);
			iCarDAO.updateCarModel(newCarModel);
			iCarDAO.updateCarYearOfIssue(newCarYearOfIssue);
			iCarDAO.updateCarQualityClass(CarQualityClass.getByName(newCarQualityClass_s));
			iCarDAO.updateCarRentalCost(newCarRentalCost);
			iCarDAO.updateCarStatus(CarStatus.getByName(newCarStatus_s));
			*/
			
		} catch (DBException dbExcep) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ALL_CAR_PARAMETRS, dbExcep);
		}
		
		session.setAttribute("responseMessage", responseMessage);
		return Path.COMMAND_REDIRECT_ADMIN_EDIT_CAR;
	}
}
