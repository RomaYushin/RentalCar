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

public class PrintAllCarsFromDBForEdit extends SimpleTagSupport {

	private static final Logger LOG = Logger.getLogger(PrintAllCarsFromDBForEdit.class);

	private Map<String, List<Car>> allCarsFromDBForEdit_map;

	public Map<String, List<Car>> getAllCarsFromDBForEdit_map() {
		return allCarsFromDBForEdit_map;
	}

	public void setAllCarsFromDBForEdit_map(Map<String, List<Car>> allCarsFromDBForEdit_map) {
		this.allCarsFromDBForEdit_map = allCarsFromDBForEdit_map;
	}

	public void doTag() throws JspException, IOException {

		JspWriter jspOut = getJspContext().getOut();

		if (allCarsFromDBForEdit_map == null) {
			LOG.error("no cars in doTag");
			return;
		}
		LOG.info(" allCarsFromDBForEdit_map " + allCarsFromDBForEdit_map);

		jspOut.println("<table border = \"1\">");
		jspOut.println("<caption> All cars from database </caption>");

		jspOut.println("<tr>");
		jspOut.println("<th> id </th>");
		jspOut.println("<th> brend </th>");
		jspOut.println("<th> model </th>");
		jspOut.println("<th> year of issue </th>");
		jspOut.println("<th> quality class </th>");
		jspOut.println("<th> rental cost </th>");
		jspOut.println("<th> Edit car </th>");
		jspOut.println("</tr>");

		for (Car car : allCarsFromDBForEdit_map.get("allCarsFromDBForEdit")) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(car.getCarYearOfIssue());

			jspOut.println("<tr>");
			jspOut.println("<td>" + car.getId() + "</td>");
			jspOut.println("<td>" + car.getCarBrend() + "</td>");
			jspOut.println("<td>" + car.getCarModel() + "</td>");
			jspOut.println("<td>" + gc.get(Calendar.YEAR) + "</td>");
			jspOut.println("<td>" + car.getCarQualityClass() + "</td>");
			jspOut.println("<td>" + car.getCarRentalCost() + "</td>");
			jspOut.println("<td> <button onclick =\"showCarParamForm(" + car.getId() + ")\" > " + "Edit car </button></td>");
			jspOut.println("</tr>");
		}
		jspOut.println("</table>");
	}

}
