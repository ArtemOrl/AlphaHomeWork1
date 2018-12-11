package com.alpha.AlphaPractice_01_12_2018;

public class EPen implements Runnable {
    @Override
    public void run() {
        System.out.println("Gug");
//        run();
    }

    public static void main(String[] args) {
        new Thread(new EPen()).start();
    }

}
