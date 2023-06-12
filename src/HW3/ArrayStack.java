package HW3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * implements a stack data structure with a max size using an array
 * @param <E> the type of object the stack will keep
 */
public class ArrayStack<E extends Cloneable> implements Stack<E>, Iterable<E> {

    private Cloneable[] data;
    private int capacity;

    /**
     * a constructor for the stack that chooses the max size it can be
     * @param size - the maximum amount of items the stack can store
     */
    public ArrayStack (int size) {
        if (size < 0) {
            throw new NegativeCapacityException();
        }
        capacity = 0;
        this.data = new Cloneable[size];
    }

    @Override
    public void push(E element) {
        if (capacity == data.length) {
            throw new StackOverflowException();
        }
        data[capacity] = element;
        capacity++;
    }


    @Override
    public E pop() {
        if (capacity == 0) {
            throw new EmptyStackException();
        }
        capacity--;
        return (E) data[capacity];
    }

    @Override
    public E peek() {
        if (capacity == 0) {
            throw new EmptyStackException();
        }
        return (E) data[capacity - 1];
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> newStack = new ArrayStack<>(this.data.length);
        newStack.capacity = this.capacity;
        for(int i = 0; i < newStack.size(); i++) {
            Method cloneMethod;
            try {
                cloneMethod = this.data[i].getClass().getMethod("clone");
                newStack.data[i] = (Cloneable) cloneMethod.invoke(this.data[i]);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return newStack;
    }

    @Override
    public Iterator iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator {

        private int currentIndex = capacity;
        @Override
        public boolean hasNext() {
            return currentIndex > 0;
        }

        @Override
        public Object next() {
            currentIndex--;
            return data[currentIndex];
        }
    }
}
