package ua.nure.yushin.SummaryTask4.controller;

public class Path {
	
	// welcome (index) page
	public static final String PAGE_WELCOME_AUTHORIZATION = "welcomeAuthorization.jsp";	
	public static final String COMMAND_REDIRECT_WELCOME_AUTHORIZATION = "/";	
	
	//error
	public static final String PAGE_FORWARD_ERROR = "/WEB-INF/view/error.jsp";	
	public static final String COMMAND_NO_COMMAND = "Controller?command=noCommand";	
	
	/*
	 * **********************************************************************************************
	 * ***************************************** FORWARD ********************************************
	 * **********************************************************************************************
	 */
	//forward client
	public static final String PAGE_FORWARD_CLIENT_REGISTRATION = "/WEB-INF/view/jsp/client/clientRegistration.jsp";	
	public static final String PAGE_FORWARD_CLIENT_PERSONAL_AREA = "/WEB-INF/view/jsp/client/clientPersonalArea.jsp";
	public static final String PAGE_FORWARD_CLIENT_AVAILABLE_CARS_ASYNC = "/WEB-INF/view/jsp/client/availableCars.jsp";
	public static final String PAGE_FORWARD_CLIENT_ORDER_CAR_ASYNC = "/WEB-INF/view/jsp/client/orderCarAsync.jsp";
	public static final String PAGE_FORWARD_CLIENT_CLIENT_NOT_CLOSED_ORDERS = "/WEB-INF/view/jsp/client/clientNotClosedOrders.jsp";
	public static final String PAGE_FORWARD_CLIENT_TOTAL_PRICE_ASYNC = "/WEB-INF/view/jsp/client/totalPriceAsync.jsp";	
	public static final String PAGE_FORWARD_CLIENT_CONFIRM_REGISTRATION = "/WEB-INF/view/confirmRegistrationView.jsp";
	public static final String PAGE_FORWARD_CLIENT_OPEN_ORDER = "/WEB-INF/view/jsp/client/openClientOrder.jsp";
	public static final String PAGE_FORWARD_CLIENT_CARS_REVIEW = "/WEB-INF/view/jsp/client/carsReview.jsp";
	public static final String PAGE_FORWARD_CLIENT_ARCHIVE_ORDERS = "/WEB-INF/view/jsp/client/archiveOrders.jsp";
	
	// forward admin
	public static final String PAGE_FORWARD_ADMIN_PERSONAL_AREA = "/WEB-INF/view/jsp/admin/adminPersonalArea.jsp";
	public static final String PAGE_FORWARD_ADMIN_SHOW_ADD_CAR_FORM = "/WEB-INF/view/jsp/admin/addNewCarForm.jsp";
	public static final String PAGE_FORWARD_ADMIN_SHOW_ALL_CARS_FORM = "/WEB-INF/view/jsp/admin/showAllCarsForm.jsp";
	public static final String PAGE_FORWARD_ADMIN_SHOW_EDIT_CAR_FORM = "/WEB-INF/view/jsp/admin/editCarForm.jsp";
	public static final String PAGE_FORWARD_ADMIN_SHOW_BLOCKING_USER_FORM = "/WEB-INF/view/jsp/admin/blockingUserForm.jsp";
	public static final String PAGE_FORWARD_ADMIN_SHOW_REGISTER_MANAGER_FROM  = "/WEB-INF/view/jsp/admin/registerManagerForm.jsp";
	public static final String PAGE_FORWARD_ADMIN_ADD_CAR_RESPONSE  = "/WEB-INF/view/jsp/admin/addCarAsyncResponse.jsp";
	public static final String PAGE_FORWARD_ADMIN_REMOVE_CAR_RESPONSE  = "/WEB-INF/view/jsp/admin/removeCarAsyncResponse.jsp";
	public static final String PAGE_FORWARD_ADMIN_EDIT_CAR_RESPONSE  = "/WEB-INF/view/jsp/admin/editCarAsyncResponse.jsp";
	public static final String PAGE_FORWARD_ADMIN_SHOW_CAR_PARAM_FORM  = "/WEB-INF/view/jsp/admin/showCarParamForm.jsp";
	public static final String PAGE_FORWARD_ADMIN_ASYNC_RESPONSE  = "/WEB-INF/view/jsp/admin/asyncResponse.jsp";
	
	//forward manager
	public static final String PAGE_FORWARD_MANAGER_PERSONAL_AREA = "/WEB-INF/view/jsp/manager/managerPersonalArea.jsp";
	public static final String PAGE_FORWARD_MANAGER_SHOW_ORDERS_FORM = "/WEB-INF/view/jsp/manager/showOrdersForm.jsp";
	public static final String PAGE_FORWARD_MANAGER_SHOW_SPECIFIED_ORDER = "/WEB-INF/view/jsp/manager/showSpecifiedOrder.jsp";
	
	
	
	/*
	 * ***********************************************************************************************
	 * ***************************************** REDIRECT ********************************************
	 * ***********************************************************************************************
	 */
	
	public static final String COMMAND_REDIRECT_TO_COMMAND= "Controller?command=";
	
	// redirect client
	public static final String COMMAND_REDIRECT_CLIENT_REGISTRATION = "Controller?command=clientRegistration";	
	public static final String COMMAND_REDIRECT_CLIENT_PERSONAL_AREA = "Controller?command=clientPersonalArea";
	public static final String COMMAND_REDIRECT_CLIENT_AVAILABLE_CARS_ASYNC = "Controller?command=availableCarsAsync";
	public static final String COMMAND_REDIRECT_CLIENT_TOTAL_PRICE_ASYNC = "Controller?command=calculateTotalPriceAsync";
	public static final String COMMAND_REDIRECT_CLIENT_SELECT_CARS_BY_RENTAL_DATES_ASYNC = "Controller?command=selectCarsByRentalDates";
	public static final String COMMAND_REDIRECT_CLIENT_CREATE_ORDER = "Controller?command=createOrder";
	public static final String COMMAND_REDIRECT_CLIENT_PAY_ORDER = "Controller?command=payOrder";
	public static final String COMMAND_REDIRECT_CLIENT_DELETE_ORDER = "Controller?command=deleteOrder";	
	public static final String COMMAND_REDIRECT_CLIENT_NOT_CLOSED_ORDERS = "Controller?command=clientNotClosedOrders";	
	public static final String COMMAND_REDIRECT_CLIENT_OPEN_ORDER = "Controller?command=openClientOrder";
	public static final String COMMAND_REDIRECT_CLIENT_CARS_REVIEW = "Controller?command=clientCarsReview";
	
	// redirect admin
	public static final String COMMAND_REDIRECT_ADMIN_PERSONAL_AREA = "Controller?command=adminPersonalArea";
	public static final String COMMAND_REDIRECT_ADMIN_SHOW_ADD_CAR_FORM = "Controller?command=showAddNewCarForm";
	public static final String COMMAND_REDIRECT_ADMIN_SHOW_ALL_CARS_FORM = "Controller?command=showAllCarsForm";
	public static final String COMMAND_REDIRECT_ADMIN_SHOW_EDIT_CAR_FORM = "Controller?command=showEditCarForm";
	public static final String COMMAND_REDIRECT_ADMIN_SHOW_BLOCKING_USER_FORM = "Controller?command=showBlockUserForm";
	public static final String COMMAND_REDIRECT_ADMIN_SHOW_REGISTER_MANAGER_FROM = "Controller?command=showRegisterManagerForm";
	public static final String COMMAND_REDIRECT_ADMIN_REGISTER_NEW_CAR = "Controller?command=registerNewCar";
	public static final String COMMAND_REDIRECT_ADMIN_REMOVE_CAR = "Controller?command=removeCar";
	public static final String COMMAND_REDIRECT_ADMIN_SHOW_CAR_PARAM_FORM = "Controller?command=showCarParamForm";
	public static final String COMMAND_REDIRECT_ADMIN_EDIT_CAR = "Controller?command=editCar";
	public static final String COMMAND_REDIRECT_ADMIN_UPDATE_USER_BLOCKING = "Controller?command=updateUserBlocking";
	public static final String COMMAND_REDIRECT_ADMIN_REGISTER_NEW_MANAGER = "Controller?command=registerNewManager";
	
	// redirect manager
	public static final String COMMAND_REDIRECT_MANAGER_PERSONAL_AREA = "Controller?command=managerPersonalArea";
	public static final String COMMAND_REDIRECT_MANAGER_SHOW_ORDERS_COMMAND = "Controller?command=showOrders";
	public static final String COMMAND_REDIRECT_MANAGER_SHOW_SPECIFIED_ORDER_COMMAND = "Controller?command=showSpecifiedOrder";
	public static final String COMMAND_REDIRECT_MANAGER_APPROVE_ORDER = "Controller?command=approveOrder";
	public static final String COMMAND_REDIRECT_MANAGER_REJECT_ORDER = "Controller?command=rejectOrder";
	public static final String COMMAND_REDIRECT_MANAGER_CLOSE_ORDER = "Controller?command=closeOrder";
	public static final String COMMAND_REDIRECT_MANAGER_CREATE_BILL_FOR_REPAIR = "Controller?command=createBillForRepair";
}































