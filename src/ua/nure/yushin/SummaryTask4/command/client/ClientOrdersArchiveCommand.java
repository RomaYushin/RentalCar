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
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.exception.AppException;

public class ClientOrdersArchiveCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3236753969698338016L;
	
	private static final Logger LOG = Logger.getLogger(ClientOrdersArchiveCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info("Start executing ClientOrdersArchiveCommand.execute");
		String result = null;
		
		if (requestMethodType.equals(ActionType.POST)) {
			result = doPost (request, response);
		} else {
			result = doGet (request, response);
		}
		
		return result;
	}
	
	private String doPost (HttpServletRequest request, HttpServletResponse response) throws AppException {
		return null;		
	}
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.info("Start executing ClientOrdersArchiveCommand.doGet");
		List <Order> closedOrders = null;
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		closedOrders = iOrderDAO.getOrdersByOrderStatus(OrderStatus.CLOSE);
		
		request.setAttribute("orders", closedOrders);
		
		return Path.PAGE_FORWARD_CLIENT_ARCHIVE_ORDERS;
	}
	
	
	
	
	

}
