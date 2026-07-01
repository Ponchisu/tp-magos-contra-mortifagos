package exceptions;

public class HelpEnemyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public HelpEnemyException(String message) {
		super(message);
	}
}
