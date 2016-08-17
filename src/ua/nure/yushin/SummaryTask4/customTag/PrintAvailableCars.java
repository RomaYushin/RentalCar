package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.Car;

public class PrintAvailableCars extends SimpleTagSupport {

	private static final Logger LOG = Logger.getLogger(PrintAvailableCars.class);

	private List<Car> availableCars;

	public List<Car> getAvailableCars() {
		return availableCars;
	}

	public void setAvailableCars(List<Car> availableCars) {
		LOG.info("availableCars before" + availableCars);
		this.availableCars = availableCars;
		LOG.info("availableCars after" + availableCars);
	}

	@Override
	public void doTag() throws JspException, IOException {

		JspWriter jspOut = getJspContext().getOut();

		if (availableCars == null) {
			LOG.info("no availableCars in doTag");
			// jspOut.println("<h1> HELLO! </h1>");
		} else {
			LOG.info("availableCars in doTag:" + availableCars);
			jspOut.println("3. Select car:");
			// добавить кнопки сортировки
			jspOut.println("<div class = \"sortButtons\">");
			jspOut.println("<button id = \"sortByPriceASC\"> Sort from cheap to expensive  </button>");
			jspOut.println("<button id = \"sortByPriceDESC\"> Sort from expensive to cheap  </button>");
			jspOut.println("</div>"); // end sortButtons
			
			for (Car car : availableCars) {
				
				jspOut.println("<div class = \"car\">");
				jspOut.println("<input type = \"radio\" name = \"car\" id = \"" + car.getId() + "\" />");
				
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(car.getCarYearOfIssue());
				jspOut.print(car.getCarBrend() + " " + car.getCarModel() + " " + gc.get(Calendar.YEAR));
				
				jspOut.println(";  Class: ");
				jspOut.println(car.getCarQualityClass().toString().toLowerCase());
				
				jspOut.println(";  Rental cost: ");
				jspOut.println(car.getCarRentalCost() + "$");
				
				jspOut.println("</div>"); // end div car
			}
			jspOut.println("4. Total price:");
			jspOut.println("<div class = \"totalPrice\">");
			jspOut.println("</div>"); // end totalPrice
			
			jspOut.println("<div class = \"createOrder\">");
			jspOut.println("<form id=\"createOrderForm\" action=\"Controller\" method=\"POST\">");
			jspOut.println("<input type = \"hidden\" name = \"command\" value = \"createOrder\" />" );
			jspOut.println("<input id = \"exit\" type=\"submit\" value=\"Create new order\" />");
			jspOut.println("</form>");
			jspOut.println("</div>"); // end createOrder
			
		}
	}
}
