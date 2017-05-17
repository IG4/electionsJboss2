package heig.metier;

@SuppressWarnings("serial")
public class DuplicateCodeException extends Exception {

	public DuplicateCodeException(String code) {
		super("Code " + code + " déjà utilisé");
	}

}
