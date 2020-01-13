package exception;

public class APIException extends Exception {

	private static final long serialVersionUID = -1328445644740340507L;

	public APIException(String message, Exception e) {
		super(message, e);
	}

	public APIException(String message) {
		super(message);
	}
}