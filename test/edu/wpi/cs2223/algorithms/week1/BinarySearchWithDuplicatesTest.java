package edu.wpi.cs2223.algorithms.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchWithDuplicatesTest {

    BinarySearchWithDuplicates binarySearchWithDuplicates;

    @BeforeEach
    public void setUp(){
        binarySearchWithDuplicates = new BinarySearchWithDuplicates();
    }

    @Test
    public void count_emptyArray(){
        int[] inputArray = new int[]{};
        assertEquals(0, binarySearchWithDuplicates.count(inputArray, 6));
    }

    @Test
    public void count_IntegerNotInArray(){
        int[] inputArray = new int[]{1, 1, 3, 5, 5, 9};
        assertEquals(0, binarySearchWithDuplicates.count(inputArray, 6));
    }

    @Test
    public void count_Single(){
        int[] inputArray = new int[]{1, 1, 3, 5, 5, 9};
        assertEquals(1, binarySearchWithDuplicates.count(inputArray, 3));
    }

    @Test
    public void count_Multiple(){
        int[] inputArray = new int[]{1, 1, 3, 5, 5, 9};
        assertEquals(2, binarySearchWithDuplicates.count(inputArray, 5));
    }



}