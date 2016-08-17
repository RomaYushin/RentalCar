package ua.nure.yushin.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.CommandContainer;
import ua.nure.yushin.SummaryTask4.command.ICommand;

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

	private void processRequest(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) throws IOException, ServletException {

		LOG.info("processRequest start");
		
		String commandName = request.getParameter("command");
		LOG.info("Request parameter: 'command' = " + commandName);
		
		ICommand command = CommandContainer.getCommand(commandName);		
		String path = command.execute(request, response, requestMethodType);
		
		if (path == null) {
			LOG.info ("Redirect to address = " + path);
			LOG.info ("Controller proccessing finished");
			response.sendRedirect(Path.PAGE_WELCOME_AUTHORIZATION);
		} else if (requestMethodType.equals(ActionType.GET)) {
			LOG.info ("Forward to address = " + path);
			LOG.info ("Controller proccessing finished");
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		} else if (requestMethodType.equals(ActionType.POST)) {
			LOG.info ("Redirect to address = " + path);
			LOG.info ("Controller proccessing finished");
			response.sendRedirect(path);
		}		
	}
}
