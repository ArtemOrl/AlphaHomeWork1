package com.alpha.AlphaPractice_09_12_2018;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Lambda1 {
    public static void main(String[] arg) {
        ArrayList<Person> allPeople = new ArrayList<>();
        allPeople.add(new Person("a", 25));
        allPeople.add(new Person("b", 30));
        allPeople.add(new Person("c", 35));
        long count = 0;
        Iterator<Person> iterator = allPeople.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getAge() < 35) {
                count++;
            }
        }
        System.out.println(count);

        // or Lambda

        ArrayList<Person> allPeople1 = new ArrayList<>();
        allPeople1.add(new Person("a", 25));
        allPeople1.add(new Person("b", 30));
        allPeople1.add(new Person("c", 35));
        long count1 = allPeople1.stream()
                .filter(person -> person.getAge() < 35)
                .count();
        System.out.println(count1);
        List<String> collected = Stream.of("a", "b", "c").collect(toList());

        // or Lambda
        Stream.of("a", "b", "c").collect(toList()).forEach(System.out::println);

        // 2.

        List<String> collected1 = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }

        // or Lambda

        List<String> collected2 = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());

        // 3.
        List<String> beginningWithNumbers = new ArrayList<>();
        for (String value : Arrays.asList("a", "1abc", "abc1")) {
            if (Character.isDigit(value.charAt(0))) {
                beginningWithNumbers.add(value);
            }
        }

        // or Lambda

        List<String> beginningWithNumbers1
                = Stream.of("a", "1abc", "abc1")
                .filter(value -> Character.isDigit(value.charAt(0)))
                .collect(Collectors.toList());

        Stream.of("a", "1abc", "abc1")
                .filter(value -> Character.isDigit(value.charAt(0)))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // 4.

        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());

        // 5. Reduce

        System.out.println(
                Stream.of(1, 2, 3)
                        .reduce(0, (acc, element) -> acc + element)
        );

        // 6. ???

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        List<Integer> stillOrdered = numbers.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());

        System.out.println(stillOrdered);

        Set<Integer> unordered = new HashSet<>(numbers);

        List<Integer> stillUnordered = unordered.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());

        System.out.println(stillUnordered);
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}