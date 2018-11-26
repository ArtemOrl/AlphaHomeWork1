package com.alpha.AlphaPractice_25_11_2018;

public class Sing extends Thread {
    public void run(){
        System.out.println("Singing");
    }

    public static void main(String[] args) {
        Thread sing = new Sing();
        Thread newThread = new Thread(sing);
        newThread.start(); //same as run();
        newThread.run();
        newThread.run();
    }
}
