import java.util.Random;

public class TestHashTable {
    public static void main(String[] args) {

        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(11);
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(rand.nextInt(1000), rand.nextInt(1000));
            table.put(key, new Student("S" + i));
        }

        table.printBuckets();
    }
}