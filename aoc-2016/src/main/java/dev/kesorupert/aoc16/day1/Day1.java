package dev.kesorupert.aoc16.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 {

    private final static String INPUT = "aoc-2016/src/main/java/dev/kesorupert/aoc16/day1/input.txt";
    static Coords coords = new Coords(0, 0);
    static Character facing = 'N';
    static List<Coords> visitedBlocks = new ArrayList<>();


    public static void main(String... args) {
        List<String> instructions;

        try {
            String input = Files.readString(Path.of(INPUT));
            instructions = Arrays.asList(input.split(", "));

            solve(instructions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve(List<String> instructions) {
        boolean part2Solved = false;

        for (String instruction : instructions) {
            String direction = instruction.substring(0, 1);
            Integer distance = Integer.parseInt(instruction.substring(1));

            // Determine which direction we are facing
            switch (facing) {
                case 'N':
                    facing = direction.equals("R") ? 'E' : 'W';
                    break;
                case 'E':
                    facing = direction.equals("R") ? 'S' : 'N';
                    break;
                case 'S':
                    facing = direction.equals("R") ? 'W' : 'E';
                    break;
                case 'W':
                    facing = direction.equals("R") ? 'N' : 'S';
                    break;
            }

            // Move the distance towards determined direction
            // Reason I have two switch statements is because each direction can occur in two case statements
            for (int i = 0 ; i < distance ; i++) {
                switch(facing) {
                    case 'N':
                        coords = new Coords(coords.x() + 1, coords.y());
                        break;
                    case 'E':
                        coords = new Coords(coords.x(), coords.y() + 1);
                        break;
                    case 'S':
                        coords = new Coords(coords.x() - 1, coords.y());
                        break;
                    case 'W':
                        coords = new Coords(coords.x(), coords.y() - 1);
                        break;
                }
                // For part 2:
                if (part2Solved || !visitedBlocks.contains(coords)) {
                    visitedBlocks.add(coords);
                } else {
                    // We have already been there
                    System.out.println("Part 2:" + (Math.abs(coords.x()) + Math.abs(coords.y())));
                    part2Solved = true;
                }
            }
        }

        // From the wiki: "it is the sum of the absolute values of the differences in both coordinates."
        // So this should be it
        System.out.println("Part 1:" + (Math.abs(coords.x()) + Math.abs(coords.y())));
    }


}

record Coords(int x, int y) {}