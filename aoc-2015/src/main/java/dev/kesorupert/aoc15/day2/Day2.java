package dev.kesorupert.aoc15.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.min;

public class Day2 {

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        AtomicInteger totalWrappingPaper = new AtomicInteger(); // Oof not nice that this needs to be atomic...

        try {
            Stream<String> inputStream = Files.lines(Path.of("aoc-2015/src/main/java/dev/kesorupert/aoc15/day2/input.txt"));
            inputStream.forEach(line -> {

                List<Integer> dimensions = Arrays.stream(line.split("x")).map(Integer::parseInt).collect(Collectors.toList());
                int lw = dimensions.get(0) * dimensions.get(1);
                int wh = dimensions.get(1) * dimensions.get(2);
                int lh = dimensions.get(0) * dimensions.get(2);

                int neededWrappingPaper = (2 * lw) + ( 2 * wh) + (2 * lh) + (min(Arrays.asList(lw, wh, lh)));

                totalWrappingPaper.addAndGet(neededWrappingPaper);

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(totalWrappingPaper.get());

    }

    private static void solvePart2() {
        AtomicInteger totalRibbonLength = new AtomicInteger();

        try {
            Stream<String> inputStream = Files.lines(Path.of("aoc-2015/src/main/java/dev/kesorupert/aoc15/day2/input.txt"));
            inputStream.forEach(line -> {

                List<Integer> dimensions = Arrays.stream(line.split("x")).map(Integer::parseInt).collect(Collectors.toList());
                int ribbonLenth = dimensions.stream().sorted().limit(2).reduce(0, (subtotal, element) -> subtotal + element * 2);
                int bowLength = dimensions.stream().reduce(1, (subtotal, element) -> subtotal * element);

                totalRibbonLength.addAndGet(ribbonLenth + bowLength);

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(totalRibbonLength.get());

    }


}
