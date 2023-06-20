package HW3;

/**
 * An exception that gets thrown if the user tries to initialize a stack with a negative size
 */
public class NegativeCapacityException extends StackException {
    public NegativeCapacityException() {
    }

    public NegativeCapacityException(String message) {
        super(message);
    }

    public NegativeCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
