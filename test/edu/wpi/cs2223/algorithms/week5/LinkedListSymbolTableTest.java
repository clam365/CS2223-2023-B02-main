package edu.wpi.cs2223.algorithms.week5;

import edu.wpi.cs2223.algorithms.week5.cllam.LinkedListSymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSymbolTableTest {
    LinkedListSymbolTable<Integer, String> symbolTable;

    @BeforeEach
    public void setUp(){
        symbolTable = new LinkedListSymbolTable<>();
    }

    @Test
    public void emptyTable(){
        assertTrue(symbolTable.isEmpty());
        assertEquals(0, symbolTable.size());

        assertNull(symbolTable.get(1));
        assertFalse(symbolTable.contains(1));
    }

    @Test
    public void singleNode(){
        symbolTable.put(1, "one");
        assertEquals(1, symbolTable.size());

        assertFalse(symbolTable.isEmpty());

        assertTrue(symbolTable.contains(1));

        assertFalse(symbolTable.contains(2));
        assertNull(symbolTable.get(2));

        assertEquals("one", symbolTable.get(1));
    }

    @Test
    public void twoNodes(){
        symbolTable.put(1, "one");
        symbolTable.put(2, "two");
        assertEquals(2, symbolTable.size());

        assertFalse(symbolTable.isEmpty());

        assertTrue(symbolTable.contains(1));
        assertTrue(symbolTable.contains(2));

        assertFalse(symbolTable.contains(3));
        assertNull(symbolTable.get(3));

        assertEquals("one", symbolTable.get(1));
        assertEquals("two", symbolTable.get(2));
    }

    @Test
    public void twoNodesWithDelete(){
        symbolTable.put(3, "three");
        symbolTable.put(1, "one");
        symbolTable.put(2, "two");
        assertEquals(3, symbolTable.size());

        assertEquals("one", symbolTable.delete(1));
        assertEquals(2, symbolTable.size());

        assertFalse(symbolTable.isEmpty());

        assertTrue(symbolTable.contains(2));
        assertTrue(symbolTable.contains(3));

        assertFalse(symbolTable.contains(1));
        assertNull(symbolTable.get(1));

        assertNull(symbolTable.delete(100)); //if node does not exist at all
        assertEquals(2, symbolTable.size());

        assertEquals("two", symbolTable.get(2));
        assertEquals("three", symbolTable.get(3));
    }

    @Test
    public void twoNodesResetVale(){
        symbolTable.put(1, "one");
        symbolTable.put(2, "two");
        symbolTable.put(1, "again");
        assertEquals(2, symbolTable.size());

        assertFalse(symbolTable.isEmpty());

        assertTrue(symbolTable.contains(1));
        assertTrue(symbolTable.contains(2));

        assertFalse(symbolTable.contains(3));
        assertNull(symbolTable.get(3));

        assertEquals("again", symbolTable.get(1));
        assertEquals("two", symbolTable.get(2));
    }

    @Test
    public void multipleNodesOutOfOrder(){
        symbolTable.put(8, "eight");
        symbolTable.put(3, "three");
        symbolTable.put(6, "six");
        symbolTable.put(11, "eleven");
        symbolTable.put(1, "one");
        assertEquals(5, symbolTable.size());

        // repeat values
        symbolTable.put(6, "six-new");
        symbolTable.put(1, "one-new");
        assertEquals(5, symbolTable.size());

        assertFalse(symbolTable.isEmpty());

        assertTrue(symbolTable.contains(1));
        assertTrue(symbolTable.contains(3));
        assertTrue(symbolTable.contains(6));
        assertTrue(symbolTable.contains(8));
        assertTrue(symbolTable.contains(11));

        assertFalse(symbolTable.contains(4));
        assertNull(symbolTable.get(4));

        assertEquals("one-new", symbolTable.get(1));
        assertEquals("three", symbolTable.get(3));
        assertEquals("six-new", symbolTable.get(6));
        assertEquals("eight", symbolTable.get(8));
        assertEquals("eleven", symbolTable.get(11));
    }

    @Test
    public void multipleNodesOutOfOrderWithDeleteAndPutBack(){
        symbolTable.put(8, "eight");
        symbolTable.put(3, "three");
        symbolTable.put(6, "six");
        symbolTable.put(11, "eleven");
        symbolTable.put(1, "one");
        assertEquals(5, symbolTable.size());

        // delete a value
        symbolTable.delete(3);
        assertEquals(4, symbolTable.size());

        // repeat values
        symbolTable.put(6, "six-new");
        symbolTable.put(1, "one-new");

        assertFalse(symbolTable.isEmpty());

        assertTrue(symbolTable.contains(1));
        assertTrue(symbolTable.contains(6));
        assertTrue(symbolTable.contains(8));
        assertTrue(symbolTable.contains(11));

        assertFalse(symbolTable.contains(3));
        assertFalse(symbolTable.contains(4));
        assertNull(symbolTable.get(3));
        assertNull(symbolTable.get(4));

        assertEquals("one-new", symbolTable.get(1));
        assertEquals("six-new", symbolTable.get(6));
        assertEquals("eight", symbolTable.get(8));
        assertEquals("eleven", symbolTable.get(11));

        symbolTable.put(3, "three-new");
        assertTrue(symbolTable.contains(3));
        assertEquals("three-new", symbolTable.get(3));
    }

}