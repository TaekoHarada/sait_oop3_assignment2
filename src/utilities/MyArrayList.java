package utilities;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E> {

	private E[] elements;
	private int size;
	
	public MyArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }
	
	@Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
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

        ensureCapacity(size + 1);

        // Shift elements to the right
        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = toAdd;
        size++;
        return true;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return elements[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        
        E removedElement = elements[index];
        
        // Shift elements to the left
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        
        elements[size - 1] = null;
        size--;
        
        return removedElement;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(toRemove)) {
                return remove(i);
            }
        }
        return null;
    }
    
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (toChange == null) {
            throw new NullPointerException("Cannot set null elements");
        }
        
        E previousElement = elements[index];
        elements[index] = toChange;
        return previousElement;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(toFind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold.length < size) {
            toHold = Arrays.copyOf(toHold, size);
        }
        System.arraycopy(elements, 0, toHold, 0, size);
        return toHold;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }
    
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1); // Increase capacity by 50%
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private class MyArrayListIterator implements Iterator<E> {

        private int cursor;

        public MyArrayListIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in iteration");
            }
            return elements[cursor++];
        }
    }
}
