package ua.nure.yushin.SummaryTask4.command.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class ShowAllCarsFormCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1208721690149091904L;
	
	private static final Logger LOG = Logger.getLogger(ShowAllCarsFormCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) 
			throws AsyncResponseException {

		LOG.info ("Start executing ShowAllCarsFormCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing ShowAllCarsFormCommand.execute");
		return result;
	}

	private static String doGet (HttpServletRequest request, HttpServletResponse response) throws AsyncResponseException {	
		
		Map <String, List <Car>> allCarsFromDB_map = new HashMap<>();
		List <Car> allCarsFromDB = null;
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		try {
			allCarsFromDB = iCarDAO.getAllCarsFromDB();
		} catch (DBException dbExcep) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_CARS, dbExcep);		
		}
		allCarsFromDB_map.put("allCarsFromDB", allCarsFromDB);
		request.setAttribute("allCarsFromDB_map", allCarsFromDB_map);		
		
		return Path.PAGE_FORWARD_ADMIN_SHOW_ALL_CARS_FORM;
	}

	private static String doPost (HttpServletRequest request, HttpServletResponse response) {			
		return Path.COMMAND_REDIRECT_ADMIN_SHOW_ALL_CARS_FORM;
	}

}
