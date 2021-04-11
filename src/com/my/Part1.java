package com.my;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part1 {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    private static void task1() {
        final String TASK_1 = "Take all numbers from stream until ‘target’ value is met, then calculate sum.";
        final List<Integer> integers = Arrays.asList(4, 44, 2, 44, 9, 10, 1, 7);
        final List<BigDecimal> bigDecimalList = integers.stream().map(BigDecimal::valueOf).collect(Collectors.toList());
        final BigDecimal target = new BigDecimal(9);

        BigDecimal sum = bigDecimalList.stream()
                .takeWhile(Predicate.isEqual(target))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        System.out.printf("Task#1: %s\nInput list: %s\nTarget number: %s\nSum: %s\n\n", TASK_1, bigDecimalList, target, sum);
    }

    private static void task2() {
        final String TASK_2 = "Given List of Strings, return a List of String according to condition:\n" +
                "each first String should have prefix “pre”, each second String should have prefix “post”.";
        final List<String> stringList1 = Arrays.asList("production", "analysis", "condition");
        final String PREFIX_PRI = "pre";
        final String PREFIX_POST = "post";

        List<String> stringList2 = IntStream
                .range(0, stringList1.size())
                .mapToObj(i -> i % 2 == 1 ? PREFIX_PRI + stringList1.get(i) : PREFIX_POST + stringList1.get(i))
                .collect(Collectors.toList());
        System.out.printf("Task#2: %s\nInput list: %s\nOutputList: %s\n\n", TASK_2, stringList1, stringList2);
    }

    private static void task3() {
        final String TASK_3 = "Given List of Strings, count occurrences of each string and sort by the number of occurrences, return first 3 most popular strings.";
        final List<String> brands = Arrays.asList("audi", "bmw", "chevrolet", "audi", "volkswagen", "mazda", "kia", "volkswagen", "audi", "bmw");

        List<String> brandsLimited = brands.stream()
                //  can be replaced by:
                // .groupingBy(Function.identity(), Collectors.counting()))
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> -o1.getValue()
                        .compareTo(o2.getValue()))
                .limit(3).map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.printf("Task#3: %s\nInput list: %s\nOutputList: %s\n\n", TASK_3, brands, brandsLimited);
    }

}
