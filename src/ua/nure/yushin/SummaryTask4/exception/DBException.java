package ua.nure.yushin.SummaryTask4.exception;

public class DBException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5045594190638546076L;
	
	public DBException() {
		super();
	}

	public DBException(String message) {
		super(message);
	}
	
	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}
