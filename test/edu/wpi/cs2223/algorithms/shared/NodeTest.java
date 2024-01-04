package edu.wpi.cs2223.algorithms.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    public void threeNodeChain(){
        Node<String> first = new Node<>(null, "I am first");
        Node<String> second = new Node<>(first, "I am second");
        Node<String> third = new Node<>(second, "I am third");

        assertEquals(second, third.next);
        assertEquals(first, second.next);
        assertEquals(first, third.next.next);

        assertEquals("I am first", third.next.next.value);
        assertEquals("I am third", third.value);
    }

    @Test
    public void genericNodes(){
        Node<String> there = new Node<>(null, "there");
        Node<String> hi = new Node<>(there, "hi");

        Node<String> current = hi;
        String output = "";
        while (current != null){
            output += " " + current.value;
            current = current.next;
        }

        System.out.println(output);

        Node<Integer> second = new Node<>(null, 5);
        Node<Integer> first = new Node<>(second, 20);

        Node<Integer> currentInt = first;
        int sum = 0;
        while (currentInt != null){
            sum += currentInt.value;
            currentInt = currentInt.next;
        }

        System.out.println(sum);
    }

}