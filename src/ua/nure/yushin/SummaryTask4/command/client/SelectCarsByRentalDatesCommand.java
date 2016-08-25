package ua.nure.yushin.SummaryTask4.command.client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class SelectCarsByRentalDatesCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6634234636767849620L;

	private static final Logger LOG = Logger.getLogger(SelectCarsByRentalDatesCommand.class);

	private Map <String , List<Car>> availableCars_map = new HashMap<>();
	private List<Car> availableCarsSortByDate = null;
	private List<Car> availableCarsAfterSelect = null;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws DBException, ValidationException {
		
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

	private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException, ValidationException {
		
		LOG.debug("Start executing SelectCarsByRentalDatesCommand.doGet");
		
		HttpSession session = request.getSession(false);
		List<Date> busyDates = null;

		Date orderStartDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_START_DATE));
		Date orderEndDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_END_DATE));
		String sortingType = request.getParameter("sortingType");
		String selectType = request.getParameter("selectType");
		String selectOption =  request.getParameter("selectOption");
		
		LOG.info("orderStartDate: " + orderStartDate);
		LOG.info("orderEndDate: " + orderEndDate);
		LOG.info("sortingType: " + sortingType);
		LOG.info("selectType: " + selectType);
		LOG.info("selectOption: " + selectOption);

		/*
		// проверка если введенные даты позже, чем сегодня
		long currentTime = System.currentTimeMillis();
		if ((orderStartDate.getTime() < currentTime) || (orderEndDate.getTime() < currentTime)) {
			session.setAttribute("availableCars_map", null);
			LOG.error("Invalid input dates, early that today:" + orderStartDate + " " + orderEndDate);
			return Path.COMMAND_REDIRECT_CLIENT_SELECT_CARS_BY_RENTAL_DATES_ASYNC;
		}
		
		// проверка, если дата старта позже даты начала
		if (orderStartDate.getTime() >= orderEndDate.getTime()) {
			session.setAttribute("availableCars_map", null);
			LOG.error("Invalid input dates, early that today:" + orderStartDate + " " + orderEndDate);
			return Path.COMMAND_REDIRECT_CLIENT_SELECT_CARS_BY_RENTAL_DATES_ASYNC;
		}
		*/
		
		ValidatorOfInputParameters.validateOrderDate(orderStartDate, orderEndDate);

		busyDates = fillBusyDates(orderStartDate, orderEndDate);
		availableCarsSortByDate = fillAvailableCars(busyDates);

		// сортировка авто
		if (sortingType != null) {
			switch (sortingType) {
			case "sortByCarName":
				Collections.sort(availableCarsSortByDate, new Comparator<Car>() {
					@Override
					public int compare(Car car1, Car car2) {
						int result = car1.getCarBrend().compareToIgnoreCase(car2.getCarBrend());
						if (result != 0) {
							return result;
						}						
						return car1.getCarModel().compareToIgnoreCase(car2.getCarModel());
					}
				});				
			break;
			case "sortByPriceDESC":
				Collections.sort(availableCarsSortByDate, new Comparator<Car>() {
					@Override
					public int compare(Car car1, Car car2) {
						return car2.getCarRentalCost() - car1.getCarRentalCost();
					}
				});
				break;
			case "sortByPriceASC":
			default:
				Collections.sort(availableCarsSortByDate, new Comparator<Car>() {
					@Override
					public int compare(Car car1, Car car2) {
						return car1.getCarRentalCost() - car2.getCarRentalCost();
					}
				});
				break;
			}
		}
		
		availableCars_map.put("availableCarsSortByDate", availableCarsSortByDate);
		
		// выборка авто по бренду или по классу
		if ( selectType != null) {
			switch (selectType) {			
				case "selectCarBrend":
					availableCarsAfterSelect = new ArrayList<>();
					for (Car c: availableCarsSortByDate) {
						availableCarsAfterSelect.add(c);
					}
					for (Car car: availableCarsSortByDate) {
						if (!selectOption.equals(car.getCarBrend())){
							availableCarsAfterSelect.remove(car);
						}
					}
					break;
				
				case "selectCarQualityClass":
					availableCarsAfterSelect = new ArrayList<>();
					for (Car c: availableCarsSortByDate) {
						availableCarsAfterSelect.add(c);
					}
					for (Car car: availableCarsSortByDate) {
						if (!selectOption.equalsIgnoreCase(car.getCarQualityClass().toString().toLowerCase())){
							availableCarsAfterSelect.remove(car);
						}
					}
				break;				
			}
		} else {
			availableCarsAfterSelect = availableCarsSortByDate;
		}
		availableCars_map.put("availableCarsAfterSelect", availableCarsAfterSelect);
		
		//LOG.info("availableCars" + availableCars.size());
		session.setAttribute("availableCars_map", availableCars_map);
		
		return Path.COMMAND_REDIRECT_CLIENT_SELECT_CARS_BY_RENTAL_DATES_ASYNC;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {
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
	
	private List<Car> fillAvailableCars(List<Date> busyDates) throws DBException {
		List<Car> availableCars = new ArrayList<>();
		List<Car> allCarsFromDB = null;

		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO carDaO = daoFactory.getCarDAO();
		allCarsFromDB = carDaO.getAllCarsFromDB();
		
		for (Car c: allCarsFromDB) {
			availableCars.add(c);
		}
		
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
