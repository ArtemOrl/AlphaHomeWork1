package com.alpha.AlphaPractice_01_12_2018;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RainBow_6 {
}

//class Rainbow {
//    Lock myLock = new ReentrantLock();
//    static List<String> colors = new ArrayList<>();
//
//    public void addColor(String newColor) {
//        myLock.lock();
//        try {
//            colors.add(newColor);
//        } finally {
//            myLock.unlock();
//        }
//    }
//}

class Rainbow {
    private final ReadWriteLock myLock = new ReentrantReadWriteLock();
    private static int pos;
    static Map<Integer, String> colors = new HashMap<>();

    public void addColor(String newColor) {
        myLock.writeLock().lock();
        try {
            colors.put(new Integer(++pos), newColor);
        } finally {
            myLock.writeLock().unlock();
        }
    }

    public void display() {
        myLock.readLock().lock();
        try {
            for (String s : colors.values()) {
                System.out.println(s);
            }
        } finally {
            myLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        Rainbow rainbow = new Rainbow();
        rainbow.addColor("Red");
        rainbow.addColor("Blue");
        rainbow.display();
    }
}