package com.wondertek.baiying.java8.stream;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by wd on 2019/3/5.
 */
public class Optional02 {
    static class Outer {
        Nested nested = new Nested();

        public Nested getNested() {
            return nested;
        }
    }

    static class Nested {
        Inner inner = new Inner();

        public Inner getInner() {
            return inner;
        }
    }

    static class Inner {
        String foo = "boo";

        public String getFoo() {
            return foo;
        }
    }

    public static <T> Optional<T> resolve(Supplier<T> resolve) {
        try {
            T result = resolve.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public static void test01() {
        Optional.of(new Outer()).flatMap(outer -> Optional.ofNullable(outer.nested))
                                .flatMap(nested -> Optional.ofNullable(nested.inner))
                                .flatMap(inner -> Optional.ofNullable(inner.foo))
                                .ifPresent(System.out::println);
    }

    public static void test02() {
        Optional.of(new Outer()).map(Outer::getNested)
                                .map(Nested::getInner)
                                .map(Inner::getFoo)
                                .ifPresent(System.out::println);
    }

    public static void test03() {
        Outer outer = new Outer();
        resolve(()->outer.getNested().getInner().getFoo()).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

}
