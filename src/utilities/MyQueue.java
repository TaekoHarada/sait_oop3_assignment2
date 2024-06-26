package utilities;

import java.util.NoSuchElementException;

public class MyQueue<E> implements QueueADT<E> {

    private E[] elements;
    private int size;
    private int front;
    private int rear;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        front = 0;
        rear = -1;
    }

    @Override
    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot enqueue null element");
        }
        ensureCapacity(size + 1);
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E element = elements[front];
        elements[front] = null; // Help GC
        front = (front + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[front];
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
        return new MyQueueIterator();
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length >> 1); // Increase capacity by 50%
            resizeArray(newCapacity);
        }
    }

    private void resizeArray(int newCapacity) {
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        elements = newElements;
        front = 0;
        rear = size - 1;
    }

    private class MyQueueIterator implements Iterator<E> {

        private int cursor;
        private int count;

        public MyQueueIterator() {
            cursor = front;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in iteration");
            }
            E element = elements[cursor];
            cursor = (cursor + 1) % elements.length;
            count++;
            return element;
        }
    }
}
