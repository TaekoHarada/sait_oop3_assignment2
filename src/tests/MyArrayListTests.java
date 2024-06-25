package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.ListADT;
import utilities.MyArrayList;

public class MyArrayListTests {

    private ListADT<Integer> list;

    @BeforeEach
    public void setup() {
        // Initialize your list implementation
        list = new MyArrayList<>();
    }

    @Test
    public void testAddAtIndex() {
        // Test adding element at specific index
        list.add(0, 10);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
    }

    @Test
    public void testAddAtIndexOutOfBounds() {
        // Test for IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(1, 20); // This should throw IndexOutOfBoundsException
        });
    }

    @Test
    public void testAddNullElement() {
        // Test for adding null element
        assertThrows(NullPointerException.class, () -> {
            list.add(null);
        });
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources if needed
        if (list != null) {
            list.clear();
        }
    }
}
