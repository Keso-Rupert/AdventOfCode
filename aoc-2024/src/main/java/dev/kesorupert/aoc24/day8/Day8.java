package dev.kesorupert.aoc24.day8;

import dev.kesorupert.aoc24.util.Coord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day8 {

    static int rowLength;
    static int columnLength;
    static  Map<Character, List<Coord>> frequencyMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] lines = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day8/input.txt")).toArray(String[]::new);
        rowLength = lines.length;
        columnLength = lines[0].length();
        char[][] antennaGrid = new char[rowLength][columnLength];

        for (int y = 0 ; y < columnLength; y++) {
            for (int x = 0 ; x < rowLength ; x++) {
                char c = lines[y].charAt(x);
                antennaGrid[y][x] = c;
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

        solvePart1(antennaGrid);
//        solvePart2(antennaGrid);
    }

    static void solvePart1(char[][] antennaGrid) {
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

    static void solvePart2(char[][] antennaGrid) {

    }
}
