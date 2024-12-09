package dev.kesorupert.aoc24.day8;

import dev.kesorupert.aoc24.util.Coord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day8 {

    static int rowLength;
    static int columnLength;
    static Map<Character, List<Coord>> frequencyMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] lines = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day8/input.txt")).toArray(String[]::new);
        rowLength = lines.length;
        columnLength = lines[0].length();

        for (int y = 0; y < columnLength; y++) {
            for (int x = 0; x < rowLength; x++) {
                char c = lines[y].charAt(x);
                if (Character.isLetterOrDigit(c)) {
                    if (frequencyMap.containsKey(c)) {
                        frequencyMap.get(c).add(new Coord(x, y));
                    } else {
                        List<Coord> coordList = new ArrayList<>();
                        coordList.add(new Coord(x, y));
                        frequencyMap.put(c, coordList);
                    }
                }
            }
        }

        solvePart1();
        solvePart2();
    }

    static void solvePart1() {
        Set<Coord> antinodeLocations = new HashSet<>();

        // Loop through all found frequencies
        for (Map.Entry<Character, List<Coord>> frequencyCoordinates : frequencyMap.entrySet()) {
            // Loop through all found Coords for given character to compare to second point
            for (Coord frequency1 : frequencyCoordinates.getValue()) {
                // Loop again, to compare with other coordinates for given frequency
                for (Coord frequency2 : frequencyCoordinates.getValue()) {
                    // Make sure we are not looking at the same coordinate
                    if (!frequency1.equals(frequency2)) {

                        // Calculate distance of x and y between points
                        int distanceX = frequency2.x() - frequency1.x();
                        int distanceY = frequency2.y() - frequency1.y();

                        // Calculate antinodes coordianates and add to set.
                        antinodeLocations.add(new Coord(frequency2.x() + distanceX, frequency2.y() + distanceY));
                        antinodeLocations.add(new Coord(frequency1.x() - distanceX, frequency1.y() - distanceY));
                    }
                }
            }
        }

        System.out.println("Part 1: " + antinodeLocations.stream().filter(antinode ->
                // Filter to only keep antinodes within boundaries
                (antinode.x() >= 0 && antinode.y() >= 0 && antinode.x() < rowLength && antinode.y() < columnLength)).count());
    }

    static void solvePart2() {
        Set<Coord> antinodeLocations = new HashSet<>();

        // Loop through all found frequencies
        for (Map.Entry<Character, List<Coord>> frequencyCoordinates : frequencyMap.entrySet()) {
            // Why do I have to do this?
            antinodeLocations.addAll(frequencyCoordinates.getValue());
            // Loop through all found Coords for given character to compare to second point
            for (Coord frequency1 : frequencyCoordinates.getValue()) {
                // Loop again, to compare with other coordinates for given frequency
                for (Coord frequency2 : frequencyCoordinates.getValue()) {
                    // Make sure we are not looking at the same coordinate
                    if (!frequency1.equals(frequency2)) {

                        // Calculate distance of x and y between points
                        int distanceX = frequency2.x() - frequency1.x();
                        int distanceY = frequency2.y() - frequency1.y();

                        // Create a loop to multiply distance each time
                        for (int i = 1; i < rowLength; i++) {
                            // Calculate antinodes coordinates and add to set.
                            Coord antinode1 = new Coord(frequency2.x() + distanceX * i, frequency2.y() + distanceY * i);
                            Coord antinode2 = new Coord(frequency1.x() - distanceX * i, frequency1.y() - distanceY * i);
                            if ((antinode1.x() >= 0 && antinode1.y() >= 0 && antinode1.x() < rowLength && antinode1.y() < columnLength)) antinodeLocations.add(antinode1);
                            if ((antinode2.x() >= 0 && antinode2.y() >= 0 && antinode2.x() < rowLength && antinode2.y() < columnLength)) antinodeLocations.add(antinode2);
                        }
                    }
                }
            }
        }
        System.out.println("Part 2: " + antinodeLocations.size());

    }
}
