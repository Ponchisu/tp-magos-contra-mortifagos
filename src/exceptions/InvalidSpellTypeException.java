package exceptions;

public class InvalidSpellTypeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidSpellTypeException(String message) {
		super(message);
	}
}
