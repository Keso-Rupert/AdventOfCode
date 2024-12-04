package dev.kesorupert.aoc24.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Day1 {

    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day1/input.txt"));
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        // split inputline and add to each list
        input.forEach(line -> {
            String[] locationIds = line.split(" {3}");
            firstList.add(Integer.parseInt(locationIds[0]));
            secondList.add(Integer.parseInt(locationIds[1]));
        });

        // sort list
        Collections.sort(firstList);
        Collections.sort(secondList);

        calculateDistance(firstList, secondList);
        calculateSimilarityScore(firstList, secondList);
        calculateSimilarityScoreEasyWay(firstList, secondList);

    }

    public static void calculateDistance(List<Integer> firstList, List<Integer> secondList) {
        int distance = 0;
        // iterate through and calculate distance for each number
        for (int i = 0; i < firstList.size() ; i++) {
            distance += (Math.abs(firstList.get(i) - secondList.get(i)));
        }

        System.out.println("Part 1: " + distance);
    }

    public static void calculateSimilarityScoreEasyWay(List<Integer> firstList, List<Integer> secondList) {
        Map<Integer, Integer> locationIdAndOccurrences = new HashMap<>();
        int score = 0;

        long start = System.currentTimeMillis();

        for (int i = 0; i < firstList.size(); i++) {
            int firstListNumber = firstList.get(i);

            Integer occurrences = (int) secondList.stream().filter(nr -> nr == firstListNumber).count();

            locationIdAndOccurrences.put(firstListNumber, occurrences);

        }

        for (Map.Entry<Integer, Integer> entry : locationIdAndOccurrences.entrySet()) {
            score += entry.getKey() * entry.getValue();
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Part 2 alternative: " + score);
        System.out.println("Time elapsed: " + timeElapsed);
    }

    public static void calculateSimilarityScore(List<Integer> firstList, List<Integer> secondList) {
        Map<Integer, Integer> locationIdAndOccurrences = new HashMap<>();
        int score = 0;

        long start = System.currentTimeMillis();

        for (int i = 0; i < firstList.size(); i++) {
            int firstListNumber = firstList.get(i);
            locationIdAndOccurrences.putIfAbsent(firstListNumber, 0);
            int secondListPosition = 0;

            while(true) {
                // If nr in first list is same as number in secondlist, update map and go to next position in second list
                if (firstListNumber == secondList.get(secondListPosition)) {
                    locationIdAndOccurrences.put(firstListNumber, (locationIdAndOccurrences.get(firstListNumber) + 1));
                    secondListPosition++;
                } else if (firstListNumber > secondList.get(secondListPosition)) {
                    // if the first number is bigger than the second number, we go to next position in second list
                    secondListPosition++;
                } else {
                    // if first number is smaller, it doesn't occur in the second list, or we've moved passed the values
                    // to count, and we can proceed in outer loop
                    break;
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : locationIdAndOccurrences.entrySet()) {
            score += entry.getKey() * entry.getValue();
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Part 2: " + score);
        System.out.println("Time elapsed: " + timeElapsed);

    }


}