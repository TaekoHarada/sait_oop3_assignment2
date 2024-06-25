package utilities;

public interface StackADT<E> {

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param element the element to be pushed onto this stack
     * @throws IllegalArgumentException if the element is null (depending on implementation)
     */
    void push(E element);

    /**
     * Removes the element at the top of this stack and returns that element.
     *
     * @return the element removed from the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    E pop();

    /**
     * Retrieves, but does not remove, the element at the top of this stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    E peek();

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in the stack
     */
    int size();

    /**
     * Checks if this stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();
    
	/**
	 * Returns an iterator over the elements in this list, in proper sequence.
	 * 
	 * @return An iterator over the elements in this list, in proper sequence.
	 * 			NB: The return is of type 
	 * 			<code>linearUtilities.Iterator<E></code>,
	 * 			not <code>java.util.Iterator</code>.
	 */
	public Iterator<E> iterator();	

}
