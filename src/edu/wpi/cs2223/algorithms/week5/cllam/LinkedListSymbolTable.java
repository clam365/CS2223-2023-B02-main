package edu.wpi.cs2223.algorithms.week5.cllam;

import edu.wpi.cs2223.algorithms.week4.SymbolTable;

/**
 * Implements the SymbolTable interface via a backing Linked List.
 *
 * 1. Running time of put(): O(N); one while loop iteration to find if there are any duplicates
 *    Everything else is O(1) just to insert one in or update value, but O(N) on Tildes' triumphs
 *
 *    Running time of get(): O(N); while loop to traverse through and find what we need
 *
 *    Running time of delete(): O(N); while loop to traverse through and find if key exists to be deleted
 *    Everything else is O(1) to determine outcomes based on boolean switches
 *
 *    Running time of contains(): O(N); while loop to traverse through and find if key exists
 *
 *    Running time of isEmpty(): O(1); just a quick if check if no nodes exist
 *
 *    Running time of size(): O(1); based on switches, if statements will increment (put) or
 *    decrement (delete)
 *
 * 2. The LinkedList SymbolTable implementation is not ideal because
 *          a. You don't have random access like arrays; no indexes
 *          b. Sequential Search: you have to go through each node one by one which is time costly
 */
public class LinkedListSymbolTable<Key extends Comparable, Value> implements SymbolTable<Key, Value> {

    SymbolTableNode<Key, Value> head;
    int sizeCounter = 0;

    //Switches
    boolean switchDelete = false;
    boolean switchPut = false;

    @Override
    public void put(Key key, Value value) {
        SymbolTableNode current = head; //pointer
        boolean isDuplicate = contains(key); //checks if key already exists

        //Iterating through to update the value
        if (isDuplicate == true) {
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    break;
                }
                current = current.next;
            }
        }

        //If there is no duplicate, key does not exist, so we put the key in the front of the linked list
        else {
            SymbolTableNode temp = new SymbolTableNode<>(key, value, head);
            head = temp;
            switchPut = true; //increment size by 1
            size();
        }
    }

    @Override
    public Value get(Key key) {
        SymbolTableNode current = head; //pointer

        // Same thing like contain(), iterate through with loop and find the value
        // and cast it b/c it's still an obj
        while (current != null) {
            if (current.key.equals(key)) {
                Value solution = (Value) current.value;
                return solution;
            }
            current = current.next;
        }

        return null; //if it doesn't exist
    }

    @Override
    public Value delete(Key key) {

        Value deleted = null; //value we are outputting

        //Keeping track of previous of current, current
        SymbolTableNode previous = null;
        SymbolTableNode current = head;

        // Loop to find value
        boolean found = false;
        while (current != null) {
            if (current.key.equals(key)) {
                found = true;
                deleted = (Value)current.value;
                previous.next = current.next; //switching pointers
                break;
            }
            previous = current;
            current = current.next;
        }

        //If we did not find any key to delete, return null
        if (found != true) {
            return deleted;
        }

        //Else, turn on our switch, call size() to decrement, and return our deleted
        else {
            switchDelete = true; //turning switch on
            size(); //calling size method
            return deleted;
        }

    }

    @Override
    public boolean contains(Key key) {

        // Pointer
        SymbolTableNode current = head;

        //While Loop to iterate through and find if it contains it
        while(current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }

        return false; //if it's not there
    }

    @Override
    public boolean isEmpty() {
        // Checks if this is empty of course and returns the outcome
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {

        //if put() was used, switch is on and will INCREMENT, then turning it off
        if (switchPut == true) {
            sizeCounter++;
            switchPut = false;
        }

        //if delete() was used, switch is on and will DECREMENT, then turning it off
        if (switchDelete == true) {
            sizeCounter--;
            switchDelete = false;
        }

        return sizeCounter;
    }

}
