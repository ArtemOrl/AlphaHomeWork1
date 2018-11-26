package com.alpha.AlphaPractice_25_11_2018;

public class Sing2 extends Thread {
    public void run(){
        System.out.println("Singing");
        throw new RuntimeException("run");
    }

    public static void main(String[] args) {
        Thread sing = new Sing2();
        sing.start();
        throw new RuntimeException("main");
    }
}
