package ua.nure.yushin.SummaryTask4.controller;

public class Path {
	
	// pages
	public static final String PAGE_WELCOME_AUTHORIZATION = "welcomeAuthorization.jsp";	
	
	public static final String PAGE_FORWARD_ERROR = "/WEB-INF/view/error.jsp";	
	public static final String PAGE_FORWARD_CLIENT_REGISTRATION = "/WEB-INF/view/jsp/client/clientRegistration.jsp";
	public static final String PAGE_FORWARD_ADMIN_PERSONAL_AREA = "/WEB-INF/view/jsp/admin/adminPersonalArea.jsp";
	public static final String PAGE_FORWARD_MANAGER_PERSONAL_AREA = "/WEB-INF/view/jsp/manager/managerPersonalArea.jsp";
	public static final String PAGE_FORWARD_CLIENT_PERSONAL_AREA = "/WEB-INF/view/jsp/client/clientPersonalArea.jsp";
	public static final String PAGE_FORWARD_CLIENT_AVAILABLE_CARS_ASYNC = "/WEB-INF/view/jsp/client/availableCars.jsp";
	public static final String PAGE_FORWARD_CLIENT_ORDER_CAR_ASYNC = "/WEB-INF/view/jsp/client/orderCarAsync.jsp";
	public static final String PAGE_FORWARD_CLIENT_CHECK_ORDER_STATUS_ASYNC = "/WEB-INF/view/jsp/client/checkOrderStatusAsync.jsp";
	public static final String PAGE_FORWARD_CLIENT_TOTAL_PRICE_ASYNC = "/WEB-INF/view/jsp/client/totalPriceAsync.jsp";
	
	public static final String COMMAND_REDIRECT_CLIENT_REGISTRATION = "Controller?command=clientRegistration";
	public static final String COMMAND_REDIRECT_ADMIN_PERSONAL_AREA = "Controller?command=adminPersonalArea";
	public static final String COMMAND_REDIRECT_MANAGER_PERSONAL_AREA = "Controller?command=managerPersonalArea";
	public static final String COMMAND_REDIRECT_CLIENT_PERSONAL_AREA = "Controller?command=clientPersonalArea";
	public static final String COMMAND_REDIRECT_CLIENT_AVAILABLE_CARS_ASYNC = "Controller?command=availableCarsAsync";
	public static final String COMMAND_REDIRECT_CLIENT_TOTAL_PRICE_ASYNC = "Controller?command=calculateTotalPriceAsync";
	
}