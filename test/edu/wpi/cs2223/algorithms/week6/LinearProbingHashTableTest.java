package edu.wpi.cs2223.algorithms.week6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.wpi.cs2223.algorithms.week6.cllam.LinearProbingHashTable ;
import static org.junit.jupiter.api.Assertions.*;

class LinearProbingHashTableTest {

    LinearProbingHashTable<Integer, String> bstSymbolTable;

    @BeforeEach
    public void setUp(){
        bstSymbolTable = new LinearProbingHashTable<>(10);
    }

    @Test
    public void emptyTable(){
        assertTrue(bstSymbolTable.isEmpty());
        assertEquals(0, bstSymbolTable.size());

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
    }

    @Test
    public void collisionsTest() {
        bstSymbolTable.put(8, "eight");
        bstSymbolTable.put(18, "eighteen");
        bstSymbolTable.put(28, "twenty-eight");
        bstSymbolTable.put(9, "nine");
        bstSymbolTable.put(19, "nine-teen");
        assertEquals(5, bstSymbolTable.size());

        assertEquals("nine-teen", bstSymbolTable.get(19));
        assertEquals("nine", bstSymbolTable.get(9));
        assertEquals("eight", bstSymbolTable.get(8));
        assertEquals("eighteen", bstSymbolTable.get(18));
        assertEquals("twenty-eight", bstSymbolTable.get(28));
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
    public void deleteBreaksCluster(){
        bstSymbolTable.put(3, "three");
        bstSymbolTable.put(13, "thirteen");
        bstSymbolTable.put(23, "twentythree");
        bstSymbolTable.put(4, "four");
        bstSymbolTable.put(14, "fourteen");
        bstSymbolTable.put(33, "thirtythree");
        bstSymbolTable.put(7, "seven");

        assertEquals(7, bstSymbolTable.size());
        assertEquals("three", bstSymbolTable.get(3));
        assertEquals("thirteen", bstSymbolTable.get(13));
        assertEquals("twentythree", bstSymbolTable.get(23));
        assertEquals("four", bstSymbolTable.get(4));
        assertEquals("fourteen", bstSymbolTable.get(14));
        assertEquals("thirtythree", bstSymbolTable.get(33));
        assertEquals("seven", bstSymbolTable.get(7));

        assertEquals("twentythree", bstSymbolTable.delete(23));

        assertEquals(6, bstSymbolTable.size());

        assertEquals("three", bstSymbolTable.get(3));
        assertEquals("thirteen", bstSymbolTable.get(13));
        assertNull(bstSymbolTable.get(23));

        assertEquals("four", bstSymbolTable.get(4));
        assertEquals("fourteen", bstSymbolTable.get(14));
        assertEquals("thirtythree", bstSymbolTable.get(33));
        assertEquals("seven", bstSymbolTable.get(7));
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