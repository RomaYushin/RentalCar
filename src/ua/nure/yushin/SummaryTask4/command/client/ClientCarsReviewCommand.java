package ua.nure.yushin.SummaryTask4.command.client;

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
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.exception.AppException;

public class ClientCarsReviewCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 827274973569206836L;
	
	private static final Logger LOG = Logger.getLogger(OpenClientOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info("Start executing ClientCarsReviewCommand.execute");
		String result = null;
		
		if (requestMethodType.equals(ActionType.POST)) {
			result = doPost (request, response);
		} else {
			result = doGet (request, response);
		}
		
		LOG.info("End executing UserAuthorizationCommand.execute");
		return result;
	}
	
	private String doPost (HttpServletRequest request, HttpServletResponse response) throws AppException {
		return null;		
	}
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.info("Start executing ClientCarsReviewCommand.doPost");
		List <Car> cars = null;
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		cars = iCarDAO.getAllCarsFromDB();
		
		request.setAttribute("cars", cars);
		
		return Path.PAGE_FORWARD_CLIENT_CARS_REVIEW; 
	}

}
