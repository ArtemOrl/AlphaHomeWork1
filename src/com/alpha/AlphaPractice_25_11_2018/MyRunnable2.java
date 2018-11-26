package com.alpha.AlphaPractice_25_11_2018;

public class MyRunnable2 implements Runnable {
//    @Override
//    public void run() {
//        try{
////        if (!Thread.interrupted()){
//            System.out.println(1);
//            Thread.sleep((1000));
//            System.out.println(2);
//        } catch (InterruptedException e){
//            System.out.println(3);
//        }
//        System.out.println(4);
//    }
    @Override
    public void run() {
        int i = 0;

        while (!Thread.interrupted()) {
            try {
            System.out.println(++i);
            Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("error");}
                Thread.currentThread().interrupt();
        }

        System.out.println("end");
    }
    //} catch (InterruptedException e) {System.out.println("error");}
//    public static void main(String[] args) {
//        Thread t = new Thread(new MyRunnable2());
//        t.start();
//        t.interrupt();
//    }
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(new MyRunnable2());
        t.start();
        Thread.sleep(100);
        t.interrupt();
    }
}
