package com.alpha.AlphaPractice_11_11_2018;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<Integer>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        System.out.println(list.toArray());

        list.reverse();
        System.out.println(list.toArray());
    }
}
