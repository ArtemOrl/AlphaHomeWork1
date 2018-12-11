package com.alpha.AlphaPractice_01_12_2018;

import java.util.Arrays;

public class CatMain {
    public static void main(String[] args) {
        Cat kitten = new Cat(null, "Vaska", 1, "red");
        Cat kitten1 = new Cat(null, "Petka", 1, "blue");
        Cat kitten2 = new Cat(null, "Murka", 1, "black");
        Cat[] kittens = new Cat[3];
        kittens[0] = kitten;
        kittens[1] = kitten1;
        kittens[2] =kitten2;

        Cat cat = new Cat(kittens, "Murka", 5, "white");
        System.out.println(Arrays.toString(cat.getKitten()) + " " + cat.getName() + " "
        + cat.getAge() + " " + cat.getColor());
    }
}
