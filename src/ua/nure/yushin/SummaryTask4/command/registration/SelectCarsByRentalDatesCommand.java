package ua.nure.yushin.SummaryTask4.command.registration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;

public class SelectCarsByRentalDatesCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6634234636767849620L;

	private static final Logger LOG = Logger.getLogger(SelectCarsByRentalDatesCommand.class);

	private List<Car> availableCars = null;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {
		
		LOG.info ("Start executing SelectCarsByRentalDatesCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing SelectCarsByRentalDatesCommand.execute");
		return result;
	}

	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		return Path.COMMAND_REDIRECT_CLIENT_AVAILABLE_CARS_ASYNC;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {

		LOG.debug("Start executing SelectCarsByRentalDatesCommand.doGet");
		// String result = null;

		Date orderStartDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_START_DATE));
		Date orderEndDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_END_DATE));
		String sortingType = request.getParameter("sortingType");
		
		
		LOG.info("orderStartDate: " + orderStartDate);
		LOG.info("orderEndDate: " + orderEndDate);
		LOG.info("sortingType: " + sortingType);

		// проверка если введенные даты позже, чем сегодня
		long currentTime = System.currentTimeMillis();
		if ((orderStartDate.getTime() < currentTime) || (orderEndDate.getTime() < currentTime)) {
			// выдать предупреждение
		}

		List<Date> busyDates = fillBusyDates(orderStartDate, orderEndDate);

		availableCars = fillAvailableCars(busyDates);

		// сортировка авто
		if (sortingType != null) {
			switch (sortingType) {
			case "sortByPriceDESC":
				Collections.sort(availableCars, new Comparator<Car>() {
					@Override
					public int compare(Car car1, Car car2) {
						return car2.getCarRentalCost() - car1.getCarRentalCost();
					}
				});
				break;
			case "sortByPriceASC":
			default:
				Collections.sort(availableCars, new Comparator<Car>() {
					@Override
					public int compare(Car car1, Car car2) {
						return car1.getCarRentalCost() - car2.getCarRentalCost();
					}
				});
				break;
			}
		}

		LOG.info("availableCars" + availableCars.size());
		request.setAttribute("availableCars", availableCars);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("availableCars", availableCars);

		// return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		return Path.PAGE_FORWARD_CLIENT_AVAILABLE_CARS_ASYNC;
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

	private List<Car> fillAvailableCars(List<Date> busyDates) {
		List<Car> availableCars = null;
		List<Car> allCarsFromDB = null;

		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO carDaO = daoFactory.getCarDAO();
		allCarsFromDB = carDaO.getAllCarsFromDB();
		availableCars = allCarsFromDB;

		for (Car c : allCarsFromDB) {
			List<Date> carBusyDates = c.getCarBusyDates();
			for (Date d : carBusyDates) {
				if (busyDates.contains(d)) {
					availableCars.remove(c);
				}
			}
		}
		return availableCars;
	}
}
