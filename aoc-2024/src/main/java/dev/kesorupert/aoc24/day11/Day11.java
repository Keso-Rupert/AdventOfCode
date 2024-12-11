package dev.kesorupert.aoc24.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11 {

    public static void main(String[] args) {
        String input = "28591 78 0 3159881 4254 524155 598 1";
//        input = "125 17";

        List<Long> stones = new ArrayList<>();
        for (String in : input.split(" ")) {
            stones.add(Long.parseLong(in));
        }

        long start = System.currentTimeMillis();

        System.out.println("Part 1: " + solve(25, stones));

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time elapsed: " + timeElapsed);

        start = System.currentTimeMillis();

        System.out.println("Part 2: " + solve(75, stones));

        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Time elapsed: " + timeElapsed);

    }

    private static long solve(int blinks, List<Long> stones) {
        // Let's create a Map where we maintain the numbers and how often they occur
        Map<Long, Long> stonesMap = stones.stream().collect(Collectors.toMap(l-> l, l -> 1L));

        for (int i = 0; i < blinks; i++) {
            System.out.println("Blink " + (i+1));
            Map<Long, Long> newStones = new HashMap<>();

            stonesMap.forEach((stone, occurrences) -> {
                blink(stone).forEach(newStone -> newStones.merge(newStone, occurrences, Long::sum));
            });
            stonesMap = newStones;
        }

        return stonesMap.values().stream().mapToLong(Long::longValue).sum();
    }

    private static List<Long> blink(Long stone) {
        String stoneString = String.valueOf(stone);
        int stoneLength = stoneString.length();

        if (stone == 0) {
            return List.of(1L);
        } else if (stoneLength % 2 == 0) {
            Long stoneA = Long.parseLong(stoneString.substring(0, stoneLength / 2));
            Long stoneB = Long.parseLong(stoneString.substring(stoneLength / 2));
            return List.of(stoneA, stoneB);
        } else {
            return List.of(stone * 2024);
        }
    }

}
