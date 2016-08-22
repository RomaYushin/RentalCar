package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.User;

public class PrintOrders extends SimpleTagSupport {

	private static final Logger LOG = Logger.getLogger(PrintOrders.class);

	private Map <String, List> orders_map;
	
	public Map <String, List> getOrders_map() {
		return orders_map;
	}

	public void setOrders_map(Map<String, List> orders_map) {
		this.orders_map = orders_map;
	}

	public void doTag() throws JspException, IOException {

		JspWriter jspOut = getJspContext().getOut();
		int number = 0;

		if (orders_map == null) {
			LOG.error("no orders_map in doTag");
			//return;
		}
		
		jspOut.println("<table border = \"1\">");
		
		jspOut.println("<caption>" + orders_map.get("param").get(0) + "</caption>"); // "/*+ tableName +*/"
		
		jspOut.println("<tr>");
		jspOut.println("<th> Number </th>");
		jspOut.println("<th> Order id </th>");
		jspOut.println("<th> Surname (Family Name) </th>");
		jspOut.println("<th> Name (Given Name) </th>");
		jspOut.println("<th> Patronomic (Full Middle Name) </th>");
		jspOut.println("<th> Email </th>");
		jspOut.println("<th> Account id </th>");
		jspOut.println("<th> Order creation date </th>");
		jspOut.println("<th> Open order </th>");
		jspOut.println("</tr>");
		
		for (Order order : (List<Order>)orders_map.get("orders")) {
			jspOut.println("<tr>");
			
			jspOut.println("<td>" + ++number + "</td>");
			jspOut.println("<td>" + order.getId() + "</td>");
			jspOut.println("<td>" + order.getOrderClient().getUserPassSurname() + "</td>");
			jspOut.println("<td>" + order.getOrderClient().getUserPassName() + "</td>");
			jspOut.println("<td>" + order.getOrderClient().getUserPassPatronomic() + "</td>");
			jspOut.println("<td>" + order.getOrderClient().getUserEmail() + "</td>");
			jspOut.println("<td>" + order.getOrderAccount().getId() + "</td>");
			jspOut.println("<td>" + order.getCreateOrderDate() + "</td>");
			jspOut.println("<td> <button onclick =\"openOrder("+ order.getId() +");\" > "
					+ "Open order </button></td>");
			
			jspOut.println("</tr>");
		}
		jspOut.println("</table>");
	} 
}
