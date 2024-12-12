package dev.kesorupert.aoc24.day10;

import dev.kesorupert.aoc24.util.Coord;
import dev.kesorupert.aoc24.util.Direction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static dev.kesorupert.aoc24.util.GridUtil.checkGridBoundaries;
import static dev.kesorupert.aoc24.util.GridUtil.getNextPosition;


public class Day10 {
    static int rowLength;
    static int columnLength;

    public static void main(String[] args) throws IOException {
        String[] lines = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day10/input.txt")).toArray(String[]::new);
        rowLength = lines.length;
        columnLength = lines[0].length();
        int[][] map = new int[rowLength][columnLength];

        for (int y = 0 ; y < columnLength; y++) {
            for (int x = 0 ; x < rowLength ; x++) {
                map[y][x] = Character.getNumericValue(lines[y].charAt(x));
            }
        }

        solvePart1(map);
        solvePart2(map);
    }

    private static void solvePart1(int[][] map) {
        int totalScore = 0;
        int totalRating = 0;

        for (int y = 0 ; y < columnLength; y++) {
            for (int x = 0 ; x < rowLength ; x++) {
                if (map[y][x] == 0) {
                    totalScore += getScore(new Coord(x, y), 0, map, new HashSet<>());
                    totalRating += getRating(new Coord(x,y), 0, map);
                }
            }
        }

        System.out.println("Part 1: " + totalScore);
        System.out.println("Part 2: " + totalRating);

    }

    private static void solvePart2(int[][] map) {
    }

    static int getScore(Coord currentCoord, int currentNr, int[][] map, Set<Coord> visitedTrails){
        int score = 0;

        // Add current coord to visited coords to make sure we don't revisit the same trail
        visitedTrails.add(currentCoord);

        // Check if we've reached a trail end
        if (currentNr == 9) {
            return 1;
        }
        // Check 4 directions
        for (Direction direction : Direction.values()) {
            Coord nextPosition = getNextPosition(direction, currentCoord);
            // Check if the next position is within the grid, and if it is, if the next number is the current nr + 1
            if (checkGridBoundaries(map, nextPosition) &&  map[nextPosition.y()][nextPosition.x()] == currentNr + 1 && !visitedTrails.contains(nextPosition)) {
                // we will keep traversing the trails
                score += getScore(nextPosition, map[nextPosition.y()][nextPosition.x()], map, visitedTrails);
            }
        }
        return score;
    }

    static int getRating(Coord currentCoord, int currentNr, int[][] map){
        int score = 0;

        // Add current coord to visited coords to make sure we don't revisit the same trail
//        visitedTrails.add(currentCoord);

        // Check if we've reached a trail end
        if (currentNr == 9) {
            return 1;
        }
        // Check 4 directions
        for (Direction direction : Direction.values()) {
            Coord nextPosition = getNextPosition(direction, currentCoord);
            // Check if the next position is within the grid, and if it is, if the next number is the current nr + 1
            if (checkGridBoundaries(map, nextPosition) &&  map[nextPosition.y()][nextPosition.x()] == currentNr + 1) {
                // we will keep traversing the trails
                score += getRating(nextPosition, map[nextPosition.y()][nextPosition.x()], map);
            }
        }
        return score;
    }

}
