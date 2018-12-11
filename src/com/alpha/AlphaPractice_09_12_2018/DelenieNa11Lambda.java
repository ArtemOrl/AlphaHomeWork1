package com.alpha.AlphaPractice_09_12_2018;

import java.util.stream.IntStream;

public class DelenieNa11Lambda {
    public static void main(String[] args) {

        IntStream.range(12, 1000)
                .filter(value -> searchResult(value)==value%11)
                .forEach(System.out::println);

    }

    public static int searchResult(int i) {

        int resultnumber = 0;
        char[] numbers = Integer.toString(i).toCharArray();

        for (int j = 0; j < numbers.length; j++) {

            int number = Integer.parseInt(Character.toString(numbers[j]));
            resultnumber += number * number;
        }
        return resultnumber;
    }

}
