package com.alpha.AlphaPractice_11_11_2018;


/*
№9. В один проход в последовательности целых чисел найдите минимальное число
и количество его повторений.
Примеры.
Вход: -2 -1; выход: -2; 1.
Вход: 3 10 3; выход: 3 2.
*/

import java.util.Arrays;

public class Sequence_9 {
    static int[] sequence = {5, 4, 2, 10, 23, 12, 2, 2, 98};

    static int[] getMinRepeat(int[] array){
        int min = Integer.MAX_VALUE;
        int repeatCount = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i]==min){
                min = array[i];
                repeatCount++;
            }
            else if (array[i]<min){
                min = array[i];
                repeatCount = 1;
            }
        }
        int[] result = {min, repeatCount};
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getMinRepeat(sequence)));
    }
}
