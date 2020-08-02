package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class MyMapDemo {

    public static void main(String[] args) {

        int mapSize = 200_000;
        String keyStr = "k";
        ///////////
        long sum1 = 0;
        Map<String, Integer> hashMap = new HashMap<>(mapSize);
        long begin = System.currentTimeMillis();

        for (int idx = 0; idx < mapSize; idx++) {
            hashMap.put(keyStr + idx, idx);
        }

        for (int idx = 0; idx < mapSize; idx++) {
            sum1 += hashMap.get(keyStr + idx);
        }
        System.out.println("HashMap time:" + (System.currentTimeMillis() - begin));
        ////
        System.out.println("-----");
        long sum2 = 0;
        MyMapInt myMap = new MyMapInt(mapSize);
        begin = System.currentTimeMillis();

        for (int idx = 0; idx < mapSize; idx++) {
            myMap.put(keyStr + idx, idx);
        }

        for (int idx = 0; idx < mapSize; idx++) {
            sum2 += myMap.get(keyStr + idx);
        }
        System.out.println("MyMapInt time:" + (System.currentTimeMillis() - begin));

        System.out.println("sum1:" + sum1 + ", sum2:" + sum2);
    }
}
