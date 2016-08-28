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
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class ClientNotClosedOrdersCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4733777027506933627L;
	
	private static final Logger LOG = Logger.getLogger(ClientNotClosedOrdersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws AppException {

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
	
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.info("MyOrdersCommand.doGet");
		
		HttpSession session = request.getSession(false);
		List <Order> orders = null;
		try {
			orders = (List<Order>) session.getAttribute("clientNotClosedOrders");
			session.removeAttribute("clientNotClosedOrders");
		} catch (Exception e) {
			LOG.error(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		request.setAttribute("clientNotClosedOrders", orders);
		
		return Path.PAGE_FORWARD_CLIENT_CLIENT_NOT_CLOSED_ORDERS;
	}
	
	private String doPost (HttpServletRequest request, HttpServletResponse response) throws AppException {		
		
		LOG.info("MyOrdersCommand.doPost");
		
		int clientId = 0;
		List <Order> orders = null;
		
		try {
			clientId = Integer.valueOf(request.getParameter("clientId"));
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		ValidatorOfInputParameters.validateId(clientId);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		orders = iOrderDAO.getOrdersByClientIdAndOrderStatus(clientId, OrderStatus.CLOSE);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("clientNotClosedOrders", orders);
		
		return Path.COMMAND_REDIRECT_CLIENT_NOT_CLOSED_ORDERS;
	}

}
