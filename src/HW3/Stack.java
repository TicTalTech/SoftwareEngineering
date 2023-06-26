package HW3;

public interface Stack<E extends Cloneable> extends Iterable<E>, Cloneable {
    /**
     * adds an element to the stack
     * @param element The element to be added
     */
    void push(E element);

    /**
     * Removes an element from the stack
     * @return The element that was removed
     */
    E pop();

    /**
     * Gives the last added element of the stack
     * @return The last added element
     */
    E peek();

    /**
     * The amount of items in the stack
     * @return The size of the stack
     */
    int size();

    /**
     * check if the stack is empty
     * @return True if the stack is empty (does not contain any items). False otherwise
     */
    boolean isEmpty();

    /**
     * create a deep clone of the stack
     * @return A new stack that is a copy of the original one
     */
    Stack<E> clone();
}


