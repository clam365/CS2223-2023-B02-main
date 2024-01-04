package edu.wpi.cs2223.algorithms.week5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.wpi.cs2223.algorithms.week5.cllam.BSTSymbolTableHibbardDelete;
import static org.junit.jupiter.api.Assertions.*;

class BSTSymbolTableHibbardDeleteTest {

    BSTSymbolTableHibbardDelete<Integer, String> bstSymbolTable;

    @BeforeEach
    public void setUp(){
        bstSymbolTable = new BSTSymbolTableHibbardDelete<>();
    }

    @Test
    public void emptyTable(){
        assertEquals(0, bstSymbolTable.size());
        assertTrue(bstSymbolTable.isEmpty());
        assertNull(bstSymbolTable.get(1));
        assertFalse(bstSymbolTable.contains(1));
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

    }

    @Test
    public void caseOneNoChildren(){
        bstSymbolTable.put(1, "one");
        bstSymbolTable.put(2, "two");
        assertEquals(2, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertTrue(bstSymbolTable.contains(2));

        assertEquals("two", bstSymbolTable.delete(2));
        assertEquals(1, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertFalse(bstSymbolTable.contains(2));
        assertNull(bstSymbolTable.get(2));
    }

    @Test
    public void caseTwoLeftChild(){
        bstSymbolTable.put(1, "one");
        bstSymbolTable.put(10, "ten");
        bstSymbolTable.put(5, "five");
        bstSymbolTable.put(6, "six");
        System.out.println(bstSymbolTable.treeDiagram());
        assertEquals(4, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertTrue(bstSymbolTable.contains(10));
        assertTrue(bstSymbolTable.contains(5));
        assertTrue(bstSymbolTable.contains(6));

        assertEquals("ten", bstSymbolTable.delete(10));
        assertEquals(3, bstSymbolTable.size());

        System.out.println(bstSymbolTable.treeDiagram());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertTrue(bstSymbolTable.contains(5));
        assertTrue(bstSymbolTable.contains(6));

        assertFalse(bstSymbolTable.contains(10));
        assertNull(bstSymbolTable.get(10));
    }

    @Test
    public void caseTwoRightChild(){
        bstSymbolTable.put(7, "seven");
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(5, "five");
        bstSymbolTable.put(4, "four");
        assertEquals(4, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(7));
        assertTrue(bstSymbolTable.contains(3));
        assertTrue(bstSymbolTable.contains(5));
        assertTrue(bstSymbolTable.contains(4));

        assertEquals("three", bstSymbolTable.delete(3));

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(7));
        assertTrue(bstSymbolTable.contains(5));
        assertTrue(bstSymbolTable.contains(4));

        assertFalse(bstSymbolTable.contains(3));
        assertNull(bstSymbolTable.get(3));
    }

    @Test
    public void findSuccessor(){
        bstSymbolTable.put(7, "seven");
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(2, "two");
        bstSymbolTable.put(5, "five");
        bstSymbolTable.put(4, "four");
        bstSymbolTable.put(6, "six");
        System.out.println(bstSymbolTable.treeDiagram());

        assertEquals(3, bstSymbolTable.head.leftChild.key); //this is correct
        assertEquals(4, bstSymbolTable.findSuccessor(bstSymbolTable.head.leftChild).node.key);
        assertEquals(5, bstSymbolTable.findSuccessor(bstSymbolTable.head.leftChild).parent.key);
    }

    @Test
    public void caseThreeTwoChildren(){
        bstSymbolTable.put(7, "seven");
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(2, "two");
        bstSymbolTable.put(5, "five");
        bstSymbolTable.put(4, "four");
        bstSymbolTable.put(6, "six");
        assertEquals(6, bstSymbolTable.size());

        System.out.println(bstSymbolTable.treeDiagram());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(7));
        assertTrue(bstSymbolTable.contains(3));
        assertTrue(bstSymbolTable.contains(2));
        assertTrue(bstSymbolTable.contains(5));
        assertTrue(bstSymbolTable.contains(4));
        assertTrue(bstSymbolTable.contains(6));

        assertEquals("three", bstSymbolTable.delete(3));
        assertEquals(5, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(7));
        assertTrue(bstSymbolTable.contains(2));
        assertTrue(bstSymbolTable.contains(5));
        assertTrue(bstSymbolTable.contains(4));
        assertTrue(bstSymbolTable.contains(6));

        assertFalse(bstSymbolTable.contains(3));
        assertNull(bstSymbolTable.get(3));
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
    }

    @Test
    public void twoNodesWithDelete(){
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(1, "one");
        bstSymbolTable.put(2, "two");
        assertEquals(3, bstSymbolTable.size());

        bstSymbolTable.delete(1);
        assertEquals(2, bstSymbolTable.size());

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(2));
        assertTrue(bstSymbolTable.contains(3));

        assertFalse(bstSymbolTable.contains(1));
        assertNull(bstSymbolTable.get(1));

        assertEquals("two", bstSymbolTable.get(2));
        assertEquals("three", bstSymbolTable.get(3));
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
    }

    @Test
    public void multipleNodesOutOfOrder(){
        bstSymbolTable.put(8, "eight");
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(6, "six");
        bstSymbolTable.put(11, "eleven");
        bstSymbolTable.put(1, "one");

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
    }

    @Test
    public void multipleNodesOutOfOrderWithDeleteAndPutBack(){
        bstSymbolTable.put(8, "eight");
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(6, "six");
        bstSymbolTable.put(11, "eleven");
        bstSymbolTable.put(1, "one");
        assertEquals(5, bstSymbolTable.size());

        // delete a value
        bstSymbolTable.delete(3);
        assertEquals(4, bstSymbolTable.size());

        // repeat values
        bstSymbolTable.put(6, "six-new");
        bstSymbolTable.put(1, "one-new");

        assertFalse(bstSymbolTable.isEmpty());

        assertTrue(bstSymbolTable.contains(1));
        assertTrue(bstSymbolTable.contains(6));
        assertTrue(bstSymbolTable.contains(8));
        assertTrue(bstSymbolTable.contains(11));

        assertFalse(bstSymbolTable.contains(3));
        assertFalse(bstSymbolTable.contains(4));
        assertNull(bstSymbolTable.get(3));
        assertNull(bstSymbolTable.get(4));

        assertEquals("one-new", bstSymbolTable.get(1));
        assertEquals("six-new", bstSymbolTable.get(6));
        assertEquals("eight", bstSymbolTable.get(8));
        assertEquals("eleven", bstSymbolTable.get(11));

        bstSymbolTable.put(3, "three-new");
        assertEquals(5, bstSymbolTable.size());
        assertTrue(bstSymbolTable.contains(3));
        assertEquals("three-new", bstSymbolTable.get(3));
    }
}