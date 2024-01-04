package edu.wpi.cs2223.algorithms.week1.cllam;

import edu.wpi.cs2223.algorithms.shared.Node;

/**
 * Implements a game where contestants stand in a circle.  Then starting with the "head" contestant,
 * they count off up to the "countPerRound" value.  The person who says that integer value is eliminated and
 * has to leave the circle.
 *
 * The next round starts with the person right after the one who just got eliminated and once again contestants
 * count up to the "countPerRound" value and whoever says that value leaves the circle.
 *
 * This goes on until the last person is eliminated.
 *
 * Guarantees:
 * * countPerRound is a positive integer
 * * head is the start of a circular linked list
 *
 * Requirements: "play" should return a Node that is the head of a list whose values are the names of the
 * contestants in the order in which they were eliminated.
 *
 * For example, if the game is configured with countPerRound = 2 and the input is :
 *  bob -> james -> rose -> sally -> back to bob,  where bob is the "head" contestant,
 *  this implementation should produce the following result:
 *  james -> sally -> rose -> bob -> null .
 */
public class CircularLinkedListSurvivor {

    /**The number of people that are skipped in each round of elimination. */
    final int countPerRound;

    public CircularLinkedListSurvivor(int countPerRound) {
        this.countPerRound = countPerRound;
    }

    public int counter(Node<String> head) {
        Node<String> copy = head;
        int counter = 1;
        String initial = copy.value;
        while (!initial.equals(copy.next.value) ) {
            counter++;
            copy = copy.next;
        }
        return counter;
    }

    /**
     * Play the game as described in the javadoc of this class.
     *
     * @param head - the head contestant in a circularly linked list of contenders.
     *
     * 1. In terms of N, the runtime if it was empty would be 1. Anything else with more than 1 node is going to be in N^2
     *    because of the while loop and an inner for loop.
     *
     * 2. T(N * M) + 1 b/c of nested loops
     *    T(2N * 2M) --> T(4N * 4M) --> T(8N * 8M) --> ...T(N * M)
     *
    **/
    public Node<String> play(Node<String> head) {

        //Base Case where it is Empty
        if (head == null) {
            return null;
        }

        if (countPerRound == 1) {
            // Counting how many nodes there are in the circular linked list using a helper method
            int size = this.counter(head);

            // Case with literally One Node
            if (size == 1) {
                Node<String> output = new Node<>(null, head.value);
                return output;
            }

            //More than 1 Node (basically detaching last node to head node
            if (size > 1) {
                Node<String> temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                temp.next = null;
                return head;
            }
        }

        //Setting it up for iterations
        Node<String> temp = head; //basically head but a copy
        Node<String> previous = head; //this is the thing before it
        Node<String> solution = null;
        Node<String> solutionTemp = null; //this will help build our actual solution

        while (temp.next != temp) {
            for(int i = 1; i < countPerRound; i++) { // Finding Elimination Player
                previous = temp;
                temp = temp.next;
            }

            if (solution == null) { //This is the very first value
                solution = new Node<>(null, temp.value);
                solutionTemp = solution;
            }

            else { //Every other node going after head node
                Node<String> intoTemp = new Node<>(null, temp.value);
                solutionTemp.next = intoTemp;
                solutionTemp = intoTemp;
            }

            // Disconnecting Eliminated Player from entire circular linked list
            previous.next = temp.next;
            temp = previous.next;
        }

        solutionTemp.next = new Node<>(null, temp.value); //Getting very last node and disconnecting it

        return solution;
    }
}
