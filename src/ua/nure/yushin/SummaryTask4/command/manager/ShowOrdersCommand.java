package ua.nure.yushin.SummaryTask4.command.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mysql.fabric.xmlrpc.base.Param;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.command.ICommand;
import ua.nure.yushin.SummaryTask4.command.admin.ShowAllCarsFormCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class ShowOrdersCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1692558784629463374L;
	
	private static final Logger LOG = Logger.getLogger(ShowOrdersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing ShowOrdersCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		
		LOG.info ("End executing ShowOrdersCommand.execute");
		return result;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response) 
			throws AsyncResponseException {	
		
		LOG.info ("Start executing ShowOrdersCommand.execute");
		HttpSession session = request.getSession(false);
		
		String tableName = null;
		String sortingType = request.getParameter("sortingType");
		List <Order> orders = null;
		LOG.debug("sortingType" + sortingType);
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		
		try {
			if (sortingType == null) {
				throw new AsyncResponseException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
			}
			switch (sortingType) {
			case "showUntreatedOrders":	
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.UNTREATED);
				// UNTREATED ORDERS
				tableName = "managerPersonalArea.jsp.untreatedOrders_tbl";
				break;
			case "showActiveOrders" :
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.ACTIVE);
				// ACTIVE ORDERS
				tableName = "managerPersonalArea.jsp.activeOrders_tbl";
				break;
			case "showClosedOrders":
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.CLOSE);
				// CLOSE ORDERS
				tableName = "managerPersonalArea.jsp.closedOrders_tbl";
				break;
			case "showRejectedOrders":
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.REJECTED);
				// REJECTED ORDERS
				tableName = "managerPersonalArea.jsp.rejectedOrders_tbl";
				break;	
			case "showAllOrders":
			default:
				orders = iOrderDAO.getAllOrdersFromDB();
				// ALL ORDERS
				tableName = "managerPersonalArea.jsp.allOrders_tbl";			
				break;				
			}					
		} catch (DBException dbExcep) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ORDERS, dbExcep);
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ORDERS, dbExcep);		
		}
		
		session.setAttribute("sortingType", sortingType);
		request.setAttribute("orders", orders);
		request.setAttribute("tableName", tableName);
		
		LOG.info ("End executing ShowOrdersCommand.execute");		
		return Path.PAGE_FORWARD_MANAGER_SHOW_ORDERS_FORM;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response) 
			throws AsyncResponseException {
		return Path.COMMAND_REDIRECT_MANAGER_SHOW_ORDERS_COMMAND;
	}
	

}
