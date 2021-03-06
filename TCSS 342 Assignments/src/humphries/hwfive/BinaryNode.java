package humphries.hwfive;// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// of package DataStructures

class BinaryNode {
    // Constructors
    BinaryNode( Comparable theElement ) {
        this( theElement, null, null );
    }

    BinaryNode( Comparable theElement, BinaryNode lt, BinaryNode rt ) {
        element  = theElement;
        left     = lt;
        right    = rt;
        frequency= 1;
    }

    // Friendly data; accessible by other package routines
    Comparable element;      // The data in the node
    BinaryNode left;         // Left child
    BinaryNode right;        // Right child
    int        frequency;    // frequency of node
}
