package ua.nure.yushin.SummaryTask4.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	/**
	 * Default constructor.
	 */
	public ContextListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		printLog("Servlet context destruction starts");
		// no op
		printLog("Servlet context destruction finished");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		printLog("Servlet context initialization starts");
		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();
	}

	private void initLog4J(ServletContext servletContext) {
		printLog("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			printLog("Cannot configure Log4j");
			ex.printStackTrace();
		}
		printLog("Log4J initialization finished");
	}

	private void initCommandContainer() {
		
		// initialize commands container
		// just load class to JVM
		try {
			Class.forName("ua.nure.yushin.SummaryTask4.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Cannot initialize Command Container");
		}		
	}

	private void printLog(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}
