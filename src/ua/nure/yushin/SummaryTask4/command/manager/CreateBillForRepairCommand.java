package ua.nure.yushin.SummaryTask4.command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.AbstractCommand;
import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.validators.ValidatorOfInputParameters;

public class CreateBillForRepairCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001925151775187194L;
	
	private static final Logger LOG = Logger.getLogger(CreateBillForRepairCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType)
			throws AppException {
		
		LOG.info ("Start executing CreateBillForRepairCommand.execute");
		String result = null;

		if (requestMethodType.equals(ActionType.GET)) {
			result = doGet(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			result = doPost(request, response);
		}
		LOG.info ("End executing CreateBillForRepairCommand.execute");
		return result;
	}
	
	private static String doPost (HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		
		LOG.info ("Start executing CreateBillForRepairCommand.doPost");
		
		int orderId = 0;
		int priceForRepair = 0;
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		
		try {
			orderId = Integer.valueOf(request.getParameter("orderId"));
			priceForRepair = Integer.valueOf(request.getParameter("priceForRepair"));
			
			LOG.info ("orderId: " + orderId);
			LOG.info ("priceForRepair: " + priceForRepair);
			
		} catch (Exception e) {
			throw new DBException(ExceptionMessages.EXCEPTION_NULL_IN_REQUEST_PARAMETR, e);
		}
		
		ValidatorOfInputParameters.validateId(orderId);
		ValidatorOfInputParameters.validatePrice(priceForRepair);
		
		iAccountDAO.updateAccountForRepairAndRepairPaidByOrderId(orderId, priceForRepair, false);
		
		return Path.COMMAND_REDIRECT_MANAGER_CREATE_BILL_FOR_REPAIR;
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		
		LOG.info ("Start CreateBillForRepairCommand.doGet");
		HttpSession session = request.getSession(false);
		String sortingType = (String) session.getAttribute("sortingType");
		
		request.setAttribute("respMessage", "Bill was succesfully created!");
		//request.setAttribute("type", "close");
		request.setAttribute("sortingType", sortingType);
		
		return Path.PAGE_FORWARD_MANAGER_SHOW_SPECIFIED_ORDER;
	}
	
	

}
