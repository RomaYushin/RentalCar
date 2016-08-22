package ua.nure.yushin.SummaryTask4.exception;

public class AsyncResponseException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9025354185737963793L;
	
	public AsyncResponseException () {
		super();
	}
	
	public AsyncResponseException (String message) {
		super(message);
	}

	public AsyncResponseException (String message, Throwable cause) {
		super(message, cause);
	}

}
