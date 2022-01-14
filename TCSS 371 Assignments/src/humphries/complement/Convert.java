package humphries.complement;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Convert {
    // Takes an array of bits to return the corresponding
    // decimal equivalent.
    public static void main(String[] args) {
        char data[] = {'1', '0', '1', '0', '0'};
        System.out.println(convert2sCompToDecimal(data));
        int dataNum = -50;
        System.out.println(Arrays.toString(convertDecimalTo2sComp(dataNum)));
    }
    public static int convert2sCompToDecimal(char[] bits){
        int runningTotal = 0;
        boolean negative = false;

        if(bits[0] == '1')
            negative = true;
        if(negative){
            char[] twos = findTwosComplement(bits);
            int exponent = twos.length;
            for (int i = 0; i < twos.length; i++) {
                exponent--;
                if(twos[i] == '1'){
                    runningTotal += Math.pow(2,exponent);
                }
                else
                    continue;
            }
            return -runningTotal;
        }
        else{
            int exponent = bits.length;
            for (int i = 0; i < bits.length; i++) {
                exponent--;
                if(bits[i] == '1'){
                    System.out.println(runningTotal);
                    runningTotal += Math.pow(2,exponent);

                }
                else
                    continue;
            }
            return runningTotal;
        }

    }

    /**
     * Takes in a char array of binary numbers,
     * Starts from the last value and moves left
     * If it finds a 1, if flips all subsequent values
     * Either from 0 to 1 or 1 to 0.
     * @param binary
     * @return
     */
    static char[] findTwosComplement(char[] binary)
    {
        int n = binary.length;
        // Looking for the first '1' starting at the end
        // and moving left. This is the "shortcut method"
        int i;
        for (i = n-1 ; i >= 0 ; i--)
            if (binary[i] == '1')
                break;

        // In the case where no 1 is found,
        // Put one in the front of a new temp array
        // That is one element larger.
        if (i == -1) {
            char[] tempArray = new char[binary.length + 1];
            for (int j = 0; j < tempArray.length; j++) {
                if(j == 0){
                    tempArray[j] = '1';
                }else
                    tempArray[j] = binary[j];
            }
            //Return the new array
            return tempArray;
        }

        // When we find the first 1,
        // We continue to iterate left
        for (int k = i-1 ; k >= 0; k--)
        {
            //Flipping each value along the way
            // (1's become 0's)
            if (binary[k] == '1'){
                binary[k] = '0';
                //binary[k+1] = '0';
            }
            // (0's become 1's)
            else if(binary[k] == '0'){
            binary[k] = '1';
            //binary[k+1] = '1';
            }
        }
        // return the changed array
        return binary;
    }
    public static char[] decimalToBinary(int decimal){
        decimal = Math.abs(decimal);
        int binary[] = new int[40];
        int index = 0;
        while(decimal > 0){
            binary[index++] = decimal%2;
            decimal = decimal/2;
        }
        char newArray[] = new char[index];
        for (int i = newArray.length-1, j = 0; i >= 0; i--, j++) {
            newArray[j] = Character.forDigit(binary[i],10);
        }
        return newArray;
    }

    /**
     * Takes in a decimal and calls the decimalToBinary
     * method on it, assigning it to a new binary char[].
     * If the original is a negative, we return
     * findTwosComplement on the char[]
     * or else we just return the char[]
     * @param decimal
     * @return
     */
   public static char[] convertDecimalTo2sComp(int decimal){
        char binary[] = decimalToBinary(decimal);
        if(decimal < 0){
            System.out.println(Arrays.toString(binary));
            return findTwosComplement(binary);
        }else{
            return binary;
        }
    }
}
