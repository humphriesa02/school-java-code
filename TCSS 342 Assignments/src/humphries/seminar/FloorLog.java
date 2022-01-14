package humphries.seminar;

public class FloorLog {
    public static int count = 0;
    public static void main(String[] args) {
        System.out.println(floorToLog(2000));
    }
    public static int floorToLog(int n){
        if(n >= 2){
            floorToLog(n/2);
            count++;
            /*Math.floor(count);*/
        }
        return count;
    }
}
