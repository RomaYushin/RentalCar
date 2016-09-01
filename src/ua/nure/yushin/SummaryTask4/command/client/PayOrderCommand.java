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
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class PayOrderCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064099726128875019L;

	private static final Logger LOG = Logger.getLogger(PayOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws AppException {
		LOG.info("Start executing PayOrderCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info("End executing PayOrderCommand.execute");
		return result;
	}

	private String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		// получение счета по id заказа и изменение его переменной на оплачено
		// вернуть сообщение об успешной оплате
		LOG.info("PayOrderCommand.doPost start");
		
		Order order = null;
		int rentPayment = 0;
		int orderId = 0;		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		HttpSession session = request.getSession(false);
		
		try {
			LOG.info("rentPayment: " + request.getParameter("rentPayment"));
			LOG.info("orderId: " + request.getParameter("orderId"));
			
			rentPayment = Integer.valueOf(request.getParameter("rentPayment"));
			orderId = Integer.valueOf(request.getParameter("orderId"));	
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}		
		order = iOrderDAO.getOrderById(orderId);
		// достаточно ли денег пришло
		ValidatorOfInputParameters.validateEnoughManyForRent(order.getOrderAccount().getAccountForRent(), rentPayment);
		
		iAccountDAO.updateAccountForRentByOrderId(orderId, true);
		
		session.setAttribute("payment", true);
		
		return Path.COMMAND_REDIRECT_CLIENT_PAY_ORDER;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		
		LOG.info("PayOrderCommand.doGet start");		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("payment") == null ) {
			LOG.error("invalid doGet request");
			return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		}
		
		//boolean payment = (boolean)(session.getAttribute("payment"));
		session.removeAttribute("payment");

		//LOG.info("payment: " + payment);
		//request.setAttribute("payment", payment);
		request.setAttribute("responseMessage", "cliPerArea.jsp.succesfullRentPayment");
		
		return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
	}

}
