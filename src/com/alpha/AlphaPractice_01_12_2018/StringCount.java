package com.alpha.AlphaPractice_01_12_2018;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class StringCount extends RecursiveTask<Map<Character, Integer>> {
    private int UNIT_SIZE = 50;
    private String[] values;
    private int startPos, endPos;

    public StringCount(String[] values) {
        this(values, 0, values.length);
    }

    public StringCount(String[] values, int startPos, int endPos) {
        this.values = values;
        this.startPos = startPos;
        this.endPos = endPos;
        System.out.println("startPos " + startPos + " endPos " + endPos);
    }

    protected Map<Character, Integer> compute() {
        final int currentSize = endPos - startPos;
        if (currentSize <= UNIT_SIZE) {
            return computeSum();
        } else {
            int center = currentSize / 2;
            System.out.println("center " + center);
            int leftEnd = startPos + center;
            System.out.println("leftEnd " + leftEnd);
            StringCount leftSum = new StringCount(values, startPos, leftEnd);

            leftSum.fork();
            int rightStart = startPos + center ;
            StringCount rightSum = new StringCount(values, rightStart, endPos);
            Map<Character, Integer> map = rightSum.compute();
            Map<Character, Integer> map1 = leftSum.join();
            for (Character character : map1.keySet()) {
                if (!map.containsKey(character)) map.put(character, 0);
                map.put(character, map1.get(character)+map.get(character));
            }
            return map;
        }
    }

    private Map<Character, Integer> computeSum() {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = startPos; i < endPos; i++) {
            for (int j = 0; j < values[i].length(); j++) {
                Character c = values[i].charAt(j);
                if (!map.containsKey(c)) map.put(c, 0);
                map.put(c, map.get(c)+1);
            }
        }

        return map;
    }

    public static void main(String[] args) {
        String[] stringArray = new String[100];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = "ldlihnvldnfcs;dgnhreieue tr;srdrgy    rfguhy.";
        }
        ForkJoinPool pool = new ForkJoinPool();
        StringCount calculator = new StringCount(stringArray);
        System.out.println(pool.invoke(calculator));
    }
}
