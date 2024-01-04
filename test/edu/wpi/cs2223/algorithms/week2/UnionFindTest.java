package edu.wpi.cs2223.algorithms.week2;

import edu.wpi.cs2223.algorithms.week2.cllam.WeightedQuickUnionUF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {
    UnionFind unionFind;

    @BeforeEach
    public void setUp(){
        unionFind = new WeightedQuickUnionUF();
    }

    @Test
    public void empty(){
        unionFind.initialize(0);
        assertEquals(0, unionFind.componentCount());
    }

    @Test
    public void singleElement(){
        unionFind.initialize(1);
        assertEquals(1, unionFind.componentCount());
        assertEquals(0, unionFind.find(0));
    }

    @Test
    public void twoElements(){
        unionFind.initialize(2);
        assertEquals(2, unionFind.componentCount());

        assertEquals(0, unionFind.find(0));
        assertEquals(1, unionFind.find(1));

        assertFalse(unionFind.union(0, 1));
        assertTrue(unionFind.union(0, 1));
        assertTrue(unionFind.connected(0, 1));

        assertEquals(1, unionFind.componentCount());
    }

    @Test
    public void tenElements(){
        unionFind.initialize(10);
        assertEquals(10, unionFind.componentCount());

        assertEquals(0, unionFind.find(0));
        assertEquals(1, unionFind.find(1));
        assertEquals(9, unionFind.find(9));

        // Connect 0 and 1
        assertFalse(unionFind.union(0, 1));
        assertTrue(unionFind.union(0, 1));
        assertTrue(unionFind.connected(0, 1));

        assertEquals(9, unionFind.componentCount());

        Set<Integer> possibleIdentifiers = new HashSet<>();
        possibleIdentifiers.add(0);
        possibleIdentifiers.add(1);

        assertTrue(possibleIdentifiers.contains(unionFind.find(0)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(1)));

        // Connect 8 and 9
        assertFalse(unionFind.union(8, 9));
        assertTrue(unionFind.union(8, 9));
        assertTrue(unionFind.connected(8, 9));

        assertEquals(8, unionFind.componentCount());

        possibleIdentifiers.clear();
        possibleIdentifiers.add(8);
        possibleIdentifiers.add(9);

        assertTrue(possibleIdentifiers.contains(unionFind.find(8)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(9)));

        // Connect 5 and 6
        assertFalse(unionFind.union(6, 5));
        assertTrue(unionFind.union(5, 6));
        assertTrue(unionFind.connected(5, 6));

        assertEquals(7, unionFind.componentCount());

        possibleIdentifiers.clear();
        possibleIdentifiers.add(5);
        possibleIdentifiers.add(6);

        assertTrue(possibleIdentifiers.contains(unionFind.find(5)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(6)));

        // Connect 1 and 5.. after this connection we should have the following components:
        // 0,1,5,6
        // 8,9
        // 2
        // 3
        // 4
        // 7
        assertFalse(unionFind.union(5, 1));
        assertTrue(unionFind.union(1, 5));
        assertTrue(unionFind.connected(5, 1));

        assertEquals(6, unionFind.componentCount());

        possibleIdentifiers.clear();
        possibleIdentifiers.add(0);
        possibleIdentifiers.add(1);
        possibleIdentifiers.add(5);
        possibleIdentifiers.add(6);

        assertTrue(possibleIdentifiers.contains(unionFind.find(0)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(1)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(5)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(6)));


        possibleIdentifiers.clear();
        possibleIdentifiers.add(8);
        possibleIdentifiers.add(9);

        assertTrue(possibleIdentifiers.contains(unionFind.find(8)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(9)));

        possibleIdentifiers.clear();
        possibleIdentifiers.add(3);

        assertTrue(possibleIdentifiers.contains(unionFind.find(3)));

        // Connect 3 and 9.. after this connection we should have the following components:
        // 0,1,5,6
        // 3,8,9
        // 2
        // 4
        // 7
        assertFalse(unionFind.union(9, 3));
        assertTrue(unionFind.union(3, 9));
        assertTrue(unionFind.connected(9, 3));

        assertEquals(5, unionFind.componentCount());

        possibleIdentifiers.clear();
        possibleIdentifiers.add(3);
        possibleIdentifiers.add(8);
        possibleIdentifiers.add(9);

        assertTrue(possibleIdentifiers.contains(unionFind.find(3)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(8)));
        assertTrue(possibleIdentifiers.contains(unionFind.find(9)));
    }

    @Test
    public void doublingPerformanceTest(){
        Random random = new Random();

        for (int n = 1; n < Math.pow(2, 14); n = n * 2) {
            unionFind.initialize(n);
            for (int unionsToMake = 0; unionsToMake < n; unionsToMake++) {
                unionFind.union(random.nextInt(n), random.nextInt(n));
            }

            System.out.printf(
                    "Union find with %d elements, after %d union calls, has running time of %d.  Expected running time was %d.\n",
                    n,
                    n,
                    unionFind.countOfNextInvocations(),
                    unionFind.runningTime(n));
        }
    }

}