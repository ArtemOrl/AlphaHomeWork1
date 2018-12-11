package com.alpha.AlphaPractice_09_12_2018;

public class StringProducerMain {
    public static void main(String[] args) {
        new StringProducerMain().run();
    }
    public void run(){
//        displayString(this::toString);
        displayString(()->new StringBuilder(this.toString()));
    }
    public void displayString(StringProducer producer){
        System.out.println(producer.produce());
    }
}
