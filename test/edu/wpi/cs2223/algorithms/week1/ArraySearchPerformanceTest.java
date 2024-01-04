package edu.wpi.cs2223.algorithms.week1;

import edu.wpi.cs2223.algorithms.week1.ArraySearch;
import edu.wpi.cs2223.algorithms.week1.BinarySearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArraySearchPerformanceTest {
    ArraySearch search;

    static final List<Integer> PERFORMANCE_ARRAY_SIZES =
            Arrays.asList(
                    (int) Math.pow(2, 12),
                    (int) Math.pow(2, 13),
                    (int) Math.pow(2, 14),
                    (int) Math.pow(2, 15) * 10,
                    (int) Math.pow(2, 15) * 100,
                    (int) Math.pow(2, 15) * 1000);

    @BeforeEach
    public void setUp(){
        search = new BinarySearch();
//        search = new LinearSearch();
//        search = new BinarySearchRecursive();
    }

    @Test
    public void measurePerformance(){
        for (int inputArraySize : PERFORMANCE_ARRAY_SIZES) {
            int[] inputArray = generateRandomSortedArray(inputArraySize);
            search.find(inputArray, new Random().nextInt());
        }
    }

    /**
     * Generate an array of sorted random integers of the specified size.
     */
    int[] generateRandomSortedArray(int arraySize) {
        Random randomGenerator = new Random();

        List<Integer> input = new ArrayList<>();
        for (int generationIndex = 0; generationIndex < arraySize; generationIndex++) {
            input.add(randomGenerator.nextInt());
        }
        return input.stream().sorted().mapToInt(x -> x).toArray();
    }
}
