package com.alpha.AlphaPractice_09_12_2018;

import java.util.Optional;

public class OptionalReferencePath {
    public static void main(String[] args) {new OptionalReferencePath().test();} public void test() {
        A a = new A();
        String value = a.getB().flatMap(B::getC).flatMap(C::getValue).orElse("Default"); System.out.println(value);
    }
    class C {
        private String value = "Hello";
        public Optional<String> getValue() { return Optional.ofNullable(value);} }
    class B {
        private C c = new C();
        public Optional<C> getC() { return Optional.ofNullable(c);}
    }
    class A {
        private B b = new B();
        public Optional<B> getB() {return Optional.ofNullable(b);} }
}
