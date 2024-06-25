package utilities;

public interface QueueADT<E> {

    /**
     * Adds an element to the rear of the queue.
     *
     * @param element the element to add to the queue
     * @throws IllegalArgumentException if the element is null (depending on implementation)
     */
    void enqueue(E element);

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element removed from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    E dequeue();

    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    E first();

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    int size();

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
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
