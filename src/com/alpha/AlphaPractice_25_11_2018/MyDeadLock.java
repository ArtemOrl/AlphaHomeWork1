package com.alpha.AlphaPractice_25_11_2018;

public class MyDeadLock {

    public static void main(String[] args) {

        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();
        resourceA.resourceB = resourceB;
        resourceB.resourceA = resourceA;
        myThread1.resourceA = resourceA;
        myThread2.resourceB = resourceB;
        myThread1.start();
        myThread2.start();
    }

    static class MyThread1 extends Thread{
        ResourceA resourceA;

        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(resourceA.getI());
        }
    }

    static class MyThread2 extends Thread{
        ResourceB resourceB;

        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(resourceB.getI());
        }
    }

    static class ResourceA {
        ResourceB resourceB;

        public synchronized int getI(){

            return resourceB.returnI();
        }

        public synchronized int returnI(){
            return 1;
        }
    }

    static class ResourceB {
        ResourceA resourceA;

        public synchronized int getI(){
            return resourceA.returnI();
        }

        public synchronized int returnI(){
            return 2;
        }
    }
}
