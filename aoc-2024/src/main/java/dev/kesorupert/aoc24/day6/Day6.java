package dev.kesorupert.aoc24.day6;

import dev.kesorupert.aoc24.util.Coord;
import dev.kesorupert.aoc24.util.Direction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.kesorupert.aoc24.util.GridUtil.getNextPosition;

public class Day6 {

    static int rowLength = 0;
    static int columnLength = 0;
    static Coord startingPosition;
    static List<Coord> obstacles = new ArrayList<Coord>();
    static Set<Coord> coordSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String[] lines = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day6/input.txt")).toArray(String[]::new);
        rowLength = lines.length;
        columnLength = lines[0].length();
        char[][] areaMap = new char[rowLength][columnLength];

        for (int y = 0 ; y < columnLength; y++) {
            for (int x = 0 ; x < rowLength ; x++) {
                areaMap[y][x] = lines[y].charAt(x);
                if (areaMap[y][x] == '^') startingPosition = new Coord(x, y);
                if (areaMap[y][x] == '#') obstacles.add(new Coord(x, y));
            }
        }

        solvePart1(areaMap);
        solvePart2(areaMap);
    }

    static void solvePart1(char[][] areaMap){
        Coord currentLocation = startingPosition;
        Direction currentDirection = Direction.NORTH;

        while(true) {
            // Check next position
            Coord nextPosition = getNextPosition(currentDirection, currentLocation);

            // Add the current position to the set
            coordSet.add(currentLocation);

            // Check if we will leave the boundaries of the grid
            if (!(nextPosition.x() >= 0 && nextPosition.y() >= 0 && nextPosition.x() < rowLength && nextPosition.y() < columnLength)) {
                break;
            }

            // Check the next block if we have to turn and decide direction, then alter nextPosition
            if (areaMap[nextPosition.y()][nextPosition.x()] == '#') {
                currentDirection = Direction.fromValue((currentDirection.direction % 4) + 1);
                nextPosition = getNextPosition(currentDirection, currentLocation);
            }

            // Take a step
            currentLocation = nextPosition;
        }

        System.out.println("Part 1: " + coordSet.size());

    }

    static void solvePart2(char[][] areaMap){
        int nrOfObstacles = 0;
        // We can test all traversed coordinates instead of all coordinates in the grid to save time
        for (Coord coord : coordSet) {
            Coord currentLocation = startingPosition;
            Direction currentDirection = Direction.NORTH;
            Set<CoordDirection> coordDirectionSet = new HashSet<>();
            coordDirectionSet.add(new CoordDirection(currentLocation, currentDirection));

            while(true) {
                // Check next position
                Coord nextPosition = getNextPosition(currentDirection, currentLocation);

                // If we leave the grid, we have not found a loop
                if (!(nextPosition.x() >= 0 && nextPosition.y() >= 0 && nextPosition.x() < rowLength && nextPosition.y() < columnLength)) {
                    break;
                }

                // Check the next block if we have to turn and decide direction, then alter nextPosition
                if (areaMap[nextPosition.y()][nextPosition.x()] == '#' || nextPosition.equals(coord)) {
                    currentDirection = Direction.fromValue((currentDirection.direction % 4) + 1);
                    // If the position and direction is already in the set, we have encountered a loop
                    if (!coordDirectionSet.add(new CoordDirection(currentLocation, currentDirection))) {
                        nrOfObstacles++;
                        break;
                    }
                } else {
                    // Take a step
                    currentLocation = nextPosition;
                }
            }
        }

        System.out.println("Part 2: " + nrOfObstacles);

    }

}

record CoordDirection(Coord coord, Direction direction){}

