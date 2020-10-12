package com.yicj.fun.hello;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class HelloTest {

    @Test
    public void test1(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //(Comparator<T>) ReverseComparator.REVERSE_ORDER;
        Collections.sort(names, Comparator.reverseOrder());
    }

    @Test
    public void test2(){
        Stream.of("foo", "bar1").map(String::length).forEach(System.out::println); ;
    }
}
