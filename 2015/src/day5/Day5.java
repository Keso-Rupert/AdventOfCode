package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Day5 {

    public static void main(String[] args) throws IOException {

        Supplier<Stream<String>> linesSupplier = () -> {
            try {
                return Files.lines(Path.of("2015/src/day5/input.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

        solvePart1(linesSupplier.get());
        solvePart2(linesSupplier.get());
    }

    public static void solvePart1(Stream<String> lines) {

        long count = lines.filter(Day5::containsThreeVowels).filter(Day5::doesNotContainNaughtyStrings).filter(Day5::containsTwoIdenticalCharactersInARow).count();

        System.out.println(count);
    }

    public static boolean containsThreeVowels(String line) {
        Set<Character> vowels = Set.of('a','e','i','o','u');
        return line.chars().filter(c -> vowels.contains((char) c)).count() >= 3;
    }

    public static boolean doesNotContainNaughtyStrings(String line) {
        Set<String> naughtyStrings = Set.of("ab", "cd", "pq", "xy");
        return naughtyStrings.stream().noneMatch(naughtyString -> line.contains(naughtyString));
    }

    public static boolean containsTwoIdenticalCharactersInARow(String line) {
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == line.charAt(i - 1)) {
                return true;
            }
        }
        return false;
    }

    public static void solvePart2(Stream<String> lines) {
        System.out.println(lines.filter(Day5::containsTwoLetterPairs).filter(Day5::containsRepeatingLetterWithOneLetterInBetween).count());
    }

    public static boolean containsTwoLetterPairs(String line) {
        for (int i = 0; i < line.length() - 1; i++) {
            String twoLetters = line.substring(i, i + 2);

            // Only check if there are more than 2 characters left in the string, to avoid out of bounds errors
            if (line.length() - i >= 2 &&  line.substring(i + 2).contains(twoLetters)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsRepeatingLetterWithOneLetterInBetween(String line) {
        for (int i = 0; i < line.length() - 1; i++) {
            String letterToCheck = line.substring(i, i + 1);

            // Only check if there are more than 3 characters left in the string, to avoid out of bounds errors
            // And we only check the second character from the letter we're currently checking
            if (line.length() - i >= 3 &&  line.substring(i + 2, i + 3).contains(letterToCheck)) {
                return true;
            }
        }
        return false;
    }

}
