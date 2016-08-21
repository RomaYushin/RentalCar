package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.Car;

public class PrintAvailableCars extends SimpleTagSupport {

	private static final Logger LOG = Logger.getLogger(PrintAvailableCars.class);
/*
	private List<Car> availableCars;

	public List<Car> getAvailableCars() {
		return availableCars;
	}

	public void setAvailableCars(List<Car> availableCars) {
		LOG.info("availableCars before" + availableCars);
		this.availableCars = availableCars;
		LOG.info("availableCars after" + availableCars);
	}
*/
	
	private Map <String , List<Car>> availableCars_map;
	
	public Map<String, List<Car>> getAvailableCars_map() {
		return availableCars_map;
	}
	
	public void setAvailableCars_map(Map<String, List<Car>> availableCars_map) {
		this.availableCars_map = availableCars_map;
	}

	@Override
	public void doTag() throws JspException, IOException {

		JspWriter jspOut = getJspContext().getOut();
		Set <String> setOfCarBrands = new HashSet<>();
		Set <String> setOfCarClass = new HashSet<>();

		if (availableCars_map == null) {
			LOG.info("no availableCars in doTag");
		} else {
			LOG.info("availableCars in doTag:" + availableCars_map);
			
			jspOut.println("2. Select car:");
			jspOut.println("<div class = \"sortButtons\">");
			
			jspOut.println("<button id = \"sortByPriceASC\" onclick =\"sortByPriceASC()\" > Sort from cheap to expensive  </button>");
			jspOut.println("<button id = \"sortByPriceDESC\" onclick =\"sortByPriceDESC()\" > Sort from expensive to cheap  </button>");
			jspOut.println("<button id = \"sortByCarName\" onclick =\"sortByCarName()\" > Sort by name  </button>");
			
			jspOut.println("<select id = \"carBrend_select\" onchange =\"selectCarBrend()\">");	
			jspOut.println("<option> Select car brend </option>");
			for (Car car: availableCars_map.get("availableCarsSortByDate")) {
				if (!setOfCarBrands.contains(car.getCarBrend())) {
					setOfCarBrands.add(car.getCarBrend());
					jspOut.println("<option id =\"" + car.getCarBrend()+ "\" name = \"carBrend_option\"> " + car.getCarBrend() + "</option>");					
				}				
			}	
			jspOut.println("</select>");	
			
			jspOut.println("<select id = \"carQualityClass_select\" onchange =\"selectCarQualityClass()\">");		
			jspOut.println("<option> Select car quality class </option>");
			for (Car car: availableCars_map.get("availableCarsSortByDate")) {
				if (!setOfCarClass.contains(car.getCarQualityClass().toString().toLowerCase())) {
					setOfCarClass.add(car.getCarQualityClass().toString().toLowerCase());
					jspOut.println("<option id =\"" + car.getCarQualityClass().toString().toLowerCase() +"\" "
							+ "name = \"carQualityClass_option\"> " + car.getCarQualityClass().toString().toLowerCase() + "</option>");					
				}				
			}	
			jspOut.println("</select>");			
			jspOut.println("</div>"); // end sortButtons
			
			jspOut.println("<div class = \"cars\"> ");
			for (Car car : availableCars_map.get("availableCarsAfterSelect")) {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(car.getCarYearOfIssue());
				String s = car.getCarBrend() + " " + car.getCarModel() + " " + gc.get(Calendar.YEAR);
				
				// gc.setTime(newOrder.getOrderCar().getCarYearOfIssue());
				
				jspOut.println("<div class = \"car\">");
				jspOut.println("<input type = \"radio\" name = \"car\" id = \""+ car.getId() + "\" onclick = \"selectCar()\" "
						+ "value = \"" + s + "\" />");
				jspOut.print(car.getCarBrend() + " " + car.getCarModel() + " " + gc.get(Calendar.YEAR));
				
				jspOut.println(";  Class: ");
				jspOut.println(car.getCarQualityClass().toString().toLowerCase());
				
				jspOut.println(";  Rental cost: ");
				jspOut.println(car.getCarRentalCost() + "$");
				
				jspOut.println("</div>"); // end div car
			}
			jspOut.println("</div>"); //end div cars
			
			jspOut.println();
			jspOut.println("<div class = \"driver\">");
			jspOut.println("3. Do you need personal driver?	");
			jspOut.println("<input type = \"radio\" name = \"driver\" value = \"true\" onclick = \"sendCar()\" /> Yes");
			jspOut.println("<input type = \"radio\" name = \"driver\" value = \"false\" onclick = \"sendCar()\"/> No");
			jspOut.println("</div>"); // end driver
			
			jspOut.println("<div class = \"totalPrice\">");
			//jspOut.println("4. Total price:");			
			jspOut.println("</div>"); // end totalPrice
			
			jspOut.println("<div class = \"createOrder\">");
			
			jspOut.println("<form id=\"createOrderForm\" action=\"Controller\" method=\"POST\" >");
			jspOut.println("<input type = \"hidden\" id = \"ordFormCommand_id\" name = \"command\" value = \"createOrder\" />" );
			jspOut.println("<input type = \"hidden\" id = \"ordFormStartDate_id\" name = \"orderStartDate\" value = \"\" />" );
			jspOut.println("<input type = \"hidden\" id = \"ordFormEndDate_id\" name = \"orderEndDate\" value = \"\" />" );
			jspOut.println("<input type = \"hidden\" id = \"ordFormCar_id\" name = \"carId\" value = \"\" />" );
			jspOut.println("<input type = \"hidden\" id = \"ordFormDriver_id\" name = \"driver\" value = \"\" />" );
			jspOut.println("<input type = \"hidden\" id = \"ordFormTotalPrice_id\" name = \"totalPrice\" value = \"\" />" );
			jspOut.println("</form>");
			
			jspOut.println("<button onclick =\"createNewOrder()\"> Create order </button>"); // 
			jspOut.println("</div>"); // end createOrder			
		}
	}
}
