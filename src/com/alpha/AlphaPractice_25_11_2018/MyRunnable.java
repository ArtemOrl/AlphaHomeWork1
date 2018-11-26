package com.alpha.AlphaPractice_25_11_2018;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRunnable extends Thread{//implements Runnable {

    private static final int REPETITIONS = 10;
    private static final int DELAY = 1000;
    private String greeting;
    public MyRunnable(String aGreeting) {greeting = aGreeting;}
    public void run(){
        try {
            for (int i = 0; i <= REPETITIONS; i++) {
                Date now = new Date();
                System.out.println(now + " " + greeting);
                Thread.sleep(DELAY);

            }
        } catch (InterruptedException e){}
    }
//    public static void main(String[] args) {
//        MyRunnable r1 = new MyRunnable("Hello");
//        MyRunnable r2 = new MyRunnable("Goodbye");
//        Thread t1 = new Thread(r1);
//        Thread t2 = new Thread(r2);
//        t1.start();
//        t2.start();
//        System.out.println("The one");
//    }

    public static void main(String[] args) {
        Runnable r1 = new MyRunnable("Hello");
        Runnable r2 = new MyRunnable("Goodbye");
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.execute(r1);
        pool.execute(r2);
        pool.shutdown();
    }
}
