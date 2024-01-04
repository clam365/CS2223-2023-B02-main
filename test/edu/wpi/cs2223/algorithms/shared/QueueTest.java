package edu.wpi.cs2223.algorithms.shared;

import edu.wpi.cs2223.algorithms.week1.cllam.FixedCapacityArrayQueue;
import edu.wpi.cs2223.algorithms.week1.cllam.ResizingArrayQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    Queue<String> queue;

    @BeforeEach
    public void setUp(){
        queue = new ResizingArrayQueue<>(1);
    }

    @Test
    public void enqueueDequeue_ordered(){
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");

        assertFalse(queue.isEmpty());

        assertEquals("first", queue.dequeue());
        assertEquals("second", queue.dequeue());
        assertEquals("third", queue.dequeue());

        assertTrue(queue.isEmpty());
        assertNull(queue.dequeue());
    }

    @Test
    public void enqueueDequeue_mixed(){
        queue.enqueue("first");
        queue.enqueue("second");

        assertEquals("first", queue.dequeue());

        queue.enqueue("third");
        queue.enqueue("fourth");

        assertEquals("second", queue.dequeue());
        assertFalse(queue.isEmpty());

        assertEquals("third", queue.dequeue());
        assertFalse(queue.isEmpty());

        assertEquals("fourth", queue.dequeue());
        assertTrue(queue.isEmpty());
        assertNull(queue.dequeue());
    }
}