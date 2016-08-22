package ua.nure.yushin.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.CommandContainer;
import ua.nure.yushin.SummaryTask4.command.ICommand;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.AsyncResponseException;

/**
 * Servlet implementation class Controller
 */
//@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(Controller.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doGet start");
		processRequest(request, response, ActionType.GET);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOG.info("doPost start");
		processRequest(request, response, ActionType.POST);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) 
			throws IOException, ServletException {

		LOG.info("processRequest start");
		
		String commandName = request.getParameter("command");
		LOG.info("command (command from request) = " + commandName);
		
		ICommand command = CommandContainer.getCommand(commandName);	
		LOG.info("command (command from CommandContainer) = " + command);
		
		String path = Path.PAGE_FORWARD_ERROR;
		RequestDispatcher disp = null;
		HttpSession session = request.getSession(false);
		try {
			path = command.execute(request, response, requestMethodType);
			
			if (path == null) {
				LOG.info ("Redirect to address = " + path);
				LOG.info ("Controller proccessing finished");
				disp = request.getRequestDispatcher(Path.PAGE_FORWARD_ERROR);
				disp.forward(request, response);
			} else if (requestMethodType.equals(ActionType.GET)) {
				LOG.info ("Forward to address = " + path);
				LOG.info ("Controller proccessing finished");
				disp = request.getRequestDispatcher(path);
				disp.forward(request, response);
			} else if (requestMethodType.equals(ActionType.POST)) {
				LOG.info ("Redirect to address = " + path);
				LOG.info ("Controller proccessing finished");
				response.sendRedirect(path);
			}
			
		} catch (AsyncResponseException asyncResponseException) {
			session.setAttribute("errorMessage", asyncResponseException.getMessage());
			LOG.error(asyncResponseException.getMessage());			
			request.getRequestDispatcher(Path.COMMAND_NO_COMMAND).forward(request, response);	
	
		} catch (AppException appException) {
			session.setAttribute("errorMessage", appException.getMessage());
			LOG.error(appException.getMessage());
			request.getRequestDispatcher(Path.COMMAND_NO_COMMAND).forward(request, response);
		}						
	}
}
