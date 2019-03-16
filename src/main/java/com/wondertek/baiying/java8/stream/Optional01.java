package com.wondertek.baiying.java8.stream;

import java.util.Optional;

/**
 * Created by wd on 2019/3/5.
 */
public class Optional01 {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("tom");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));

        optional.ifPresent(s -> {
            System.out.println(s.charAt(0));
        });
    }
}
