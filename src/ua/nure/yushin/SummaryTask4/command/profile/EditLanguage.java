package ua.nure.yushin.SummaryTask4.command.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;

public class EditLanguage extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291692835953167605L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		String result = null;
		if (ActionType.GET.equals(requestMethodType)) {
			result = doGet(request, response);
		} else {
			result = doPost(request, response);
		}

		return result;
	}

	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		HttpSession session = request.getSession(false);
		
		//String userLanguage = 
		
		return result;
	}

	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		
		
		return result;		
	}

}
