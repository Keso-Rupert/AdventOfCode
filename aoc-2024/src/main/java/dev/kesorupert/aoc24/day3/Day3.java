package dev.kesorupert.aoc24.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 {

    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day3/input.txt"));
        int resultPart1 = 0;
        int resultPart2 = 0;
        Pattern findDigitsRegex = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Pattern doInstructionRegex = Pattern.compile("do\\(\\)");
        Pattern dontInstructionRegex = Pattern.compile("don't\\(\\)");
        boolean multiply = true;


        for (String line : (Iterable<String>) lines::iterator) {
            String[] inputSections = line.split("(?=mul\\(|do\\(\\)|don't\\(\\))");

            for (String input : inputSections) {
                if (doInstructionRegex.matcher(input).find()) {
                    multiply = true;
                } else if (dontInstructionRegex.matcher(input).find()) {
                    multiply = false;
                } else if (multiply) {
                    resultPart2 += findAndMultiply(findDigitsRegex.matcher(input));
                }
            }

            Matcher matcher = findDigitsRegex.matcher(line);
            resultPart1 += findAndMultiply(matcher);

        }

        System.out.println("Part 1: " + resultPart1);
        System.out.println("Part 2: " + resultPart2);
    }

    public static int findAndMultiply(Matcher matcher) {
        int result = 0;

        while (matcher.find()) {
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }
        return result;
    }

}
