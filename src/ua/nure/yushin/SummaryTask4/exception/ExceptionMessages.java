package ua.nure.yushin.SummaryTask4.exception;

public class ExceptionMessages {

	// public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS =
	// "Cannot obtain user order beans";

	public static final String EXCEPTION_CAN_NOT_INSERT_NEW_CAR = "Can not insert new Car in DB";
	public static final String EXCEPTION_CAN_NOT_GET_ALL_CARS = "Can not get cars from DB";
	public static final String EXCEPTION_CAN_NOT_GET_CAR_BY_ID = "Can not get car by id";
	public static final String EXCEPTION_CAN_NOT_REMOVE_CAR = "Can not remove car from DB";
	public static final String EXCEPTION_CAN_NOT_UPDATE_ALL_CAR_PARAMETRS = "Can not update all cars parametrs";
	public static final String EXCEPTION_CAN_NOT_GET_ALL_USERS = "Can not get all users from database!";
	public static final String EXCEPTION_CAN_NOT_UPDATE_USER_BLOCKING = "Can not update user blocking!";
	public static final String EXCEPTION_CAN_NOT_INSERT_NEW_USER = "Can not insert new user";
	public static final String EXCEPTION_CAN_NOT_GET_ALL_ORDERS = "Can not get all orders";
	public static final String EXCEPTION_CAN_NOT_GET_USER_BY_ID = "Can not get user by id";
	public static final String EXCEPTION_CAN_NOT_GET_ACCOUNT_BY_ID = "Can not get account by id";
	public static final String EXCEPTION_CAN_NOT_GET_ORDER_BY_ORDERSTATUS = "Can not get order by orderStatus";
	public static final String EXCEPTION_CAN_NOT_GET_ALL_BUSY_DATES = "Can not get all busy dates!";
	public static final String EXCEPTION_CAN_NOT_GET_ORDER_BY_ID = "Can not get order by Id!";
	public static final String EXCEPTION_CAN_NOT_GET_USER_BY_EMAIL_AND_PASSWORD = "Can not get user by id and password!";
	public static final String EXCEPTION_USER_ALREADY_EXIST = "User with such email, pass number and pass series alredy exist!";
	public static final String EXCEPTION_CAN_NOT_UPDATE_USER = " Can not update user parametrs!";
	public static final String EXCEPTION_CAN_NOT_SEND_EMAIL = " Can not send confirmation email to user!";
	public static final String EXCEPTION_CAN_NOT_REMOVE_USER = " Can not remove user!";
	public static final String EXCEPTION_CAN_NOT_UPDATE_ACCOUNT = " Can not update account (Can not pay the order)!";
	public static final String EXCEPTION_CAN_NOT_UPDATE_ORDER = " Can not update order!";
	public static final String EXCEPTION_CAN_NOT_UPDATE_REJECTION_REASON = " Can not update rejection reason!";
	public static final String EXCEPTION_CAN_NOT_DELETE_CAR_BUSY_DATES_BY_ORDER_ID = " Can not delete car busy dates for specified car by order id!";
	public static final String EXCEPTION_ROLLBACK_CLOSE_ORDER = " Can not close order (rollback)!";
	public static final String EXCEPTION_CAN_NOT_CLOSE_ORDER = " Can not close order!";
	public static final String EXCEPTION_CAN_NOT_INSERT_ORDER_DATES = " Can not insert order dates!";
	public static final String EXCEPTION_CAN_NOT_INSERT_NEW_ORDER = " Can not insert new order!";
	public static final String EXCEPTION_CAN_NOT_INSERT_NEW_BYSY_DATE = " Can not insert new busy date!";
	public static final String EXCEPTION_CAN_NOT_GET_ORDERS_BY_CLIENT_ID_AND_ORDER_STATUS = " Can not get orders by client id and order status!";
	public static final String EXCEPTION_CAN_NOT_DELETE_ORDER = " Can not delete order!";
	public static final String EXCEPTION_CAN_NOT_DELETE_ACCOUNT = " Can not delete account!";
	public static final String EXCEPTION_CAN_NOT_GET_ACCOUNT_ID_BY_ORDER_ID = " Can not get account id by order id!";
	public static final String EXCEPTION_CAN_NOT_GET_ALL_ORDERS_WITH_UNPAID_REPAIRS = "Can not get all orders with unpaid repairs!";
	public static final String EXCEPTION_CAN_NOT_GET_ALL_ACCOUNTS_WITH_UNPAID_REPAIRS = "Can not get all accounts with unpaid repairs!";
	public static final String EXCEPTION_CAN_NOT_UPDATE_MANAGER_NAME_WHO_CLOSED_ORDER = "Can not update manager name who closed order!";
	public static final String EXCEPTION_CAN_NOT_UPDATE_ACCOUNT_FOR_REPAIR_AND_REPAIR_PAID_BY_ORDER_ID = "Can not update account for repair and repair paid by order id!";
	public static final String EXCEPTION_CAN_NOT_FIND_ALL_FREE_CARS_BY_BUSY_DATES = "Can not find all free cars by busy dates!";
	
	// exceptions validation add new car
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_BREND = "Invalid car brend. Name contains wrong character (for example: @#)!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_MODEL = "Invalid car model. Name contains wrong character (for example: @#)!";
	public static final String EXCEPTION_VALIDATION_INVALID_YEAR_OF_ISSUE = "Invalid year of issue. Cars can not be made earlier than today!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_QUALITY_CLASS = "Invalid car quality class. No such car quality class exist!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_STATUS = "Invalid car status. No such car status exist!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_RENTAL_COST = "Invalid car rental cost. Price can't be less than 0 or 0!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_ID = "Invalid car id!";
	public static final String EXCEPTION_VALIDATION_INVALID_SEX = "Invalid user sex!";
	public static final String EXCEPTION_VALIDATION_INVALID_ROLE = "Invalid user Role!";
	public static final String EXCEPTION_VALIDATION_INVALID_EMAIL = "Invalid email!";
	public static final String EXCEPTION_VALIDATION_INVALID_PASSWORD = "Invalid password!";
	public static final String EXCEPTION_VALIDATION_INVALID_PASS_SERIES = "Invalid pass series!";
	public static final String EXCEPTION_VALIDATION_INVALID_PASS_NUMBER = "Invalid pass number!";
	public static final String EXCEPTION_VALIDATION_INVALID_FIO = "Invalid name or surname or Patronomic!";
	public static final String EXCEPTION_VALIDATION_INVALID_DATE_OF_BIRTH = "You are to young to use this application. You must be older than 14! (Invalid date of Birth)";
	public static final String EXCEPTION_VALIDATION_INVALID_NOT_THE_SAME_PASSWORD = "Not the same password!";
	public static final String EXCEPTION_VALIDATION_INVALID_ORDER_ID = "Invalid order id!";
	public static final String EXCEPTION_VALIDATION_INVALID_REJECTION_REASON = "Invalid rejection reason. There are to much or to less characters in rejection reason. Must be more than 2 and less than 500!";
	public static final String EXCEPTION_VALIDATION_INVALID_ORDER_DATE = "Invalid order date!";
	public static final String EXCEPTION_VALIDATION_INVALID_PRICE = "Invalid price!";
	public static final String EXCEPTION_VALIDATION_NOT_ENOUGH_MANY_FOR_RENT = " Not enough many for rent!";
	public static final String EXCEPTION_VALIDATION_NOT_ENOUGH_MANY_FOR_REPAIR = " Not enough many for repair!";
	
	// App
	public static final String EXCEPTION_BLOCKED_USER = "User is blocked!";
	public static final String EXCEPTION_UNCONFIRMED_USER = "User is unconfirmed!";

	public static final String EXCEPTION_NULL_IN_REQUEST_PARAMETR = "Empty field!";
}
