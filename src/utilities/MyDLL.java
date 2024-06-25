package utilities;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {

    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null elements");
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (index == 0) {
            addFirst(newNode);
        } else if (index == size) {
            addLast(newNode);
        } else {
            MyDLLNode<E> current = getNodeAtIndex(index);
            addBefore(current, newNode);
        }
        size++;
        return true;
    }

    private void addFirst(MyDLLNode<E> newNode) {
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    private void addLast(MyDLLNode<E> newNode) {
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    private void addBefore(MyDLLNode<E> current, MyDLLNode<E> newNode) {
        MyDLLNode<E> prevNode = current.getPrev();
        newNode.setNext(current);
        newNode.setPrev(prevNode);
        current.setPrev(newNode);
        if (prevNode != null) {
            prevNode.setNext(newNode);
        } else {
            head = newNode;
        }
    }

    private MyDLLNode<E> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        return add(size, toAdd);
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        Iterator<? extends E> iterator = toAdd.iterator();
        boolean modified = false;
        while (iterator.hasNext()) {
            add(iterator.next());
            modified = true;
        }
        return modified;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        MyDLLNode<E> node = getNodeAtIndex(index);
        return node.getData();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        MyDLLNode<E> nodeToRemove = getNodeAtIndex(index);
        return removeNode(nodeToRemove);
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(toRemove)) {
                return removeNode(current);
            }
            current = current.getNext();
        }
        return null;
    }

    private E removeNode(MyDLLNode<E> node) {
        MyDLLNode<E> prevNode = node.getPrev();
        MyDLLNode<E> nextNode = node.getNext();

        if (prevNode != null) {
            prevNode.setNext(nextNode);
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.setPrev(prevNode);
        } else {
            tail = prevNode;
        }

        size--;
        return node.getData();
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        MyDLLNode<E> node = getNodeAtIndex(index);
        E previousData = node.getData();
        node.setData(toChange);
        return previousData;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(toFind)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold.length < size) {
            toHold = Arrays.copyOf(toHold, size);
        }
        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            toHold[index++] = current.getData();
            current = current.getNext();
        }
        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyDLLIterator();
    }

    private class MyDLLIterator implements Iterator<E> {

        private MyDLLNode<E> current;

        public MyDLLIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in iteration");
            }
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
