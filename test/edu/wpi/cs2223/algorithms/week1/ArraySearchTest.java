package edu.wpi.cs2223.algorithms.week1;

import edu.wpi.cs2223.algorithms.week1.ArraySearch;
import edu.wpi.cs2223.algorithms.week1.BinarySearchRecursive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArraySearchTest {
    ArraySearch search;

    @BeforeEach
    public void setUp(){
        search = new BinarySearchRecursive();
//        search = new BinarySearch();
//        search = new LinearSearch();
    }

    @Test
    public void find_falseEmptyArray() {
        int[] inputArray = new int[]{};
        assertEquals(-1, search.find(inputArray, 101));
    }

    @Test
    public void find_false() {
        int[] inputArray = new int[]{1, 9, 100, 564, 999};
        assertEquals(-1, search.find(inputArray, 101));
    }

    @Test
    public void find_falseOutOfRangeLow() {
        int[] inputArray = new int[]{1, 9, 100, 564, 999};
        assertEquals(-1, search.find(inputArray, -101));
    }

    @Test
    public void find_falseOutOfRangeHigh() {
        int[] inputArray = new int[]{1, 9, 100, 564, 999};
        assertEquals(-1, search.find(inputArray, 9001));
    }

    @Test
    public void find_trueSingleValue() {
        int[] inputArray = new int[]{1};
        assertEquals(0, search.find(inputArray, 1));
    }

    @Test
    public void find_trueLeftOfMidpoint() {
        int[] inputArray = new int[]{1, 9, 100, 564, 999};
        assertEquals(1, search.find(inputArray, 9));
    }

    @Test
    public void find_trueRightOfMidpoint() {
        int[] inputArray = new int[]{1, 9, 100, 564, 999};
        assertEquals(3, search.find(inputArray, 564));
    }
}