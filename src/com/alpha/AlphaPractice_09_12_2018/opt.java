package AlphaPractice_09_12_2018;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class opt {
    public static void main(String[] args) {
        Optional<Integer> opt3 = Optional.of(555);
        StringProducerMain m = new StringProducerMain();
//        opt3.orElseGet(m::);
        Optional<String> out = Optional.of("Hello");
        out.filter((str) -> str.length() > 1).ifPresent(System.out::println);
        Integer count = Stream.of(1, 2, 3).reduce(0, (a, b) -> a + b);
        System.out.println(count);


        Optional<String> opt = Optional.of("Hello");
        opt.filter((str) -> str.length() > 0).ifPresent(System.out::println);
        Optional<String> opt1 = Optional.of("Hello");
        opt1.map(String::length).ifPresent(System.out::println);
    }

    public int getNextAvailablePort() {
        int min = 49152;
        int max = 65535;
        return new Random().nextInt((max - min) + 1) + min;
    }

    public int getPort(Optional<Integer> opt) {
        return opt.orElseGet(this::getNextAvailablePort);
    }
}
