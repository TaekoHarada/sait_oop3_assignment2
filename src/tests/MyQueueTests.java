package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.QueueADT;
import utilities.MyQueue;

public class MyQueueTests {

    private QueueADT<Integer> queue;

    @BeforeEach
    public void setup() {
        // Initialize your queue implementation (e.g., MyQueue<Integer>)
        queue = new MyQueue<>();
    }

    @Test
    public void testEnqueueAndSize() {
        // Test enqueuing elements and checking size
        assertEquals(0, queue.size());
        queue.enqueue(10);
        assertEquals(1, queue.size());
        queue.enqueue(20);
        assertEquals(2, queue.size());
    }

    @Test
    public void testEnqueueNullElement() {
        // Test for enqueuing a null element
        assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        });
    }

    @Test
    public void testDequeue() {
        // Test dequeuing elements
        queue.enqueue(10);
        queue.enqueue(20);
        assertEquals(Integer.valueOf(10), queue.dequeue());
        assertEquals(Integer.valueOf(20), queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueFromEmptyQueue() {
        // Test dequeuing from an empty queue
        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    public void testFirst() {
        // Test retrieving the first element
        queue.enqueue(10);
        queue.enqueue(20);
        assertEquals(Integer.valueOf(10), queue.first());
        assertEquals(2, queue.size()); // Ensure size remains the same
    }

    @Test
    public void testFirstFromEmptyQueue() {
        // Test retrieving the first element from an empty queue
        assertThrows(NoSuchElementException.class, () -> {
            queue.first();
        });
    }

    @Test
    public void testIsEmpty() {
        // Test checking if the queue is empty
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        assertFalse(queue.isEmpty());
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources if needed
        queue = null;
    }
}
