package HW3;

/**
 * A base class for all the exceptions that are related to the ArrayStack implementation
 */
public class StackException extends RuntimeException {
    public StackException() {
    }

    public StackException(String message) {
        super(message);
    }

    public StackException(String message, Throwable cause) {
        super(message, cause);
    }
}
