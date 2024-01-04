package edu.wpi.cs2223.algorithms.week5.cllam;

import edu.wpi.cs2223.algorithms.week4.BSTNode;
import edu.wpi.cs2223.algorithms.week4.SymbolTable;

/**
 * Binary Search backed implementation of a Symbol Table.  Deletes are implemented lazily - nodes are deactivated
 * but remain in the tree.
 *
 * @param <Key> type of keys in the symbol table
 * @param <Value> type of values in the symbol table
 *
 * 1. Running time of put(): O(N); it's O(1) if the head is empty but any other following nodes
 *    will be O(N) to traverse through entire BST to find the correct place to put it in.
 *
 *    Running time of get(): O(N); loop to traverse through BST to find key, worst case is at lowest depth.
 *
 *    Running time of delete(): O(N); it just requires a switch to say this node is invalid, but must
 *    iterate through BST to find it.
 *
 *    Running time of contain(): O(N); almost the same function as get(), just returns the boolean version
 *
 *    Running time of isEmpty(): O(1); just one check at the head with an if statement.
 *
 *    Running time of size(): O(1); increments and decrements based on call from switches.
 *
 * 2. Lazy Deletion implementation is not ideal because what if we had a BST full of deleted nodes? We would
 *    still have to traverse through ALL of them instead of just removing it completely for performance.
 */
public class BSTSymbolTableLazyDelete<Key extends Comparable, Value> implements SymbolTable<Key, Value> {

    BSTNode<Key, Value> head;
    int sizeCounter = 0;
    boolean switchPut = false;
    boolean switchDelete = false;

    @Override
    public void put(Key key, Value value) {

        BSTNode current = head; //pointer to head
        BSTNode parentNode = null;
        boolean isDuplicate = contains(key); //true or false if it has it

        //Base Case of creating the root
        if (head == null) {
            head = new BSTNode<>(null, null, key, value);
            //Incrementing size
            switchPut = true;
            size();
            return; //end entire program
        }

        //If key already exists, we will update its value instead
        if (isDuplicate == true) {
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; //Updating our value
                    return;
                }
                else {
                    if (key.compareTo(current.key) < 0) {
                        current = current.leftChild; //key < BST current key go left
                    }
                    else {
                        current = current.rightChild; //key > BST current key go right
                    }
                }
            }
        }

        //If key does not previously exist (isDuplicate == false), add it to the BST
        else {
            // Loop to iterate through BST until it reaches the bottom depth of where we need to go for insert
            while (current != null) {
                if (key.compareTo(current.key) < 0) {
                    parentNode = current;
                    current = current.leftChild; //key < BST current key go left
                }
                else {
                    parentNode = current;
                    current = current.rightChild; //key > BST current key go right
                }

            }

            // We are now inserting it in
            if (key.compareTo(parentNode.key) < 0) {
                parentNode.leftChild = new BSTNode<>(null, null, key, value);
            }
            else {
                parentNode.rightChild = new BSTNode<>(null, null, key, value);
            }

            switchPut = true;
            size(); //we then will increment the BST total node amount
        }
    }

    @Override
    public Value get(Key key) {

        BSTNode current = head; //our pointer

        while (current != null) {
            if (current.key.equals(key) && current.isActive == true) {
                Value solution = (Value) current.value; //Return value of key if found
                return solution;
            }
            else {
                if (key.compareTo(current.key) < 0) { //key < BST current key go left
                    current = current.leftChild;
                }
                else {
                    current = current.rightChild; //key > BST current key go right
                }
             }
        }
        return null; //if key is not found
    }

    @Override
    public Value delete(Key key) {
        BSTNode current = head; //our pointer

        //Loop to find key to be deleted in BST
        while (current != null) {
            // If we find our key, deActivate, turn switchDelete on, decrement size, and return key's value
            if (current.key.equals(key)) {
                Value solution = (Value) current.value;
                switchDelete = true;
                current.deActivate();
                size();
                return solution;
            }
            // If we haven't found key, keep iterating based on BST laws
            else {
                if (key.compareTo(current.key) < 0) {
                    current = current.leftChild;
                }
                else {
                    current = current.rightChild;
                }
            }
        }
        return null; // when we don't find the key to delete at all
    }

    @Override
    public boolean contains(Key key) {

        BSTNode current = head; //our pointer

        // Loop to iterate through BST
        while (current != null) {
            if (current.key.equals(key) && current.isActive == true) { //Return True if it is found
                return true;
            }

            else { //Go down depth in BST
                if (key.compareTo(current.key) < 0) {
                    current = current.leftChild; //if key < BST current key
                }
                else {
                    current = current.rightChild; //if key > BST current key
                }
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {

        // Checks if this is empty of course and returns the outcome
        if (head == null && sizeCounter == 0) {
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

    /**
     * Helper method to locate a node and its parent for a given key.  The parent reference is helpful when performing
     * manipulation on the BST.  If the key is found in the root, the parent reference will be null.
     */
    ParentAndNode locate(Key target){
        if (head == null){
            return new ParentAndNode(null, null);
        }

        BSTNode<Key, Value> current = head;
        BSTNode<Key, Value> previous = null;

        while (current != null){
            if (current.key.equals(target)) {
                return new ParentAndNode(previous, current);
            }

            previous = current;

            if (target.compareTo(current.key) < 0) { //let's use this scaffold
                current = current.leftChild;
            }

            else {
                current = current.rightChild;
            }
        }

        return new ParentAndNode(previous, null);
    }

    /**
     * Simple, internal tuple of a node and its parent.
     */
    class ParentAndNode{
        final BSTNode<Key, Value> parent;
        final BSTNode<Key, Value> node;

        public ParentAndNode(BSTNode<Key, Value> parent, BSTNode<Key, Value> node) {
            this.parent = parent;
            this.node = node;
        }
    }

    /**
     * Builds up a string representation of this BST.  An attempt is made to make all spacing as true to the structure
     * of the tree as possible.  For example, the following invocations:
     * bstSymbolTable.put(6, "six");
     * bstSymbolTable.put(3, "three");
     * bstSymbolTable.put(8, "eight");
     *
     * Should result in a string that looks like this:
     *         6
     *       3   8
     */
    public String treeDiagram(){
        String[] keys = new String[10];
        buildDebugInfoForLevel(head, keys, 0);

        String result = "";
        int level = 0;

        int totalLevels = 0;
        while (keys[totalLevels] != null) {
            totalLevels++;
        }

        while (keys[level] != null) {
            String indent = "";
            for (int indentIndex = 0; indentIndex < (totalLevels - level); indentIndex++){
                indent += " ";
            }

            result = result + indent + keys[level] + " \n";
            level++;
        }

        return result;
    }

    /**
     * Helper for the treeDiagram utility.
     */
    void buildDebugInfoForLevel(BSTNode<Key, Value> node, String[] keys, int level) {
        if (node == null) {
            return;
        }

        if (keys[level] == null) {
            keys[level] = "";
        }

        keys[level] += " " + node.key;
        level++;

        buildDebugInfoForLevel(node.leftChild, keys, level);
        buildDebugInfoForLevel(node.rightChild, keys, level);
    }
}
