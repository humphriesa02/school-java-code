package humphries.hwone;

import java.util.Arrays;

public class MySet<T> {
    /**
     * Variable Fields
     */
    public T[] set;
    public static int sizeOfCollection = 0;

    /**
     * Constructor with an array size parameter
     * @param sizeOfCollection
     */

    public MySet(int sizeOfCollection){
        this.sizeOfCollection = sizeOfCollection;
        this.set = (T[]) new Object[sizeOfCollection];
    }

    /**
     * Default constructor
     */
    public MySet(){
        this.set = (T[]) new Object[sizeOfCollection];
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
    @SuppressWarnings("Unchecked")
    public void add(T x){
        sizeOfCollection++;
        if(set.length > 0){
            for (int i = 0; i < set.length-1; i++) {
                if(set[i].equals(x))
                    return;
            }
            T[] tempArray = Arrays.copyOf(set, sizeOfCollection);
            tempArray[tempArray.length-1] = x;
            set = tempArray;
        }else{
            set = (T[]) new Object[1];
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
        set = (T[]) new Object[0];
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
    @SuppressWarnings("Unchecked")
    public void insert(T insert, int index){
        sizeOfCollection++;
        T[] tempArray = (T[]) new Object[sizeOfCollection];
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
    public void remove(T x){
        sizeOfCollection--;
        T[] tempArray = (T[]) new Object[sizeOfCollection];
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
    public boolean isPresent(T x){
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

    public void test() {
        MySet<String> stringMySet = new MySet<>();
        stringMySet.add("Hello");
        stringMySet.insert("Hi", 0);
        stringMySet.add("Hello");
        stringMySet.add("Hell");
        stringMySet.add("Hel");
        stringMySet.add("He");
        stringMySet.add("H");
        System.out.println("Test set: " + stringMySet);
        stringMySet.remove("Hi");
        System.out.println("Removed set: " + stringMySet);
        System.out.println("Size of set: " + stringMySet.size());
        System.out.println("Check if Hi is present: " + stringMySet.isPresent("Hi"));
        stringMySet.makeEmpty();
        System.out.println("Made empty: " + stringMySet);

    }
}

class Main {
    public static void main(String[] args) {
        MySet mySet = new MySet();
        mySet.test();
    }
}
