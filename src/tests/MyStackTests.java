package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.StackADT;
import utilities.MyStack;

public class MyStackTests {

    private StackADT<Integer> stack;

    @BeforeEach
    public void setup() {
        // Initialize your stack implementation (e.g., MyStack<Integer>)
        stack = new MyStack<>();
    }

    @Test
    public void testPushAndSize() {
        // Test pushing elements and checking size
        assertEquals(0, stack.size());
        stack.push(10);
        assertEquals(1, stack.size());
        stack.push(20);
        assertEquals(2, stack.size());
    }

    @Test
    public void testPushNullElement() {
        // Test for pushing a null element
        assertThrows(IllegalArgumentException.class, () -> {
            stack.push(null);
        });
    }

    @Test
    public void testPop() {
        // Test popping elements
        stack.push(10);
        stack.push(20);
        assertEquals(Integer.valueOf(20), stack.pop());
        assertEquals(Integer.valueOf(10), stack.pop());
    }

    @Test
    public void testPopFromEmptyStack() {
        // Test popping from an empty stack
        assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void testPeek() {
        // Test peeking at the top element
        stack.push(10);
        stack.push(20);
        assertEquals(Integer.valueOf(20), stack.peek());
        assertEquals(2, stack.size()); // Ensure size remains the same
    }

    @Test
    public void testPeekFromEmptyStack() {
        // Test peeking from an empty stack
        assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        });
    }

    @Test
    public void testIsEmpty() {
        // Test checking if the stack is empty
        assertTrue(stack.isEmpty());
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources if needed
        stack = null;
    }
}
