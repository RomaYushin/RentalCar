package ua.nure.yushin.SummaryTask4.exception;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1555795668444698271L;
	
	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
