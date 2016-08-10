package ua.nure.yushin.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.ICommand;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(ControllerServlet.class);

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
		processRequest(request, response, ActionType.POST);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response, ActionType requestMethodType) {

		String commandName = request.getParameter("command");
		
		//ICommand command = CommandManager.get(commandName);
		
		//String path = command.execute(request, response, requestMethodType);
		
		/*
		if (path == null) {
			response.sendRedirect(Path.);
		} else if (requestMethodType.equals(GET)) {
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		} else if (requestMethodType.equals(POST)) {
			response.sendRedirect(path);
		}
		*/
		
	}

}
