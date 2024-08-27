package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class Day1 {

    static int floor = 0;

    public static void main(String... args) {

        solvePart1();
        solvePart2();

    }

    private static void solvePart2() {
        int position = 0;

        // More modern way of reading the input file, using NIO2
        try {
            String input = Files.readString(Path.of("2015/src/day1/input.txt"));
            int floor = 0;

            for (char character : input.toCharArray()) {
                position++;
                if (character == '(') {
                    floor++;
                } else if (character == ')') {
                    floor--;
                }
                if (floor == -1) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(position);
    }

    private static void solvePart1() {
        List<Character> inputList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/day1/input.txt"))) {
            int cr;
            while ((cr = br.read()) != -1) {
                char character = (char) cr;
                inputList.add(character);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        inputList.forEach( character -> {
            if (character == '(') {
                floor++;
            } else if (character == ')') {
                floor--;
            }
        });

        out.println(floor);
    }
}
