package ua.nure.yushin.SummaryTask4.command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.command.ICommand;
import ua.nure.yushin.SummaryTask4.command.admin.RegisterNewCarCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class ShowSpecifiedOrderCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7720333559650283023L;
	
	private static final Logger LOG = Logger.getLogger(ShowSpecifiedOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing ShowSpecifiedOrderCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing ShowSpecifiedOrderCommand.execute");
		return result;
	}

	private static String doGet (HttpServletRequest request, HttpServletResponse response)
			throws DBException, AsyncResponseException {
		
		LOG.info ("Start executing ShowSpecifiedOrderCommand.doGet");
		HttpSession session = request.getSession(false);
		Order order = null;
		try {
			order = (Order) session.getAttribute("order");
			session.removeAttribute("order");
		} catch (Exception e) {
			throw new AsyncResponseException (ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR, e);
		}
		request.setAttribute("order", order);
		
		return Path.PAGE_FORWARD_MANAGER_SHOW_SPECIFIED_ORDER;
		
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response)
			throws DBException, AsyncResponseException {
		
		LOG.info ("Start executing ShowSpecifiedOrderCommand.doPost");
		HttpSession session = request.getSession(false);
		int orderId = 0;
		Order order = null;
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		
		try {
			orderId = Integer.parseInt(request.getParameter("orderId"));
		} catch (Exception e) {
			throw new AsyncResponseException (ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR, e);
		}
		
		LOG.info("orderId: " + orderId);
		
		try {
			ValidatorOfInputParameters.validateId(orderId);
		} catch (ValidationException e) {
			throw new AsyncResponseException(e.getMessage(), e);
		}
		
		try {
			order = iOrderDAO.getOrderById(orderId);
		} catch (DBException e) {
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_CAR, e);
		}	
		
		session.setAttribute("order", order);
		
		return Path.COMMAND_REDIRECT_MANAGER_SHOW_SPECIFIED_ORDER_COMMAND;
		
	}
	
}
