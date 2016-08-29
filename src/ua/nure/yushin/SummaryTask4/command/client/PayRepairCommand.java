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

public class PayRepairCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2818733379152313836L;
	
	private static final Logger LOG = Logger.getLogger(PayRepairCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info("Start executing PayRepairCommand.execute");
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
		LOG.info("PayRepairCommand.doPost start");
		
		Order order = null;
		int repairPayment = 0;
		int orderId = 0;		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		HttpSession session = request.getSession(false);
		
		try {
			LOG.info("repairPayment: " + request.getParameter("repairPayment"));
			LOG.info("orderId: " + request.getParameter("orderId"));
			
			repairPayment = Integer.valueOf(request.getParameter("repairPayment"));
			orderId = Integer.valueOf(request.getParameter("orderId"));	
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		order = iOrderDAO.getOrderById(orderId);
		ValidatorOfInputParameters.validateEnoughManyForRent(order.getOrderAccount().getAccountForRepair(), repairPayment);
		
		iAccountDAO.updateAccountForRepairByOrderId(orderId, true);
		session.setAttribute("payment", true);
		
		return Path.COMMAND_REDIRECT_CLIENT_PAY_REPAIR;
	}
	
	private String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.info("PayRepairCommand.doGet start");		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("payment") == null ) {
			LOG.error(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}
		
		boolean payment = (boolean)(session.getAttribute("payment"));
		session.removeAttribute("payment");

		LOG.info("payment: " + payment);
		request.setAttribute("payment", payment);
		
		return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
	}

}
