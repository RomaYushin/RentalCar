package ua.nure.yushin.SummaryTask4.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class DeleteOrderCommand extends AbstractCommand {

	private static final long serialVersionUID = 2907609196339163468L;

	private static final Logger LOG = Logger.getLogger(DeleteOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws AppException {

		LOG.info("Start executing DeleteOrderCommand execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info("End executing DeleteOrderCommand execute");
		return result;
	}

	private String doPost (HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.info("PayOrderCommand.doPost start");
		int orderId = 0;
		
		try {
			LOG.info("orderId: " + orderId);
			orderId = Integer.valueOf(request.getParameter("orderId"));
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		ValidatorOfInputParameters.validateId(orderId);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();	
		Order order = iOrderDAO.getOrderById(orderId);
		
		// если заказ не Rejected то удалять запрещено
		/*
		LOG.info("order.getOrderStatus(): " + order.getOrderStatus());
		LOG.info("OrderStatus.REJECTED: " + OrderStatus.REJECTED);
		LOG.info("true/false: " + order.getOrderStatus().equals(OrderStatus.REJECTED));
		*/
		
		if (!order.getOrderStatus().equals(OrderStatus.UNTREATED)) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_DELETE_ORDER);
			throw new AppException(ExceptionMessages.EXCEPTION_CAN_NOT_DELETE_ORDER);
		}
		
		iOrderDAO.deleteOrderById(orderId);
		
		LOG.info("PayOrderCommand.doPost end");
		HttpSession session = request.getSession(false);
		session.setAttribute("deleting", true);
		
		return Path.COMMAND_REDIRECT_CLIENT_DELETE_ORDER;
	}

	private String doGet (HttpServletRequest request, HttpServletResponse response) {		
		LOG.info ("Start executing DeleteOrderCommand.doGet");	
		
		LOG.info("PayOrderCommand.doGet start");		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("deleting") == null ) {
			LOG.error("invalid doGet request");
			return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		}
		
		//boolean payment = (boolean)(session.getAttribute("payment"));
		session.removeAttribute("deleting");

		//LOG.info("payment: " + payment);
		//request.setAttribute("payment", payment);
		request.setAttribute("responseMessage", "cliPerArea.jsp.succesfullDeleting");
		
		return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
	}
}
