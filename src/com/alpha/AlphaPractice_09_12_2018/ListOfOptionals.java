package com.alpha.AlphaPractice_09_12_2018;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ListOfOptionals {
    public static void main(String[] args) {
        List<Optional<String>> optionalList = Arrays.asList( Optional.empty(),
                Optional.of("hello"), Optional.ofNullable(null), Optional.of("world")
        );
        String value = optionalList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(null);
        System.out.println(value); }
}
