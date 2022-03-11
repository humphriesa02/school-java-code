package humphries.hwfive;// The basic entry stored in ProbingHashTable

class HashEntry {
    Hashable element;   // the element
    boolean  isActive;  // false is deleted
    int      frequency; // frequency of entry

    public HashEntry( Hashable e ) {
        this( e, true );
    }

    public HashEntry( Hashable e, boolean i ) {
        element   = e;
        isActive  = i;
        frequency = 1;
    }
}
