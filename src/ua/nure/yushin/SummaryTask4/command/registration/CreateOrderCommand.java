package ua.nure.yushin.SummaryTask4.command.registration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.FieldsInJSPPages;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Account;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.DBException;

public class CreateOrderCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7521929337525097400L;
	
	private static final Logger LOG = Logger.getLogger(CreateOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws DBException {

		LOG.info ("Start executing CreateOrderCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing CreateOrderCommand.execute");
		return result;
	}
	
	private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
		
		LOG.info ("Start executing CreateOrderCommand.doPost");
		HttpSession session = request.getSession(false);
		//String paramForCommand = null;
		
		int carIdFromRequest = Integer.parseInt(request.getParameter("carId"));
		User userFromSession = (User) session.getAttribute("user");		
		boolean driverFromRequest = Boolean.valueOf(request.getParameter("driver"));
		Date orderStartDateFromRequest = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_START_DATE));
		Date orderEndDateFromRequest = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_END_DATE));
		double totalPriceFromRequest = (Double.valueOf(request.getParameter("totalPrice")));
		
		LOG.info("carIdFromRequest: " + carIdFromRequest);
		LOG.info("userId: " + userFromSession.getId());
		LOG.info("driverFromRequest: " + driverFromRequest);
		LOG.info("orderStartDateFromRequest: " + orderStartDateFromRequest);
		LOG.info("orderEndDateFromRequest: " + orderEndDateFromRequest);
		LOG.info("totalPriceFromRequest: " + totalPriceFromRequest);
		
		// валидация входных данных
		
		// 1. сформировать счет
		Account newAccount = new Account((int)totalPriceFromRequest);		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		iAccountDAO.insertNewAccount(newAccount);
		
		// если счет не добавлен
		if (newAccount.getId() == -1) {
			LOG.error("ERROR New Account wasn't created");
			request.setAttribute("errorMessage", " New Account wasn't created");
			//paramForCommand = "&createOrder=false";
			session.setAttribute("createOrder", false);
			return Path.COMMAND_REDIRECT_CLIENT_CREATE_ORDER;// + paramForCommand;
			//return Path.COMMAND_REDIRECT_CLIENT_PERSONAL_AREA;
		}		
		
		// 2. создание объекта заказ
		// 2.1 Получение авто по id
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		Car carFromDB = iCarDAO.getCarById(carIdFromRequest);
		
		// 2.2 Формирование объекта заказ	
		Order newOrder = new Order(carFromDB, userFromSession, driverFromRequest, 
				orderStartDateFromRequest, orderEndDateFromRequest, newAccount);
		
		// 2.3 запись заказа в БД
		List<Date> busyDates = fillBusyDates(orderStartDateFromRequest, orderEndDateFromRequest);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();		
		iOrderDAO.insertNewOrder(newOrder, busyDates);
		
		// если заказ не добавлен
		if (newOrder.getId() == -1) {
			LOG.error("ERROR NewOrder wasn't created");
			//request.setAttribute("errorMessage", " New Order wasn't created");
			//paramForCommand = "&createOrder=false";
			session.setAttribute("createOrder", false);
			return Path.COMMAND_REDIRECT_CLIENT_CREATE_ORDER;// + paramForCommand;
		}
				
		session.setAttribute("newOrder", newOrder);
		session.setAttribute("createOrder", true);
		
		//paramForCommand = "&createOrder=true";
		LOG.info ("End executing CreateOrderCommand");
		return Path.COMMAND_REDIRECT_CLIENT_CREATE_ORDER;// + paramForCommand;
	}
	
	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		
		LOG.info ("Start executing CreateOrderCommand.doGet");
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("createOrder") == null || session.getAttribute("newOrder") == null) {
			LOG.error("invalid doGet request");
			return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		}
		
		boolean isCreateOrderSuccess = (boolean)(session.getAttribute("createOrder"));
		Order newOrder = (Order) session.getAttribute("newOrder");
		
		session.removeAttribute("createOrder");
		session.removeAttribute("newOrder");
		
		LOG.info("newOrder in doGet: " + newOrder);
		LOG.info("isCreateOrderSuccess: " + isCreateOrderSuccess);
		
		if (!isCreateOrderSuccess) {
			// вернуть сообщение об ошибке при формированиии заказа
			request.setAttribute("isCreateOrderSuccess", false);
			request.setAttribute("errorMessage", "New order wasn't created");
			return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		}
		
		request.setAttribute("isCreateOrderSuccess", true);
		request.setAttribute("newOrder", newOrder);
		
		
		// действия в случае успешного заказа
		// получить данные из заказа по id
		
		return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;	
	}
	
	private List<Date> fillBusyDates(Date orderStartDate, Date orderEndDate) {

		List<Date> busyDates = new ArrayList<>();
		long startDateInMilisecond = orderStartDate.getTime();
		long endDateInMilisecond = orderEndDate.getTime();
		long oneDayInMilisecond = 1000 * 60 * 60 * 24;
		long nextDateInMilisecond = startDateInMilisecond;

		while (true) {
			// ++amountOfBusyDates;
			Date nextDate = new Date(nextDateInMilisecond);
			busyDates.add(nextDate);
			if ((nextDateInMilisecond += oneDayInMilisecond) > endDateInMilisecond) {
				break;
			}
		}
		return busyDates;
	}
	
	

}
