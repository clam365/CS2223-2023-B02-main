package edu.wpi.cs2223.algorithms.shared;

import edu.wpi.cs2223.algorithms.week1.cllam.TwoQueueStackPopOptimized;
import edu.wpi.cs2223.algorithms.week1.cllam.TwoQueueStackPushOptimized;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    Stack<String> stack;

    @BeforeEach
    public void setUp(){
        stack = new TwoQueueStackPopOptimized<>();
    }

    @Test
    public void pushPop_ordered(){
        stack.push("first");
        stack.push("second");
        stack.push("third");

        assertFalse(stack.isEmpty());

        assertEquals("third", stack.pop());
        assertEquals("second", stack.pop());
        assertEquals("first", stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushPop_mixed(){
        assertNull(stack.pop());

        stack.push("first");
        stack.push("second");

        assertEquals("second", stack.pop());
        assertFalse(stack.isEmpty());

        stack.push("third");
        stack.push("fourth");

        assertEquals("fourth", stack.pop());
        assertEquals("third", stack.pop());
        assertEquals("first", stack.pop());
        assertNull(stack.pop());

        assertTrue(stack.isEmpty());
    }

}