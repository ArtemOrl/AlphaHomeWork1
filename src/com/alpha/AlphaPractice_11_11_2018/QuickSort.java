package com.alpha.AlphaPractice_11_11_2018;

import java.util.ArrayList;
import java.util.Arrays;

/*
№5. Написать реализация quicksort
 */
public class QuickSort {
    static Integer[] a = {1, 2, 5, 4 ,3, 90, 76 };

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(a)));
    }

    static Integer[] sort(Integer[] a){
        if (a.length <=1) return a;
        int a0 = a[0];
        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> more = new ArrayList<>();
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a0) less.add(a[i]);
            else more.add(a[i]);
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.addAll(Arrays.asList(sort(less.toArray(new Integer[0]))));
        result.add(a0);
        result.addAll(Arrays.asList(sort(more.toArray(new Integer[0]))));
        return result.toArray(new Integer[0]);
    }

}
