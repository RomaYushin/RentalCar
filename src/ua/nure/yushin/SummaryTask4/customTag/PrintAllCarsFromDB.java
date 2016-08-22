package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.Car;

public class PrintAllCarsFromDB extends SimpleTagSupport {

	private static final Logger LOG = Logger.getLogger(PrintAllCarsFromDB.class);
	
	private Map <String, List <Car>> allCarsFromDB_map;

	public Map<String, List<Car>> getAllCarsFromDB_map() {
		return allCarsFromDB_map;
	}

	public void setAllCarsFromDB_map(Map<String, List<Car>> allCarsFromDB_map) {
		this.allCarsFromDB_map = allCarsFromDB_map;
	}
	
	public void doTag()  throws JspException, IOException {
		
		JspWriter jspOut = getJspContext().getOut();
		int number = 0;
		
		if (allCarsFromDB_map == null) {
			LOG.error("no cars in doTag");
			return;
		}		
		LOG.info(" allCarsFromDB_map " +  allCarsFromDB_map);
		
		jspOut.println("<table border = \"1\">");
		jspOut.println("<caption> All cars from database </caption>");
		
		jspOut.println("<tr>");
		jspOut.println("<th> Number </th>");
		jspOut.println("<th> Car id </th>");
		jspOut.println("<th> Brend </th>");
		jspOut.println("<th> Model </th>");
		jspOut.println("<th> Year of issue </th>");
		jspOut.println("<th> Quality class </th>");
		jspOut.println("<th> Rental cost </th>");
		jspOut.println("<th> Edit car </th>");
		jspOut.println("<th> Remove car </th>");		
		jspOut.println("</tr>");
		
		for (Car car : allCarsFromDB_map.get("allCarsFromDB")) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(car.getCarYearOfIssue());
			
			jspOut.println("<tr>");
			jspOut.println("<td>" + ++number + "</td>");
			jspOut.println("<td>" + car.getId() + "</td>");
			jspOut.println("<td>" + car.getCarBrend() + "</td>");
			jspOut.println("<td>" + car.getCarModel() + "</td>");			
			jspOut.println("<td>" + gc.get(Calendar.YEAR) + "</td>");
			jspOut.println("<td>" + car.getCarQualityClass() + "</td>");
			jspOut.println("<td>" + car.getCarRentalCost() + "</td>");
			jspOut.println("<td> <button onclick =\"showEditCarForm("+ car.getId() +")\" > "
					+ "Edit car </button></td>");	
			jspOut.println("<td> <button onclick =\"removeCar("+ car.getId() +")\" > "
					+ "Remove car </button></td>");	
			jspOut.println("</tr>");
		}		
		jspOut.println("</table>");
	}
}
