package humphries.seminar;

import java.util.Hashtable;

public class Jewels {

    public static void main(String[] args) {
        String jewels = "aA";
        String stones = "aAAbbbb";
        System.out.println(numJewelsInStones(jewels, stones));
        String jewels2 = "z";
        String stones2 = "ZZ";
        System.out.println(numJewelsInStones(jewels2, stones2));
        String jewels3 = "bAZg";
        String stones3 = "aaffsgsgsBbAAZggGGG";
        System.out.println(numJewelsInStones(jewels3, stones3));
    }

    public static int numJewelsInStones(String jewels, String stones){
        int jewelCount = 0;
        Hashtable<Character, Integer> hashtable = new Hashtable();

        for (int i = 0; i < stones.length(); i++){
            hashtable.put(stones.charAt(i), hashtable.getOrDefault(stones.charAt(i), 0) + 1);
        }

        for(int i = 0; i < jewels.length(); i++){
            if(hashtable.containsKey(jewels.charAt(i))){
                jewelCount += hashtable.get(jewels.charAt(i));
            }
        }

        return jewelCount;
    }
}
