package com.alpha.AlphaPractice_01_12_2018;

import java.util.Arrays;
import java.util.Objects;

public final class Cat {
    private Cat[] kitten;
    private final String name;
    private final int age;
    private String color;

    public Cat(Cat[] kitten, String name, int age, String color) {
        this.kitten = kitten;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Cat[] getKitten() {
        return kitten.clone();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        Cat cat = (Cat) o;
        return age == cat.age &&
                Arrays.equals(kitten, cat.kitten) &&
                name.equals(cat.name) &&
                color.equals(cat.color);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, age, color);
        result = 31 * result + Arrays.hashCode(kitten);
        return result;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "kitten=" + Arrays.toString(kitten) +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
