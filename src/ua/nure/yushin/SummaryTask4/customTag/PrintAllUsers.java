package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.User;

public class PrintAllUsers extends SimpleTagSupport {
	
	private static final Logger LOG = Logger.getLogger(PrintAllUsers.class);
	
	private List <User> allUsers;

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	
	public void doTag()  throws JspException, IOException {
		
		JspWriter jspOut = getJspContext().getOut();
		int number = 0;
		
		if (allUsers == null) {
			LOG.error("no users in doTag");
			return;
		}
		
		LOG.info("allUsers" +  allUsers);
		
		jspOut.println("<table border = \"1\">");
		jspOut.println("<caption> All users from database </caption>");
		
		jspOut.println("<tr>");
		jspOut.println("<th> Number </th>");
		jspOut.println("<th> User id </th>");
		jspOut.println("<th> Surname (Family Name) </th>");
		jspOut.println("<th> Name (Given Name) </th>");
		jspOut.println("<th> Patronomic (Full Middle Name) </th>");
		jspOut.println("<th> Email </th>");
		jspOut.println("<th> User role </th>");
		jspOut.println("<th> is blocked (true/false) </th>");
		jspOut.println("<th> blocking/unblocking </th>"); //9
		jspOut.println("</tr>");
		
		for (User user : allUsers) {
			jspOut.println("<tr>");
			
			jspOut.println("<td>" + ++number + "</td>");
			jspOut.println("<td>" + user.getId() + "</td>");
			jspOut.println("<td>" + user.getUserPassSurname() + "</td>");
			jspOut.println("<td>" + user.getUserPassName() + "</td>");
			jspOut.println("<td>" + user.getUserPassPatronomic() + "</td>");
			jspOut.println("<td>" + user.getUserEmail() + "</td>");
			jspOut.println("<td>" + user.getUserRole() + "</td>");
			jspOut.println("<td>" + user.isUserBlocking() + "</td>");
			if (user.isUserBlocking()) {
				jspOut.println("<td> <button onclick =\"updateBlocking("+ user.getId() +", "+ user.isUserBlocking() +")\" > "
						+ "Unblock user </button></td>");
			} else {
				jspOut.println("<td> <button onclick =\"updateBlocking("+ user.getId() +", "+ user.isUserBlocking() +")\" > "
						+ "Block user </button></td>");
			}			
			jspOut.println("</tr>");
		}		
		jspOut.println("</table>");
	}

}
