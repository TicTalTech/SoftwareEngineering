package HW3;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * implements a stack data structure with a max size using an array
 * @param <E> the type of object the stack will keep
 */
public class ArrayStack<E extends Cloneable> implements Stack<E> {

    private Cloneable[] arr;
    private int effectiveSize;

    /**
     * a constructor for the stack that chooses the max size it can be
     * @param size - the maximum amount of items the stack can store
     */
    public ArrayStack (int size) {
        if (size < 0) {
            throw new NegativeCapacityException();
        }
        effectiveSize = 0;
        this.arr = new Cloneable[size];
    }

    /**
     * add an item to the top of the stack
     * @param element - the item tobe added
     */
    @Override
    public void push(E element) {
        if (effectiveSize == arr.length) {
            throw new StackOverflowException();
        }
        arr[effectiveSize] = element;
        effectiveSize++;
    }


    /**
     * removes an item from the top of the stack and returns it
     * @return the item that was removed
     */
    @Override
    public E pop() {
        if (effectiveSize == 0) {
            throw new EmptyStackException();
        }
        effectiveSize--;
        return (E) arr[effectiveSize];
    }

    /**
     * looks at the top item
     * @return the top item in the stack
     */
    @Override
    public E peek() {
        if (effectiveSize == 0) {
            throw new EmptyStackException();
        }
        return (E) arr[effectiveSize - 1];
    }

    /**
     * returns the amount of items in the stack
     * @return the size of the stack
     */
    @Override
    public int size() {
        return effectiveSize;
    }

    /**
     * check if the stack contain any items
     * @return - true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    /**
     * create a deep copy of the stack
     * @return - a new object containing a copy of the stack
     */
    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> copyStack;
        try {
            copyStack = (ArrayStack<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        copyStack.arr = new Cloneable[this.arr.length];
        for (int i = 0; i < this.size(); i++) {
            try {
                copyStack.arr[i] = (Cloneable) this.arr[i].getClass().
                        getMethod("clone").invoke(arr[i]);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }
        return copyStack;
    }
//    @Override
//    public ArrayStack<E> clone() {
//        ArrayStack<E> newStack = new ArrayStack<>(this.arr.length);
//        newStack.effectiveSize = this.effectiveSize;
//        for(int i = 0; i < newStack.size(); i++) {
//            Method cloneMethod;
//            try {
//                cloneMethod = this.arr[i].getClass().getMethod("clone");
//                newStack.arr[i] = (Cloneable) cloneMethod.invoke(this.arr[i]);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return newStack;
//    }

    /**
     * creates an iterator for the stack
     * @return the iterator
     */
    @Override
    public Iterator iterator() {
        return new StackIterator();
    }

    /**
     * an iterator class for the ArrayStack class
     */
    private class StackIterator implements Iterator<E> {

        private int currentIndex = effectiveSize;

        /**
         * check if the iterator has passed through all the objects
         * @return true if there is another item
         */
        @Override
        public boolean hasNext() {
            return currentIndex > 0;
        }

        /**
         * each time called, returns the next item in the stack
         * @return the next item in the stack
         */
        @Override
        public E next() {
            currentIndex--;
            return (E) arr[currentIndex];
        }
    }
}
