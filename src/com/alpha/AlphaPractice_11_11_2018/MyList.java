package com.alpha.AlphaPractice_11_11_2018;

import java.util.ArrayList;

/*
№1. Написать свою реализацию оносвязного списка.
№2. Написать функцию reverse для списка из предыдущего задания.
*/

// Односвязный список с reverse

public class MyList<T> {

    MyNode<T> first = null;

    void add(T value){
        MyNode<T> node = new MyNode<T>(value);
        if (first == null) first = node;
        else{
            MyNode<T> dop = first;
            while (dop.next != null){
                dop = dop.next;
            }
            dop.next = node;
        }
    }

    void reverse(){
        MyNode<T> last = first;

        while (first.next != null){
            MyNode<T> b = first.next;
            MyNode<T> c = b.next;
            b.next = last;
            last = b;
            first.next = c;
        }
        first = last;






//        while (current != null) {
//            Node next = current.next;
//            current.next = previous;
//
//            previous = current;
//            current = next;
//        }
//
//        return previous;
    }

    ArrayList<T> toArray(){
        ArrayList<T> arr = new ArrayList<>();
        arr.add(first.value);
        MyNode<T> dop = first;
        while (dop.next != null){
            dop = dop.next;
            arr.add(dop.value);
        }
        return arr;
    }
}
