package com.alpha.AlphaPractice_09_12_2018;

class OldThread {
    public static void main(String[] args) {
        new Thread(new Runnable() { public void run() {
            System.out.println("Hello World!"); }
        }).start(); }
}


public class LambdaThread {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Hello World!")).start();
    }
}
