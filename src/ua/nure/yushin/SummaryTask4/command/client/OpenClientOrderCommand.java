package ua.nure.yushin.SummaryTask4.command.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class OpenClientOrderCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6741696552509288028L;
	
	private static final Logger LOG = Logger.getLogger(OpenClientOrderCommand.class);


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info("Start executing ClientNotClosedOrdersCommand.execute");
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
		
		LOG.info("Start executing OpenClientOrderCommand.doPost");
		
		int orderId = 0;
		
		try {
			orderId = Integer.valueOf(request.getParameter("orderId"));
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		ValidatorOfInputParameters.validateId(orderId);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		Order order = iOrderDAO.getOrderById(orderId);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("clientCurrentOrder", order);
		
		return Path.COMMAND_REDIRECT_CLIENT_OPEN_ORDER;
	}
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.info("OpenClientOrderCommand.doGet start");
		
		HttpSession session = request.getSession(false);
		Order currentOrder = null;
		try {
			currentOrder = (Order) session.getAttribute("clientCurrentOrder");
			session.removeAttribute("clientCurrentOrder");
		} catch (Exception e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ORDER_BY_ID);
			throw new AppException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ORDER_BY_ID);
		}
		
		request.setAttribute("order", currentOrder);
		
		return Path.PAGE_FORWARD_CLIENT_OPEN_ORDER;
	}
	
	
	

}
