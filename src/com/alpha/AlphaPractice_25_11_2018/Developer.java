package com.alpha.AlphaPractice_25_11_2018;

public class Developer {
    static class ScreenDesigner extends Thread{
        public void run(){
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        }
    }
    ScreenDesigner design;

    public Developer(ScreenDesigner design) {
        this.design = design;
    }

    public void code(){
        try {
            System.out.println("Waiting for design to complete");
            design.join();
            System.out.println("Coding phase start");
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScreenDesigner design = new ScreenDesigner();
        design.start();
        Developer dev = new Developer(design);
        //design.join();
        dev.code();

    }
}
