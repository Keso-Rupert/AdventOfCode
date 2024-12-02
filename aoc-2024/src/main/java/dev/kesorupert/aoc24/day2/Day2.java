package dev.kesorupert.aoc24.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day2 {


    public static void main(String[] args) throws IOException {
        Stream<String> input = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day2/input.txt"));
        int safeLevelsPart1 = 0;
        int safeLevelsPart2 = 0;

        for (String line : (Iterable<String>) input::iterator) {
            String[] reportDataStrings = line.split(" ");
            List<Integer> reportData = new ArrayList<>();

            // Make them ints since it will save in parseInt calls
            for (int i = 0; i < reportDataStrings.length; i++) {
                reportData.add(Integer.parseInt(reportDataStrings[i]));
            }

            if(checkIfLevelIsSafe(reportData)) safeLevelsPart1++;
            if(checkIfLevelIsSafePart2(reportData)) safeLevelsPart2++;

        }

        System.out.println("Part 1: " + safeLevelsPart1);

    }

    private static boolean checkIfLevelIsSafePart2(List<Integer> reportData) {
        boolean safe = false;

        return safe;
    }

    private static boolean checkIfLevelIsSafe(List<Integer> reportData) {
        boolean safe = false;
        boolean descending = false;
        int j = 0;

        while(true) {
            // First decide whether we are descending or ascending
            if (j == 0) {
                if (reportData.get(j) < reportData.get(j+1)) { // I can get away with this since there is no array of length 1
                    descending = false;
                } else if (reportData.get(j) > reportData.get(j+1)) {
                    descending = true;
                } else {
                    // Level is unsafe
                    break;
                }
            }

            // Check if we haven't reached the end of the data yet
            if (j == reportData.size() - 1) {
                break;
            }

            // Calculate and check difference
            int difference = reportData.get(j) - reportData.get(j+1);
            if (descending) {
                if (difference >= 1 && difference <= 3 ) {
                    safe = true;
                } else {
                    safe = false;
                    break;
                }
            } else {
                if (difference <= -1 && difference >= -3) {
                    safe = true;
                } else {
                    safe = false;
                    break;
                }
            }
            j++;
        }

        return safe;
    }
}
