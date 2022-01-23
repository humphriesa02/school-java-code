package humphries.hwonefix;

import java.util.Arrays;

/**
 * A MySet class that behaves like a mathematical set.
 * 
 * @author Donald Chinn, modified by [Alex Humphries]
 * @version January 13, 2022
 */
public class MySet {
    private Object[] elements;  // the elements of this MySet
    private int numActive;      // number of active elements
    private static int initialCapacity = 4; // size of the array
    // numActive is the number of elements in the set
    // elements[0 .. numActive – 1] contains the set elements

    /**
     * Constructor for MySet
     */
    public MySet() {
        elements = new Object[initialCapacity];
        numActive = 0;
    }

    /**
     * Determine whether this MySet is empty
     * (i.e., has no elements in it)
     * @return  whether or not this MySet is empty
     */
    public boolean isEmpty() {
        return numActive == 0;
    }
    
    /**
     * Make this MySet empty.  If we want to allow the elements in
     * the array to be garbage collected, then we should null out every
     * spot in the array.
     */
    public void makeEmpty() {
        numActive = 0;
    }
    
    /**
     * Insert an item into this MySet.  If the item is already
     * in this MySet (as defined by .equals() ), then this method call
     * has no effect.
     * @param x  the object to insert into this MySet
     */
    public void insert(Object x) {
        if (x == null) return;
        // Check to see if x is already an element
        for (int i = 0; i < elements.length; i++) {
            if(elements[i] == x)
                return;
        }
        // Check to see if the array is full.
        // If it is full, double the size of the array.
        if(elements.length == numActive+1){
            elements = Arrays.copyOf(elements, elements.length*2);
        }
        for (int i = 0; i <= elements.length; i++) {
            if(elements[i] == null){
                elements[i] = x;
                numActive++;
                break;
            }
        }
    }

    /**
     * Remove items from this MySet that are .equals() to an object.
     * If the item is not .equals() to any object in this MySet,
     * this method has no effect.
     * @param x  the object to remove from this MySet
     */
    public void remove(Object x) {
       if (x == null) return;
       //Check for empty set.
       if(numActive == 0) {
           //Would usually throw exception here,
           //But not necessary for assignment,
           System.out.println("Error - no items to remove!");
           return;
       }
        // try to find the item x in the array
        // if an item is .equals() to x, remove it
        for (int i = 0; i <= numActive; i++) {
            //Using .equals on i
            if (elements[i].equals(x)) {
                //decrease numActive
                numActive--;
                if (i == numActive) {
                    //Element to remove is at the
                    //end of the set, so just make it null.
                    elements[i] = null;
                } else {
                    //Assign a temp object that is
                    //The last element of the set.
                    Object temp = elements[numActive];
                    //Set the element for removal to the
                    //temp element
                    elements[i] = temp;
                    //Set the last element of the set to null.
                    elements[numActive] = null;

                }
                //Break out on removal
                break;
            }
        }

    }
    
    /**
     * Determine whether an item is in this MySet.
     * @param x  an object
     * @return  whether or not an object .equals() to x is in this MySet
     */
    public boolean isPresent(Object x) {
        for (int index = 0; index < numActive; index++) {
            if (x.equals(elements[index])) {
                return true;
            }
        }
        // the object wasn't in the array
        return false;
    }

    /**
     * Print out all of the elements in this MySet.
     * (A sometimes useful debugging method.)
     */
    private void printAll() {
        for (int index = 0; index < numActive; index++) {
            System.out.println(index + " " + elements[index]);
        }
        System.out.println();
    }
    
    /**
     * A test method to test the various method of this class.
     * This is NOT the way one would create tests using JUnit, but the
     * point here is that this test code tries to exercise all possible
     * code paths in the class’s method code.
     */
    public static void test() {
        MySet testSet = new MySet();
        
        if (!testSet.isEmpty()) {
            System.out.println("Something is wrong 1.");
        }
        System.out.println("Inserting Andy ...");
        testSet.insert("Andy");
        if (testSet.isEmpty()) {
            System.out.println("Something is wrong 2.");
        }
        System.out.println("Inserting Belinda ...");
        testSet.insert("Belinda");
        testSet.printAll();
        
        // test isPresent
        if (!testSet.isPresent("Andy")) {
            System.out.println("Something is wrong 3.");
        }
        if (!testSet.isPresent("Belinda")) {
            System.out.println("Something is wrong 4.");
        }
        if (testSet.isPresent("Charlie")) {
            System.out.println("Something is wrong 5.");
        }
        System.out.println("Removing Andy ...");
        testSet.remove("Andy");
        if (testSet.isPresent("Andy")) {
            System.out.println("Something is wrong 6.");
        }
        testSet.printAll();
        
        // test insert -- when Gerald is inserted, the capacity
        // of the array should double
        System.out.println("Inserting Danielle ...");
        testSet.insert("Danielle");
        System.out.println("Inserting Ed ...");
        testSet.insert("Ed");
        System.out.println("Inserting Francine ...");
        testSet.insert("Francine");
        System.out.println("Inserting Gerald ...");
        testSet.insert("Gerald");
        testSet.printAll();
        
        // test remove
        System.out.println("Removing Danielle ...");
        testSet.remove("Danielle");
        testSet.printAll();
        
        // remove the item at the end of the array
        System.out.println("Removing Francine ...");
        testSet.remove("Francine");
        testSet.printAll();
        
        // test makeEmpty
        System.out.println("Making empty ...");
        testSet.makeEmpty();
        if (!testSet.isEmpty()) {
            System.out.println("Something is wrong 8.");
        }
        
        // try removing when there is nothing in the array
        System.out.println("Removing Belinda ...");
        testSet.remove("Belinda");
        testSet.printAll();
    }

    public static void main(String[] args) {
        test();
    }
}
