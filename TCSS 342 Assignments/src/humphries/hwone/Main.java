package humphries.hwone;

public class Main {
    public static void main(String[] args) {
        MySet mySet = new MySet();
        mySet.add("Hello");
        mySet.add("Hello");
        mySet.add("Hell");
        mySet.add("Hel");
        mySet.add("He");
        mySet.add("H");
        System.out.println(mySet);
        mySet.insert("Yo",3);
        System.out.println(mySet);
        mySet.remove("Hell");
        System.out.println(mySet);
        mySet.remove("Hel");
        mySet.remove("He");
        System.out.println(mySet);
        System.out.println(mySet.size());
        mySet.makeEmpty();
        System.out.println(mySet);
        System.out.println(mySet.isPresent("Hi"));


    }
}
