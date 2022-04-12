/**
 * Alex Humphries
 * 4/5/2022
 * TCSS 360
 * Minesweeper
 */
import java.util.Objects;
import java.util.Scanner;

/**
 * Takes in an input of two numbers which represents
 * the fields dimensions, then the fields themselves.
 * Turns it into a 2d array with null boundaries
 * Ex
 * 4 4
 * null null null null
 * null*...null
 * null....null
 * null..**null
 * null.*..null
 * null null null null
 * then checks each . to see how many bombs are around it.
 * Ex
 * *100
 * 2210
 * 1*10
 * 1110
 */
public class GameMain {
    /**
     * Driver code
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        int counter = 0;
        while (scr.hasNextLine()) {
                counter += 1;
                String[][] minefield = inputToField(scr);
                assert minefield != null;
                int[][] zeroMatrix = constructMatrix(minefield);
                printFullMatrix(zeroMatrix,minefield, counter);
        }
        scr.close();
    }

    /**
     * Parses input and turns it into a field grid
     *
     * @param scanner
     */
    private static String[][] inputToField(final Scanner scanner) {
        int rows = scanner.nextInt() + 2;
        int columns = scanner.nextInt() + 2;
        if (rows == 0 || columns == 0)
            return null;
        scanner.nextLine();
        int i = 1;
        String tempString;
        String[][] minefield = new String[rows][columns];
        while (i < rows - 1) {
            tempString = scanner.nextLine();
            for (int j = 1; j < columns - 1; j++) {
                minefield[i][j] = String.valueOf(tempString.charAt(j-1));
            }
            i++;
        }
        return minefield;
    }

    /**
     * Uses grid to find bomb locations
     * Makes a new grid with containing bomb counts
     * around each cell.
     * @param field
     * @return
     */
    private static int[][] constructMatrix(final String[][] field) {
        int[][] zeroMatrix = new int[field.length][field[0].length];
        for (int i = 1; i < field.length-1; i++) {
            for (int j = 1; j < field[0].length-1; j++) {
                if (Objects.equals(field[i][j], "*")) {
                    if(field[i][j + 1] != null){
                        zeroMatrix[i][j+1] = zeroMatrix[i][j+1] + 1;
                    }
                    if(field[i][j - 1] != null){
                        zeroMatrix[i][j-1] = zeroMatrix[i][j-1] + 1;
                    }
                    if(field[i-1][j] != null){
                        zeroMatrix[i-1][j] = zeroMatrix[i-1][j] + 1;
                    }
                    if(field[i+1][j] != null){
                        zeroMatrix[i+1][j] = zeroMatrix[i+1][j] + 1;
                    }
                    if(field[i+1][j+1] != null){
                        zeroMatrix[i+1][j+1] = zeroMatrix[i+1][j+1] + 1;
                    }
                    if(field[i-1][j-1] != null){
                        zeroMatrix[i-1][j-1] = zeroMatrix[i-1][j-1] + 1;
                    }
                    if(field[i+1][j-1] != null){
                        zeroMatrix[i+1][j-1] = zeroMatrix[i+1][j-1] + 1;
                    }
                    if(field[i-1][j+1] != null){
                        zeroMatrix[i-1][j+1] = zeroMatrix[i-1][j+1] + 1;
                    }
                }
            }
        }
        return zeroMatrix;
    }

    /**
     * Loops through the minefield
     * starting at the non null locations
     * If it's a bomb, print from the minefield,
     * else print from the number matrix
     * @param matrix
     * @param minefield
     * @param count
     */
    private static void printFullMatrix(final int[][] matrix, String[][] minefield, int count){
        System.out.println("Field #" + count + ":");
        for(int i = 1; i < minefield.length-1; i++){
            for(int j = 1; j < minefield[0].length-1; j++){
                if (Objects.equals(minefield[i][j], "*")) {
                    System.out.print(minefield[i][j]);
                }else
                    System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
