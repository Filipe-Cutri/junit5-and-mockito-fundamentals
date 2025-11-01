package cursoJUnit5.Examples.domain.exceptions;

public class ValidationException extends RuntimeException {

	public ValidationException(String message) {
		super(message);
	}
}
