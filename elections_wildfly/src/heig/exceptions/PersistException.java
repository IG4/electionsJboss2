package heig.exceptions;

@SuppressWarnings("serial")
public class PersistException extends Exception {

	public PersistException(String message) {
		super(message);
	}

	public PersistException(String message, Throwable cause) {
		super(message, cause);
	}
}
