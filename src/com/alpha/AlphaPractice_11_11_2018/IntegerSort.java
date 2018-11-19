package com.alpha.AlphaPractice_11_11_2018;

import java.util.Arrays;

/*
№3. Дан массив x[1]..x[n] целых чисел. Не используя других массивов, переставьте
элементы массива в обратном порядке.
 */
public class IntegerSort {

    static Integer[] a = {1, 2, 5, 4 ,3, 90, 76 };

    static void sort(Integer[] a){

        int n = a.length;
        int count = 0;
        for (int i = n-1; i >=(a.length-1)/2; i--) {

            a[i] = a[i] ^ a[count];
            a[count] = a[i] ^ a[count];
            a[i] = a[i] ^ a[count];


//            c = c ^ d;
//            d = c ^ d;
//            c = c ^ d;
            count++;

        }
    }

    public static void main(String[] args) {
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
