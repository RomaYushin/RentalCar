package ua.nure.yushin.SummaryTask4.command.client;

import java.sql.Date;
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
import ua.nure.yushin.SummaryTask4.entity.Car;

public class CalculateTotalPriceCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5757289941972401223L;
	
	private static final Logger LOG = Logger.getLogger(CalculateTotalPriceCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {
		
		LOG.info ("Start executing CalculateTotalPriceCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing CalculateTotalPriceCommand.execute");
		return result;
	}
	
	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		
		LOG.info("Start executing CalculateTotalPriceCommand");
		
		boolean isDriverNeed = Boolean.valueOf(request.getParameter("driver"));
		Date orderStartDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_START_DATE));
		Date orderEndDate = Date.valueOf(request.getParameter(FieldsInJSPPages.ORDER_END_DATE));
		int carId = Integer.valueOf(request.getParameter("carId"));
		//List <Car> cars = (List<Car>) request.getAttribute("availableCars");
		HttpSession session = request.getSession();
		//List <Car> cars = (List<Car>) session.getAttribute("availableCars");
		Map <String , List<Car>> availableCars_map = (Map <String , List<Car>>) session.getAttribute("availableCars_map");
		
		LOG.info("isDriverNeed: " + isDriverNeed);
		LOG.info("orderStartDate: " + orderStartDate);
		LOG.info("orderEndDate: " + orderEndDate);
		LOG.info("carId: " + carId);
		//LOG.info("cars: " + cars);
		
		// водитель
		int driverKoef = 1;
		if (isDriverNeed) {
			driverKoef = 2;
		}
		
		// даты
		long oneDayInMilisecond = 1000*60*60*24;
		int rentalTime = (int)((orderEndDate.getTime() - orderStartDate.getTime())/oneDayInMilisecond)+1;
		int carRentalCost = 0;
		
		for (Car c : availableCars_map.get("availableCarsAfterSelect")) {
			//LOG.info("car_id: " + c.getId() + " carRentelCost: " + c.getCarRentalCost());
			if (c.getId() == carId) {
				carRentalCost = c.getCarRentalCost();
			}			
		}

		int totalPrice = driverKoef*rentalTime*carRentalCost;
		
		LOG.info("driverKoef: " + driverKoef);
		LOG.info("rentalTime: " + rentalTime);
		LOG.info("carRentalCost: " + carRentalCost);
		LOG.info("totalPrice: " + totalPrice);
		
		return Path.COMMAND_REDIRECT_CLIENT_TOTAL_PRICE_ASYNC + "&totalPrice=" + totalPrice;
	}
	
	private String doGet (HttpServletRequest request, HttpServletResponse response) {
		int totalPrice = Integer.valueOf (request.getParameter("totalPrice"));
		request.setAttribute("totalPrice", totalPrice);
		return Path.PAGE_FORWARD_CLIENT_TOTAL_PRICE_ASYNC;
	}
}
