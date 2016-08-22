package ua.nure.yushin.SummaryTask4.exception;

public class ValidationException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7948326123785851516L;

	public ValidationException () {
		super();
	}

	public  ValidationException (String message) {
		super(message);
	}
	
	public  ValidationException (String message, Throwable cause) {
		super(message, cause);
	}
}
