package edu.wpi.cs2223.algorithms.week1;

import edu.wpi.cs2223.algorithms.shared.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListSurvivorTest {

    @Test
    public void fourPeopleCountPerRound(){
        Node<String> bob = new Node<>(null, "bob");
        Node<String> sally = new Node<>(bob, "sally");
        Node<String> rose = new Node<>(sally, "rose");
        Node<String> james = new Node<>(rose, "james");
        bob.next = james;

        // STARTING circle: bob -> james -> rose -> sally -> circular back to bob...
        // STARTING person: bob
        // COUNT PER ROUND: 1

        CircularLinkedListSurvivor circularLinkedListSurvivor = new CircularLinkedListSurvivor(1);
        Node<String> result = circularLinkedListSurvivor.play(bob);

        assertEquals("bob", result.value);
        assertEquals("james", result.next.value);
        assertEquals("rose", result.next.next.value);
        assertEquals("sally", result.next.next.next.value);
        assertNull(result.next.next.next.next);
    }

    @Test
    public void fourPeopleTwoCountPerRound(){
        Node<String> bob = new Node<>(null, "bob");
        Node<String> sally = new Node<>(bob, "sally");
        Node<String> rose = new Node<>(sally, "rose");
        Node<String> james = new Node<>(rose, "james");
        bob.next = james;

        // STARTING circle: bob -> james -> rose -> sally -> circular back to bob...
        // STARTING person: bob
        // COUNT PER ROUND: 2

        CircularLinkedListSurvivor circularLinkedListSurvivor = new CircularLinkedListSurvivor(2);
        Node<String> result = circularLinkedListSurvivor.play(bob);

        assertEquals("james", result.value);
        assertEquals("sally", result.next.value);
        assertEquals("rose", result.next.next.value);
        assertEquals("bob", result.next.next.next.value);
        assertNull(result.next.next.next.next);
    }

    @Test
    public void fourPeopleThreeCountPerRound(){
        Node<String> bob = new Node<>(null, "bob");
        Node<String> sally = new Node<>(bob, "sally");
        Node<String> rose = new Node<>(sally, "rose");
        Node<String> james = new Node<>(rose, "james");
        bob.next = james;

        // STARTING circle: bob -> james -> rose -> sally -> circular back to bob...
        // STARTING person: bob
        // COUNT PER ROUND: 3

        CircularLinkedListSurvivor circularLinkedListSurvivor = new CircularLinkedListSurvivor(3);
        Node<String> result = circularLinkedListSurvivor.play(bob);

        assertEquals("rose", result.value);
        assertEquals("james", result.next.value);
        assertEquals("sally", result.next.next.value);
        assertEquals("bob", result.next.next.next.value);
        assertNull(result.next.next.next.next);
    }

    @Test
    public void fourPeopleFourCountPerRound(){
        Node<String> bob = new Node<>(null, "bob");
        Node<String> sally = new Node<>(bob, "sally");
        Node<String> rose = new Node<>(sally, "rose");
        Node<String> james = new Node<>(rose, "james");
        bob.next = james;

        // STARTING circle: bob -> james -> rose -> sally -> circular back to bob...
        // STARTING person: bob
        // COUNT PER ROUND: 4

        CircularLinkedListSurvivor circularLinkedListSurvivor = new CircularLinkedListSurvivor(4);
        Node<String> result = circularLinkedListSurvivor.play(bob);

        assertEquals("sally", result.value);
        assertEquals("bob", result.next.value);
        assertEquals("rose", result.next.next.value);
        assertEquals("james", result.next.next.next.value);
        assertNull(result.next.next.next.next);
    }

    @Test
    public void zeroPeople(){
        // STARTING circle: null
        // STARTING person null
        // COUNT PER ROUND: 1

        CircularLinkedListSurvivor circularLinkedListSurvivor = new CircularLinkedListSurvivor(1);
        Node<String> result = circularLinkedListSurvivor.play(null);

        assertNull(result);
    }

    @Test
    public void onePersonRegardlessOfCountPerRound(){
        Node<String> sally = new Node<>(null, "sally");
        sally.next = sally;

        // STARTING circle: sally
        // STARTING person: sally
        // COUNT PER ROUND: multiple values should all return same result

        CircularLinkedListSurvivor circularLinkedListSurvivor = new CircularLinkedListSurvivor(1);

        Node<String> result = circularLinkedListSurvivor.play(sally);
        assertEquals("sally",result.value);
        assertNull(result.next);
    }

    @Test
    public void sixPeopleEightCountPerRound(){
        Node<String> bob = new Node<>(null, "bob");
        Node<String> sally = new Node<>(bob, "sally");
        Node<String> rose = new Node<>(sally, "rose");
        Node<String> james = new Node<>(rose, "james");
        Node<String> donna = new Node<>(james, "donna");
        Node<String> malcolm = new Node<>(donna, "malcolm");
        bob.next = malcolm;

        // STARTING circle: bob -> malcolm -> donna -> james -> rose -> sally -> circular back to bob...
        // STARTING person bob
        // move eight per round

        CircularLinkedListSurvivor circularLinkedListSurvivor = new CircularLinkedListSurvivor(8);

        Node<String> result = circularLinkedListSurvivor.play(bob);

        assertEquals("malcolm", result.value);
        assertEquals("rose", result.next.value);
        assertEquals("james", result.next.next.value);
        assertEquals("bob", result.next.next.next.value);
        assertEquals("sally", result.next.next.next.next.value);
        assertEquals("donna", result.next.next.next.next.next.value);

        assertNull(result.next.next.next.next.next.next);
    }
}