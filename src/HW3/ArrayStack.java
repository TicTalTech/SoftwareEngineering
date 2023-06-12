package HW3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Override
    public void push(E element) {
        if (effectiveSize == arr.length) {
            throw new StackOverflowException();
        }
        arr[effectiveSize] = element;
        effectiveSize++;
    }


    @Override
    public E pop() {
        if (effectiveSize == 0) {
            throw new EmptyStackException();
        }
        effectiveSize--;
        return (E) arr[effectiveSize];
    }

    @Override
    public E peek() {
        if (effectiveSize == 0) {
            throw new EmptyStackException();
        }
        return (E) arr[effectiveSize - 1];
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public ArrayStack<E> clone() {
//        ArrayStack<E> newStack = new ArrayStack<>(this.data.length);
        ArrayStack<E> copyStack;
        try {
            copyStack = (ArrayStack<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        copyStack.arr = new Cloneable[this.arr.length];
        for (int i = 0; i < this.size(); i++) {
            Method cloneMethod;
            try {
                cloneMethod = this.arr[i].getClass().
                        getMethod("clone");
                copyStack.arr[i] = (Cloneable) cloneMethod.invoke(arr[i]);

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

    @Override
    public Iterator iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {

        private int currentIndex = effectiveSize;
        @Override
        public boolean hasNext() {
            return currentIndex > 0;
        }

        @Override
        public E next() {
            currentIndex--;
            return (E) arr[currentIndex];
        }
    }
}
