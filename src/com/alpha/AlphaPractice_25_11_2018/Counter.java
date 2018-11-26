package com.alpha.AlphaPractice_25_11_2018;

public class Counter implements Runnable {
    Integer count = 0;
    Object vasya = new Object();

    private void add(){count++;} // synchronized
    public void run(){
//        try{
        synchronized (vasya){ // if count то объект иммутабельный и не пойдет, can be this
            for (int i = 0; i < 1000; i++) {
                 add();
            }
        }
//                Thread.sleep(1);
//            }
//        }catch (InterruptedException e){e.printStackTrace();}
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t = new Thread(counter);
        Thread t2 = new Thread(counter);
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(counter.count);
    }
}
