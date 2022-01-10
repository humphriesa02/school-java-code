package humphries.hwone;

import java.util.Arrays;

public class MySet {
    /**
     * Variable Fields
     */
    public static Object[] set;
    public static int sizeOfCollection = 0;

    /**
     * Constructor with an array size parameter
     * @param sizeOfCollection
     */
    public MySet(int sizeOfCollection){
        this.sizeOfCollection = sizeOfCollection;
        this.set = new Object[sizeOfCollection];
    }

    /**
     * Default constructor
     */
    public MySet(){
        this.set = new Object[sizeOfCollection];
    }

    /**
     * Add function for MySet
     * Increases size variable by 1,
     * Checks if the array is empty,
     * if it is set array becomes a
     * new Object array with one value,
     * which is x.
     * If the array is not empty, it
     * checks for dupes, if it finds one
     * it breaks out, if it doesn't,
     * it creates a new copied array
     * with a size of the new collection value
     * then sets the final, null value to x,
     * then sets 'set' to the temp array.
     * @param x
     */
    public void add(Object x){
        sizeOfCollection++;
        if(set.length > 0){
            for (int i = 0; i < set.length-1; i++) {
                if(set[i].equals(x))
                    return;
            }
            Object[] tempArray = Arrays.copyOf(set, sizeOfCollection);
            tempArray[tempArray.length-1] = x;
            set = tempArray;
        }else{
            set = new Object[1];
            set[0] = x;
        }

    }

    /**
     * Checks if the set is empty
     * by checking it's length
     * @return boolean
     */
    public boolean isEmpty(){
        if(set.length == 0)
            return true;
        else
            return false;
    }

    /** Sets the set to a new
     * array with no elements
     */
    public void makeEmpty(){
        set = new Object[0];
    }

    /**
     * Returns the stored array
     * size variable.
     * @return int sizeOfCollection
     */
    public int size(){
        return sizeOfCollection;
    }

    /**
     * Increments sizeOfCollection.
     * Sets a temp array with the size of
     * 'sizeOfCollection'.
     * Runs through the original set,
     * incrementing two variables i and
     * j. If the i variable is equal to
     * the specified index, the tempArray
     * gets the insert Object value
     * put in that place, and j is incremented.
     * In the case where i doesn't equal index,
     * We essentially copy the elements from set
     * at the end we set 'set' to tempArray.
     * @param insert
     * @param index
     */
    public void insert(Object insert, int index){
        sizeOfCollection++;
        Object[] tempArray = new Object[sizeOfCollection];
        for (int i = 0, j = 0; i <set.length; i++, j++) {
            if(i == index){
                tempArray[j] = insert;
                j++;
            }
            tempArray[j] = set[i];
        }
        set = tempArray;
    }

    /**
     * We start be decrementing sizeOfCollection
     * Assign a temp array with that new size.
     * Loop through the set
     * If we find the object x within set,
     * we skip that number and move on,
     * then we simply copy set into temp array
     * without the found object.
     * @param x
     */
    public void remove(Object x){
        sizeOfCollection--;
        Object[] tempArray = new Object[sizeOfCollection];
        for (int i = 0, k = 0; i < set.length; i++) {
            if(set[i].equals(x)){
                continue;
            }
            tempArray[k++] = set[i];
        }
        set = tempArray;
    }

    /**
     * Checks if x is present in
     * set by using .equals
     * @param x
     * @return
     */
    public boolean isPresent(Object x){
        for (int i = 0; i < set.length; i++){
            if(set[i].equals(x))
                return true;
        }
        return false;
    }

    /**
     * Changed toString to be the Arrays format
     * @return
     */
    @Override
    public String toString() {
        return Arrays.toString(set);
    }

    public void test(){
        isEmpty();
        Object[] newSet = set;
        //makeEmpty(newSet);
    }
}
