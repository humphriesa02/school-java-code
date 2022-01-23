/**
 * Alex Humphries
 * 1/17/2022
 * TCSS 342
 */

package humphries.hwtwo;

public class Binary {
    public static void main(String[] args) {
        System.out.println(binaryLength(65));
    }
    public static int binaryLength(int n){
        if(n <= 1) //Line 1
            return 1; //Line 2
        else //Line 3
            return 1 + binaryLength(n/2); //Line 4
    }
}
