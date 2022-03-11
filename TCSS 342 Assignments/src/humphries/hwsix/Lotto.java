package humphries.hwsix;

public class Lotto {

    public static void main(String[] args) {
        summation();
    }
    private static void summation(){
        int[] ticket = {2,4,3,5,6,7};
        int hashed = 0;
        for(int i = 0; i < 6; i ++){
            hashed = ticket[i] * 51^i;
        }
        hashed = hashed % 100;
        System.out.println(hashed);
    }
}
