package ua.nure.yushin.SummaryTask4.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.entity.UserRole;

public class CommandAccessFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);
	
	// commands access
	private Map <UserRole, List<String>> accessMap = new HashMap <UserRole, List<String>>();
	//private List<String> admin = new ArrayList<String>();
	//private List<String> client = new ArrayList<String>();
	//private List<String> manager = new ArrayList<String>();
	private List<String> common = new ArrayList<String>();
	private List<String> outOfControl = new ArrayList<String>();

	@Override
	public void destroy() {
		LOG.info("Filter destruction starts");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		LOG.info("Filter starts");
		
		if (accessAllowed(servletRequest)) {
			LOG.info("Filter finished");
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			String errorMessage = "You do not have permission to access the requested page!";
			servletRequest.setAttribute("errorMessage", errorMessage);
			LOG.info("errorMessage: " + errorMessage);
			servletRequest.getRequestDispatcher(Path.PAGE_FORWARD_ERROR).forward(servletRequest, servletResponse);
		}		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		LOG.info("Filter initialization starts");
		
		// roles
		accessMap.put(UserRole.ADMIN, asList(filterConfig.getInitParameter("admin")));
		accessMap.put(UserRole.MANAGER, asList(filterConfig.getInitParameter("manager")));
		accessMap.put(UserRole.CLIENT, asList(filterConfig.getInitParameter("client")));
		LOG.info("accessMap: " + accessMap);
		
		//common
		common = asList(filterConfig.getInitParameter("common"));
		LOG.info("common: " + common);
		
		//out of control
		outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
		LOG.info("out-of-control: " + outOfControl);		
	}
	
	private List <String> asList (String initParam) {
		List <String> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(initParam);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}
	
	
	private boolean accessAllowed (ServletRequest request) {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String commandName = request.getParameter("command");
		if (commandName == null || commandName.isEmpty()) {
			return false;
		}
		
		if (outOfControl.contains(commandName)) {
			LOG.info("outOfControl true");
			return true;
		}
		
		HttpSession session = httpRequest.getSession(false);		
		if (session == null) {
			return false;
		}
		
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (accessMap.get(userRole).contains(commandName) || common.contains(commandName)) {
			LOG.info("true");
			return true;
		}		
		
		if (userRole == null) {
			return false;
		}
		
				
		return false;		
	}
	
	
	

}
