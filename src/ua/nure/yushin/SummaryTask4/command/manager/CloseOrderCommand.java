package ua.nure.yushin.SummaryTask4.command.manager;

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
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class CloseOrderCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7466360847471930749L;
	
	private static final Logger LOG = Logger.getLogger(CloseOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing CloseOrderCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing ShowSpecifiedOrderCommand.execute");
		return result;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		
		LOG.info ("Start executing CloseOrderCommand.doPost");
		
		int orderId = 0;
		int userId = 0;
		User manager = null;
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		
		try {
			orderId = Integer.parseInt(request.getParameter("orderId"));
			userId =  Integer.parseInt(request.getParameter("userId"));
		} catch (Exception e) {		
			LOG.error(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		ValidatorOfInputParameters.validateId(userId);
		ValidatorOfInputParameters.validateId(orderId);
		
		try {		
			OrderStatus os = OrderStatus.CLOSE;
			iOrderDAO.updateOrderStatusById (orderId, os);
			
			manager = iUserDAO.getUserById(userId);
			iOrderDAO.updateOrderManager(manager, orderId);			
		} catch (DBException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ORDER);
		}
		
		return Path.COMMAND_REDIRECT_MANAGER_CLOSE_ORDER;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		
		LOG.info ("Start executing CloseOrderCommand.doGet");

		HttpSession session = request.getSession(false);
		String sortingType = (String) session.getAttribute("sortingType");
		
		request.setAttribute("respMessage", "showSpecifiedOrder.jsp.succesfullClosed");
		request.setAttribute("type", "close");
		request.setAttribute("sortingType", sortingType);
		
		return Path.PAGE_FORWARD_MANAGER_SHOW_SPECIFIED_ORDER;
	}

}
