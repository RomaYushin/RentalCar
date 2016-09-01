package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.util.LocaleUtil;

public class PrintAllCarsFromDB extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9183647948560090827L;

	private static final Logger LOG = Logger.getLogger(PrintAllCarsFromDB.class);
	
	private Map <String, List <Car>> allCarsFromDB_map;

	public Map<String, List<Car>> getAllCarsFromDB_map() {
		return allCarsFromDB_map;
	}

	public void setAllCarsFromDB_map(Map<String, List<Car>> allCarsFromDB_map) {
		this.allCarsFromDB_map = allCarsFromDB_map;
	}
	
	@Override
	public int doStartTag()  throws JspException {
		
		HttpSession session = pageContext.getSession();
		
		String allCarsTableName = LocaleUtil.getValueByKey("adminPerArea.jsp.allCarsTableName", session);
		String number_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.number_clmn", session);
		String carId_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.carId_clmn", session);
		String carBrend_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.carBrend_clmn", session);
		String carModel_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.carModel_clmn", session);
		String carYearOfIssue_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.carYearOfIssue_clmn", session);
		String carQualityClass_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.carQualityClass_clmn", session);
		String carStatus_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.carStatus_clmn", session);
		String carRentalCost_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.carRentalCost_clmn", session);
		String editCar_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.editCar_clmn", session);
		String removeCar_clmn = LocaleUtil.getValueByKey("adminPerArea.jsp.removeCar_clmn", session);
		String editCar_btn = LocaleUtil.getValueByKey("adminPerArea.jsp.editCar_btn", session);
		String removeCar_btn = LocaleUtil.getValueByKey("adminPerArea.jsp.removeCar_btn", session);		
		
		JspWriter jspOut = pageContext.getOut();
		int number = 0;
		
		try {		
			jspOut.println("<table border = \"1\">");
			jspOut.println("<caption>" + allCarsTableName + "</caption>");
			
			jspOut.println("<tr>");
			jspOut.println("<th>" + number_clmn + "</th>");
			jspOut.println("<th>" + carId_clmn + "</th>");
			jspOut.println("<th>" + carBrend_clmn + "</th>");
			jspOut.println("<th>" + carModel_clmn + "</th>");
			jspOut.println("<th>" + carYearOfIssue_clmn +"</th>");
			jspOut.println("<th>" + carQualityClass_clmn +"</th>");
			jspOut.println("<th>" + carStatus_clmn +"</th>");
			jspOut.println("<th>" + carRentalCost_clmn +"</th>");
			jspOut.println("<th>" + editCar_clmn +"</th>");
			jspOut.println("<th>" + removeCar_clmn +"</th>");		
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
				jspOut.println("<td>" + car.getCarStatus() + "</td>");
				jspOut.println("<td>" + car.getCarRentalCost() + "</td>");
				jspOut.println("<td> <button onclick =\"showEditCarForm("+ car.getId() +")\" > "
						+ editCar_btn + "</button></td>");
				
				jspOut.println("<td>");
				jspOut.println("<form id = \"form"+ car.getId() +"\" action=\"Controller\" method=\"POST\">");
				jspOut.println("<input type=\"hidden\" name=\"command\" value = \"removeCar\" />");
				jspOut.println("<input type=\"hidden\" name=\"removeCarId\" value = \""+ car.getId() +"\" />");
				jspOut.println("<input type=\"submit\" value = \"" + removeCar_btn + "\"/>");		
				//jspOut.println("<button onclick =\"removeCar("+ car.getId() +")\" >"	+ removeCar_btn +"</button>");
				jspOut.println("</form>");
				
				jspOut.println("</td>");
				
				/*
				jspOut.println("<td> <button onclick =\"removeCar("+ car.getId() +")\" > "
						+ removeCar_btn +"</button></td>");	
				*/
				
				jspOut.println("</tr>");
			}		
			jspOut.println("</table>");
		} catch (IOException e) {
			LOG.error(e);
		}
		
		return SKIP_BODY;
	}
}
