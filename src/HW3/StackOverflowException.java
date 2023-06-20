package HW3;

/**
 * An exception that gets thrown if the user tries to add an item to an already filled stack
 * (there is no space to add more)
 */
public class StackOverflowException extends StackException {
    public StackOverflowException() {
    }

    public StackOverflowException(String message) {
        super(message);
    }

    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
