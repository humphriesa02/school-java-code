package humphries.hwfour;

public class BinaryTreeNodes {
    //Write a recursive method that computes the number of nodes in a binary tree that
    //have exactly one child
    public class BinaryNode {
        Object element;
        BinaryNode left;
        BinaryNode right;
    }

    public int numberOfOneChildNodes(BinaryNode tree) {
        if (tree == null)
            return 0;
        else {
            int sum = 0;
            sum = numberOfOneChildNodes(tree.left) +
                    numberOfOneChildNodes(tree.right);
            if ((tree.left != null) && (tree.right == null)) {
                sum += 1;
            }else if((tree.left == null) && (tree.right != null)){
                sum += 1;
            }
            return sum;
        }
    }
}
