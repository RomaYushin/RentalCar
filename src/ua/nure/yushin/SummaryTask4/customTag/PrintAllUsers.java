package ua.nure.yushin.SummaryTask4.customTag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.util.LocaleUtil;

public class PrintAllUsers extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516536589996831801L;

	private static final Logger LOG = Logger.getLogger(PrintAllUsers.class);
	
	private List <User> allUsers;

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	
	@Override
	public int doStartTag()  throws JspException {
		
		HttpSession session = pageContext.getSession();
		
		String tableName = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.tableName", session);
		String number_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.number_clmn", session);
		String userId_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userId_clmn", session);
		String userSurname_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userSurname_clmn", session);
		String userName_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userName_clmn", session);
		String userPatronomic_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userPatronomic_clmn", session);
		String email_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.email_clmn", session);
		String userRole_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userRole_clmn", session);
		String userIsBlocking_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userIsBlocking_clmn", session);
		String userIsBlockingTrue = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userIsBlockingTrue", session);
		String userIsBlockingFalse = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userIsBlockingFalse", session);
		String userBlockingUnblocking_clmn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.userBlockingUnblocking_clmn", session);
		String blockUser_btn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.blockUser_btn", session);
		String unBlockUser_btn = LocaleUtil.getValueByKey("adminPerArea_userTbl.jsp.unBlockUser_btn", session);
		
		JspWriter jspOut = pageContext.getOut();
		int number = 0;
		
		if (allUsers == null) {
			LOG.error("no users in doTag");
		}
		
		try {
			jspOut.println("<table border = \"1\">");
			jspOut.println("<caption>" + tableName + "</caption>");
			
			jspOut.println("<tr>");
			jspOut.println("<th>" + number_clmn + "</th>");
			jspOut.println("<th>" + userId_clmn + "</th>");
			jspOut.println("<th>" + userSurname_clmn + "</th>");
			jspOut.println("<th>" + userName_clmn + "</th>");
			jspOut.println("<th>" + userPatronomic_clmn + "</th>");
			jspOut.println("<th>" + email_clmn + "</th>");
			jspOut.println("<th>" + userRole_clmn + "</th>");
			jspOut.println("<th>" + userIsBlocking_clmn + "</th>");
			jspOut.println("<th>" + userBlockingUnblocking_clmn + "</th>"); //9
			jspOut.println("</tr>");
			
			for (User user : allUsers) {
				jspOut.println("<tr>");
				
				jspOut.println("<td>" + ++number + "</td>");
				jspOut.println("<td>" + user.getId() + "</td>");
				jspOut.println("<td>" + user.getUserPassSurname() + "</td>");
				jspOut.println("<td>" + user.getUserPassName()  + "</td>");
				jspOut.println("<td>" + user.getUserPassPatronomic() + "</td>");
				jspOut.println("<td>" + user.getUserEmail() + "</td>");
				jspOut.println("<td>" + user.getUserRole() + "</td>");
				if (user.isUserBlocking()) {
					jspOut.println("<td>" + userIsBlockingTrue + "</td>");
				} else {
					jspOut.println("<td>" + userIsBlockingFalse + "</td>");
				}
				
				if (user.isUserBlocking()) {
					jspOut.println("<td> <button onclick =\"updateBlocking("+ user.getId() +", "+ user.isUserBlocking() +")\" > "
							+ unBlockUser_btn + "</button></td>");
				} else {
					jspOut.println("<td> <button onclick =\"updateBlocking("+ user.getId() +", "+ user.isUserBlocking() +")\" > "
							+ blockUser_btn + "</button></td>");
				}			
				jspOut.println("</tr>");
			}		
			jspOut.println("</table>");
		} catch (IOException e) {
			
		}
		
		return SKIP_BODY;
	}

}
