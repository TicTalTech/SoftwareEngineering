package HW3;

/**
 * An exception that gets thrown if the user tries to remove an item from an already empty stack
 */
public class EmptyStackException extends StackException {
    public EmptyStackException() {
    }

    public EmptyStackException(String message) {
        super(message);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
