package utilities;

import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E> {

    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public MyStack() {
        elements = (E[]) new Object[10]; // Initial capacity of 10
        size = 0;
    }

    @Override
    public void push(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot push null element");
        }
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        E element = elements[--size];
        elements[size] = null; // Help GC
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return elements[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyStackIterator();
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1); // Increase capacity by 50%
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    private class MyStackIterator implements Iterator<E> {

        private int cursor;

        public MyStackIterator() {
            cursor = size - 1;
        }

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in iteration");
            }
            return elements[cursor--];
        }
    }

}
