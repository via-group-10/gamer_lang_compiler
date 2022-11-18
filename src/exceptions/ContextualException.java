package exceptions;

public class ContextualException extends RuntimeException {
    public ContextualException(String message) {
        super(message);
    }

    public ContextualException(String message, Throwable cause) {
        super(message, cause);
    }
}
