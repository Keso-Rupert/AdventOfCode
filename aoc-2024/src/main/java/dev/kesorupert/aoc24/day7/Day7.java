package dev.kesorupert.aoc24.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day7/input.txt")).toList();
        solvePart1(lines);
        solvePart2(lines);

    }

    static void solvePart1(List<String> lines){

        Long totalSum = 0L;

        wordLoop:
        for (String line : lines) {
            Long result = Long.parseLong(line.split(":")[0]);
            Long[] partsOfSum = Arrays.stream(line.split(": ")[1].split(" ")).map(Long::parseLong).toArray(Long[]::new);
            Long calculationResult = 0L;

            // Max value of 4095 since that is the max representation of binary nr of length 12
            for (int i = 0; i < 4095; i++) {
                // Reset calculation result
                calculationResult = 0L;

                // Determine operators based on binary representation of i
                char[] operators = String.format("%12s", Integer.toBinaryString(i)).toCharArray();

                // Perform calculation, make sure to stop at second to last value
                for (int j = 0 ; j < partsOfSum.length - 1 ; j++) {
                    if (calculationResult == 0) calculationResult = partsOfSum[j];

                    if (operators[operators.length - (j+1)] == '1') {
                        calculationResult += partsOfSum[j + 1];
                    } else {
                        calculationResult *= partsOfSum[j + 1];
                    }

                }

                if (calculationResult.equals(result)) {
                    totalSum += result;
                    continue wordLoop;
                }
            }

        }

        System.out.println("Part 1: " + totalSum);

    }

    static void solvePart2(List<String> lines){

        Long totalSum = 0L;

        for (String line : lines) {
            Long result = Long.parseLong(line.split(":")[0]);
            Long[] partsOfSum = Arrays.stream(line.split(": ")[1].split(" ")).map(Long::parseLong).toArray(Long[]::new);

                if (evaluateSum(result, partsOfSum, 0, partsOfSum[0])) {
                    totalSum += result;
                }
        }

        System.out.println("Part 2: " + totalSum);

    }

    static boolean evaluateSum(long result, Long[] partsOfSum, int index, long currentResult) {
        // Check if we have already gone too far
        if (currentResult > result) return false;
        // End condition is when we've reached the end of the array
        if (index == partsOfSum.length - 1) {
            return currentResult == result;
        }
        // Recursion fun starts here
        return evaluateSum(result, partsOfSum, index + 1, currentResult + partsOfSum[index + 1]) ||
                evaluateSum(result, partsOfSum, index + 1, currentResult * partsOfSum[index + 1]) ||
                evaluateSum(result, partsOfSum, index + 1, Long.parseLong(""+currentResult + partsOfSum[index + 1]));
    }

}
