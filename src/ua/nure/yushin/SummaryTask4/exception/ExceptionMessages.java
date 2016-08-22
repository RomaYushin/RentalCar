package ua.nure.yushin.SummaryTask4.exception;

public class ExceptionMessages {
	
	// public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS = 
	//"Cannot obtain user order beans";

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
	
	// exceptions validation add new car
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_BREND = "Invalid car brend. Name contains wrong character (for example: @#)!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_MODEL = "Invalid car model. Name contains wrong character (for example: @#)!";
	public static final String EXCEPTION_VALIDATION_INVALID_YEAR_OF_ISSUE = "Invalid year of issue. Cars can not be made earlier than today! ";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_QUALITY_CLASS = "Invalid car quality class. No such car quality class exist!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_STATUS = "Invalid car status. No such car status exist!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_RENTAL_COST= "Invalid car rental cost. Price can't be less than 0 or 0!";
	public static final String EXCEPTION_VALIDATION_INVALID_CAR_ID= "Invalid car id!";
	public static final String EXCEPTION_VALIDATION_INVALID_SEX= "Invalid user sex!";
	
	public static final String EXCEPTION_NULL_IN_REQUEST_PARAMETR = "Empty field!";
}
