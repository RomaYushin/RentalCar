package ua.nure.yushin.SummaryTask4.command.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String tableName = null;
		String sortingType = request.getParameter("sortingType");
		Map <String, List> orders_map = new HashMap<>();
		List <String> param = new ArrayList<>();
		List <Order> orders = null;
		
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		
		try {
			if (sortingType == null) {
				throw new AsyncResponseException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
			}
			switch (sortingType) {
			case "showUntreatedOrders":	
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.UNTREATED);
				tableName = " UNTREATED ORDERS";
				break;
			case "showActiveOrders" :
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.ACTIVE);
				tableName = " ACTIVE ORDERS";
				break;
			case "showClosedOrders":
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.CLOSE);
				tableName = " CLOSE ORDERS";
				break;
			case "showRejectedOrders":
				orders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.REJECTED);
				tableName = " REJECTED ORDERS";
				break;	
			case "showAllOrders":
			default:
				orders = iOrderDAO.getAllOrdersFromDB();
				tableName = "ALL ORDERS";
				break;
			}					
		} catch (DBException dbExcep) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ORDERS, dbExcep);
			throw new AsyncResponseException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ORDERS, dbExcep);		
		}
	
		param.add(tableName);
		orders_map.put("orders", orders);
		orders_map.put("param", param);
		
		request.setAttribute("orders_map", orders_map);
		
		LOG.info ("End executing ShowOrdersCommand.execute");		
		return Path.PAGE_FORWARD_MANAGER_SHOW_ORDERS_FORM;
		
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response) 
			throws AsyncResponseException {
		return Path.COMMAND_REDIRECT_MANAGER_SHOW_ORDERS_COMMAND;
	}
	

}
