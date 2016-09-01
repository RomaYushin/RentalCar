package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.util.LocaleUtil;

public class PrintOrders extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6625957555593892401L;

	private static final Logger LOG = Logger.getLogger(PrintOrders.class);

	private List<Order> orders;
	private String tableName;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	@Override
	public int doStartTag() throws JspException {

		HttpSession session = pageContext.getSession();

		String tableNameVal = LocaleUtil.getValueByKey(tableName, session);
		String numberVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.number_clmn", session);
		String orderIdVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.orderId_clmn", session);
		String surnameVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.surname_clmn", session);
		String nameVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.name_clmn", session);
		String patronomicVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.patronomic_clmn", session);
		String emailVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.email_clmn", session);
		String accountIdVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.accountId_clmn", session);
		String orderCreationDateVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.orderCreationDate_clmn", session);
		String orderPaymentCondition  = LocaleUtil.getValueByKey("managerPersonalArea.jsp.orderPaymentCondition", session);
		
		String orderStatus = LocaleUtil.getValueByKey("managerPersonalArea.jsp.orderStatus", session);
		String orderUntreated = LocaleUtil.getValueByKey("managerPersonalArea.jsp.UNTREATED", session);
		String orderActive = LocaleUtil.getValueByKey("managerPersonalArea.jsp.ACTIVE", session);
		String orderClose = LocaleUtil.getValueByKey("managerPersonalArea.jsp.CLOSE", session);
		String orderRejected = LocaleUtil.getValueByKey("managerPersonalArea.jsp.REJECTED", session);
		
		String orderPaymentYes  = LocaleUtil.getValueByKey("managerPersonalArea.jsp.orderPaymentYes", session);
		String orderPaymentNo  = LocaleUtil.getValueByKey("managerPersonalArea.jsp.orderPaymentNo", session);
		String openOrderVal = LocaleUtil.getValueByKey("managerPersonalArea.jsp.openOrder_clmn", session);
		String openOrderButton = LocaleUtil.getValueByKey("managerPersonalArea.jsp.openOrder_clmn", session);

		int number = 0;

		if (orders == null) {
			LOG.error("no orders_map in doTag");
		}

		try {
			JspWriter jspOut = pageContext.getOut();
			
			jspOut.println("<table border = \"1\">");

			jspOut.println("<caption>" + tableNameVal + "</caption>");
			jspOut.println("<tr>");
			jspOut.println("<th>" + numberVal + "</th>");
			jspOut.println("<th>" + orderIdVal + "</th>");
			jspOut.println("<th>" + surnameVal + "</th>");
			jspOut.println("<th>" + nameVal + "</th>");
			//jspOut.println("<th>" + patronomicVal + "</th>");
			jspOut.println("<th>" + emailVal + "</th>");
			jspOut.println("<th>" + accountIdVal + "</th>");
			jspOut.println("<th>" + orderCreationDateVal + "</th>");
			jspOut.println("<th>" + orderPaymentCondition + "</th>");
			jspOut.println("<th>" + orderStatus + "</th>");
			jspOut.println("<th>" + openOrderVal + "</th>");

			jspOut.println("</tr>");

			for (Order order : orders) {
				jspOut.println("<tr>");

				jspOut.println("<td>" + ++number + "</td>");
				jspOut.println("<td>" + order.getId() + "</td>");
				jspOut.println("<td>" + order.getOrderClient().getUserPassSurname() + "</td>");
				jspOut.println("<td>" + order.getOrderClient().getUserPassName() + "</td>");
				//jspOut.println("<td>" + order.getOrderClient().getUserPassPatronomic() + "</td>");
				jspOut.println("<td>" + order.getOrderClient().getUserEmail() + "</td>");
				jspOut.println("<td>" + order.getOrderAccount().getId() + "</td>");
				jspOut.println("<td>" + order.getCreateOrderDate() + "</td>");
				
				if (order.getOrderAccount().isAccountRentPaid()) {
					jspOut.println("<td>" + orderPaymentYes + "</td>");
				} else {
					jspOut.println("<td>" + orderPaymentNo + "</td>");
				}
				
				switch (order.getOrderStatus().toString()) {
				case "UNTREATED":
					jspOut.println("<td>" + orderUntreated + "</td>");
					break;
				case "ACTIVE":
					jspOut.println("<td>" + orderActive + "</td>");
					break;
				case "CLOSE":
					jspOut.println("<td>" + orderClose + "</td>");
					break;
				case "REJECTED":
					jspOut.println("<td>" + orderRejected + "</td>");
					break;				
				}
				jspOut.println("<td> <button onclick =\"openOrder(" + order.getId() + ");\"> "+ openOrderButton +"</button></td>");
				jspOut.println("</tr>");
			}
			jspOut.println("</table>");
		} catch (IOException e) {
			LOG.error(e);
		}
		return SKIP_BODY;
	}
}
