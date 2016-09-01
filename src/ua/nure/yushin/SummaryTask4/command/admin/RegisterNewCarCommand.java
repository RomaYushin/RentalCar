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
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.util.LocaleUtil;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class RegisterNewCarCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2665791906089256538L;
	
	private static final Logger LOG = Logger.getLogger(RegisterNewCarCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) 
			throws DBException, AsyncResponseException, ValidationException {

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
		
		HttpSession session = request.getSession(false);
		String responseMessage = (String) session.getAttribute("responseMessage");
		session.removeAttribute("responseMessage");
		
		request.setAttribute("responseMessage", responseMessage);		
		return Path.PAGE_FORWARD_ADMIN_PERSONAL_AREA;
	}

	private static String doPost (HttpServletRequest request, HttpServletResponse response) throws DBException, AsyncResponseException, ValidationException {
		
		LOG.info ("Start executing RegisterNewCarCommand.doPost");
		
		String carBrend = null;
		String carModel = null;
		Date carYearOfIssue = null;
		CarQualityClass carQualityClass = null;
		int carRentalCost = 0;
		HttpSession session = request.getSession(false);
		String responseMessage = LocaleUtil.getValueByKey("adminPerArea.jsp.editCarRespMessage", session);
		
		try {
			carBrend = request.getParameter("carBrend");
			carModel = request.getParameter("carModel");
			//String carYearOfIssue_s = request.getParameter("carYearOfIssue");
			carYearOfIssue = Date.valueOf(request.getParameter("carYearOfIssue"));
			carQualityClass = CarQualityClass.getByName(request.getParameter("carQualityClass"));
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
		

		ValidatorOfInputParameters.validateCarBrend(carBrend);
		ValidatorOfInputParameters.validateCarModel(carModel);
		ValidatorOfInputParameters.validateCarYearOfIssue(carYearOfIssue);
		ValidatorOfInputParameters.validateQualityCarClass(carQualityClass);
		ValidatorOfInputParameters.validateCarRentalCost(carRentalCost);

		
		Car newCar = new Car (carBrend, carModel, carQualityClass,
				carRentalCost, CarStatus.FREE, carYearOfIssue);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		iCarDAO.insertNewCar(newCar);
			
		
		session.setAttribute("responseMessage", responseMessage);
		return Path.COMMAND_REDIRECT_ADMIN_REGISTER_NEW_CAR;
	}
}
