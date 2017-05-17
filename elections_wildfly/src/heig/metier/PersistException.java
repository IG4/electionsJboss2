package heig.metier;

@SuppressWarnings("serial")
public class PersistException extends Exception {
	public PersistException() {
		super("Pb d'accès à la base de donnée");
	}

	public PersistException(Throwable arg0) {
		super(arg0);
	}

	public PersistException(String message) {
		super(message);
	}

}
