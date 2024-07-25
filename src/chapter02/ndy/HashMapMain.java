package chapter02.ndy;

import java.util.HashMap;

public class HashMapMain {

    record MyRecode(String a){}

    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, MyRecode> map = new HashMap<>();
        Integer key1 = 1000;
        Integer key2 = 2000;

        System.gc();
        map.put(key1, new MyRecode("test a"));
        map.put(key2, new MyRecode("test b"));

        key1 = null;
        System.gc();
        System.out.println();
    }
}
