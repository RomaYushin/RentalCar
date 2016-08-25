package ua.nure.yushin.SummaryTask4.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;

public class DeleteOrderCommand extends AbstractCommand {

	private static final long serialVersionUID = 2907609196339163468L;

	private static final Logger LOG = Logger.getLogger(DeleteOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		LOG.info("Start executing DeleteOrderCommand execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info("End executing DeleteOrderCommand execute");
		return result;
	}

	private String doPost (HttpServletRequest request, HttpServletResponse response) {
		
		LOG.info("PayOrderCommand.doPost start");
		
		HttpSession session = request.getSession(false);
		int orderId = (int) Integer.valueOf(request.getParameter("orderId"));
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IOrderDAO iOrderDAO = daoFactory.getOrderDAO();
		boolean result = iOrderDAO.deleteOrderById (orderId);
		
		if (!result) {
			LOG.error("ERROR  order wasn't deleted");
			session.setAttribute("deleteOrder", false);
			return Path.COMMAND_REDIRECT_CLIENT_DELETE_ORDER;
		}		
		session.setAttribute("deleteOrder", true);		
		
		LOG.info("PayOrderCommand.doPost end");
		return Path.COMMAND_REDIRECT_CLIENT_DELETE_ORDER;
	}

	private String doGet (HttpServletRequest request, HttpServletResponse response) {
		
		LOG.info ("Start executing DeleteOrderCommand.doGet");
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("deleteOrder") == null) {
			LOG.error("invalid doGet request");
			return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		}
		
		boolean isDeleteOrder = (boolean)(session.getAttribute("deleteOrder"));
		session.removeAttribute("deleteOrder");
		
		LOG.info("isDeleteOrderr in doGet: " + isDeleteOrder);
		
		if (!isDeleteOrder) {
			request.setAttribute("isDeleteOrder", false);
			return Path.PAGE_FORWARD_CLIENT_PERSONAL_AREA;
		}
		
		request.setAttribute("isDeleteOrder", isDeleteOrder);
		
		return Path.PAGE_FORWARD_ADMIN_PERSONAL_AREA;
	}
}
