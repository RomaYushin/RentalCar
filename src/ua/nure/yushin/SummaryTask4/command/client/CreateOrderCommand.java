package ua.nure.yushin.SummaryTask4.command.client;

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
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class CreateOrderCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7521929337525097400L;

	private static final Logger LOG = Logger.getLogger(CreateOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {

		LOG.info("Start executing CreateOrderCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info("End executing CreateOrderCommand.execute");
		return result;
	}

	private String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.info("Start executing CreateOrderCommand.doPost");
		HttpSession session = request.getSession(false);
		int carId = 0;
		User userFromSession = null;
		boolean driver = false;
		Date orderStartDate = null;
		Date orderEndDate = null;
		double totalPrice = 0.0;
		
		try {
			carId = Integer.parseInt(request.getParameter("carId"));
			userFromSession = (User) session.getAttribute("user");
			driver = Boolean.valueOf(request.getParameter("driver"));
			orderStartDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_START_DATE));
			orderEndDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_END_DATE));
			totalPrice = (Double.valueOf(request.getParameter("totalPrice")));

			LOG.info("carIdFromRequest: " + carId);
			LOG.info("userId: " + userFromSession.getId());
			LOG.info("driverFromRequest: " + driver);
			LOG.info("orderStartDateFromRequest: " + orderStartDate);
			LOG.info("orderEndDateFromRequest: " + orderEndDate);
			LOG.info("totalPriceFromRequest: " + totalPrice);
		} catch (Exception e) {
			throw new AppException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR);
		}

		ValidatorOfInputParameters.validateId(carId);
		ValidatorOfInputParameters.validateOrderDate(orderStartDate, orderEndDate);
		ValidatorOfInputParameters.validateCarRentalCost((int)totalPrice);
		
		// еще раз проверить, не занят ли авто
		
		//
		Account newAccount = new Account((int) totalPrice);

		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		Car carFromDB = iCarDAO.getCarById(carId);

		// 1. сформировать счет

		// IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		// iAccountDAO.insertNewAccount(newAccount);

		/*
		 * // если счет не добавлен if (newAccount.getId() == -1) { LOG.error(
		 * "ERROR New Account wasn't created");
		 * request.setAttribute("errorMessage", " New Account wasn't created");
		 * //paramForCommand = "&createOrder=false";
		 * session.setAttribute("createOrder", false); return
		 * Path.COMMAND_REDIRECT_CLIENT_CREATE_ORDER;// + paramForCommand;
		 * //return Path.COMMAND_REDIRECT_CLIENT_PERSONAL_AREA; }
		 */

		// 2.2 Формирование объекта заказ
		Order newOrder = new Order(carFromDB, userFromSession, driver, orderStartDate,
				orderEndDate, newAccount);
		// ********************************

		// 2.3 запись заказа в БД
		List<Date> busyDates = fillBusyDates(orderStartDate, orderEndDate);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		iOrderDAO.insertNewOrder(newOrder, busyDates);

		/*
		// если заказ не добавлен
		if (newOrder.getId() == -1) {
			LOG.error("ERROR NewOrder wasn't created");
			// request.setAttribute("errorMessage", " New Order wasn't
			// created");
			// paramForCommand = "&createOrder=false";
			session.setAttribute("createOrder", false);
			return Path.COMMAND_REDIRECT_CLIENT_CREATE_ORDER;// +
																// paramForCommand;
		}
		*/

		session.setAttribute("newOrder", newOrder);
		session.setAttribute("createOrder", true);

		// paramForCommand = "&createOrder=true";
		LOG.info("End executing CreateOrderCommand");
		return Path.COMMAND_REDIRECT_CLIENT_CREATE_ORDER;// + paramForCommand;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {

		LOG.info("Start executing CreateOrderCommand.doGet");

		HttpSession session = request.getSession(false);

		if (session.getAttribute("createOrder") == null || session.getAttribute("newOrder") == null) {
			LOG.error("invalid doGet request");
			return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		}

		boolean isCreateOrderSuccess = (boolean) (session.getAttribute("createOrder"));
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
