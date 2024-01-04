package edu.wpi.cs2223.algorithms.week5;

import edu.wpi.cs2223.algorithms.week5.cllam.RedBlackBSTSymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackBSTSymbolTableTest {

    RedBlackBSTSymbolTable<Integer, String> bstSymbolTable;

    @BeforeEach
    public void setUp(){
        bstSymbolTable = new RedBlackBSTSymbolTable<>();
    }

    @Test
    public void emptyTable(){
        assertTrue(bstSymbolTable.isEmpty());
        assertEquals(0, bstSymbolTable.size());

        assertNull(bstSymbolTable.get(1));
        assertFalse(bstSymbolTable.contains(1));

        assertTrue(bstSymbolTable.isValidRedBlackTree());
        assertTrue(bstSymbolTable.isTreeBalanced());
    }

    @Test
    public void singleNode(){
        bstSymbolTable.put(1, "one");
        assertEquals(1, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));

        assertFalse(bstSymbolTable.contains(2));
        assertNull(bstSymbolTable.get(2));

        assertEquals("one", bstSymbolTable.get(1));

        assertTrue(bstSymbolTable.isValidRedBlackTree());
        assertTrue(bstSymbolTable.isTreeBalanced());
    }

    @Test
    public void twoNodes(){
        bstSymbolTable.put(1, "one");
        bstSymbolTable.put(2, "two");
        assertEquals(2, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertTrue(bstSymbolTable.contains(2));

        assertFalse(bstSymbolTable.contains(3));
        assertNull(bstSymbolTable.get(3));

        assertEquals("one", bstSymbolTable.get(1));
        assertEquals("two", bstSymbolTable.get(2));

        assertTrue(bstSymbolTable.isValidRedBlackTree());
        assertTrue(bstSymbolTable.isTreeBalanced());
    }

    @Test
    public void twoNodesResetVale(){
        bstSymbolTable.put(1, "one");
        bstSymbolTable.put(2, "two");
        bstSymbolTable.put(1, "again");
        assertEquals(2, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertTrue(bstSymbolTable.contains(2));

        assertFalse(bstSymbolTable.contains(3));
        assertNull(bstSymbolTable.get(3));

        assertEquals("again", bstSymbolTable.get(1));
        assertEquals("two", bstSymbolTable.get(2));

        System.out.println(bstSymbolTable.treeDiagram());
        assertTrue(bstSymbolTable.isValidRedBlackTree());
        assertTrue(bstSymbolTable.isTreeBalanced());
    }

    @Test
    public void multipleNodesOutOfOrder(){
        bstSymbolTable.put(8, "eight");
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(6, "six");
        bstSymbolTable.put(11, "eleven");
        bstSymbolTable.put(1, "one");
        assertEquals(5, bstSymbolTable.size());

        // repeat values
        bstSymbolTable.put(6, "six-new");
        bstSymbolTable.put(1, "one-new");
        assertEquals(5, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertTrue(bstSymbolTable.contains(3));
        assertTrue(bstSymbolTable.contains(6));
        assertTrue(bstSymbolTable.contains(8));
        assertTrue(bstSymbolTable.contains(11));

        assertFalse(bstSymbolTable.contains(4));
        assertNull(bstSymbolTable.get(4));

        assertEquals("one-new", bstSymbolTable.get(1));
        assertEquals("three", bstSymbolTable.get(3));
        assertEquals("six-new", bstSymbolTable.get(6));
        assertEquals("eight", bstSymbolTable.get(8));
        assertEquals("eleven", bstSymbolTable.get(11));

        System.out.println(bstSymbolTable.treeDiagram());
        assertTrue(bstSymbolTable.isValidRedBlackTree());
        assertTrue(bstSymbolTable.isTreeBalanced());
    }

    @Test
    public void multipleNodesPoorBalance() {
        bstSymbolTable.put(8, "eight");
        bstSymbolTable.put(7, "seven");
        bstSymbolTable.put(6, "six");
        bstSymbolTable.put(5, "five");
        bstSymbolTable.put(4, "four");
        assertEquals(5, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(8));
        assertTrue(bstSymbolTable.contains(7));
        assertTrue(bstSymbolTable.contains(6));
        assertTrue(bstSymbolTable.contains(5));
        assertTrue(bstSymbolTable.contains(4));

        System.out.println(bstSymbolTable.treeDiagram());
        assertTrue(bstSymbolTable.isValidRedBlackTree());
        assertTrue(bstSymbolTable.isTreeBalanced());
    }
}