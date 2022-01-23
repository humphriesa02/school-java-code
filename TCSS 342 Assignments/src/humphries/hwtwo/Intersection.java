package humphries.hwtwo;

public class Intersection {
    public static double logTotal=0;
    public static double expTotal = 0;
    public static void main(String[] args) {
        checker(6000);
    }


    public static void checker(int n){
        boolean hasCrossed = false;
        int crossedIndex = 0;
        for (double i = 2; i <= n; i++) {
            logTotal = 1000* i *Math.log10(i);
            expTotal = Math.pow(i,2);
            System.out.println(expTotal);
            System.out.println(logTotal);
            if(expTotal > logTotal){
                hasCrossed = true;
                crossedIndex = (int) i;
                break;
            }
        }
        System.out.println();
        System.out.println(hasCrossed);
        System.out.println(crossedIndex);
    }

}
