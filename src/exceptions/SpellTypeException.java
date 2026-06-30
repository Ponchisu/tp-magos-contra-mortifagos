package exceptions;

public class SpellTypeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SpellTypeException(String message) {
		super(message);
	}
}
