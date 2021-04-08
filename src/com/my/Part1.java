package com.my;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {

    private static int counter = 0;

    public static void main(String[] args) {
        String task1 = "Take all numbers from stream until ‘target’ value is met, then calculate sum.";
        List<Integer> integers = Arrays.asList(4, 44, 2, 44, 9, 10, 1, 7);
        List<BigDecimal> bigDecimalList = integers.stream().map(BigDecimal::valueOf).collect(Collectors.toList());
        BigDecimal target = new BigDecimal(9);

        BigDecimal sum = bigDecimalList.stream().limit(bigDecimalList.indexOf(target)).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        System.out.printf("Task#1: %s\nInput list: %s\nTarget number: %s\nSum: %s\n\n", task1, bigDecimalList, target, sum);

        String task2 = "Given List of Strings, return a List of String according to condition:\n" +
                        "each first String should have prefix “pre”, each second String should have prefix “post”.";
        List<String> stringList1 = Arrays.asList("production", "analysis", "condition");
        List<String> stringList2 = stringList1.stream().map(s -> {
            counter++;
            return counter % 2 == 1 ? "pre" + s : "post" + s;
        }).collect(Collectors.toList());
        System.out.printf("Task#2: %s\nInput list: %s\nOutputList: %s\n\n", task2, stringList1, stringList2);

        String task3 = "Given List of Strings, count occurrences of each string and sort by the number of occurrences, return first 3 most popular strings.";
        List<String> brands = Arrays.asList("audi", "bmw", "chevrolet", "audi", "volkswagen", "mazda", "kia", "volkswagen", "audi", "bmw");

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
        System.out.printf("Task#3: %s\nInput list: %s\nOutputList: %s\n\n", task3, brands, brandsLimited);



    }
}
