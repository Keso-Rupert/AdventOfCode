package dev.kesorupert.aoc24.day6;

import dev.kesorupert.aoc24.util.Coord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    static int rowLength = 0;
    static int columnLength = 0;
    static Coord startingPosition;
    static List<Coord> obstacles = new ArrayList<Coord>();

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
        int markedArea = 0;

        while(true) {
            // Check next position
            Coord nextPosition = getNextPosition(currentDirection, currentLocation);

            // Mark the current position
            if (areaMap[currentLocation.y()][currentLocation.x()] != 'X') {
                areaMap[currentLocation.y()][currentLocation.x()] = 'X';
                markedArea++;
            }

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

        System.out.println("Part 1: " + markedArea);

    }

    private static Coord getNextPosition(Direction currentDirection, Coord currentLocation) {
        Coord nextPosition = switch(currentDirection) {
            case NORTH -> new Coord(currentLocation.x(), currentLocation.y() - 1);
            case SOUTH -> new Coord(currentLocation.x(), currentLocation.y() + 1);
            case EAST -> new Coord(currentLocation.x() + 1, currentLocation.y());
            case WEST -> new Coord(currentLocation.x() - 1, currentLocation.y());
        };
        return nextPosition;
    }

    static void solvePart2(char[][] areaMap){

    }

}

enum Direction {
    NORTH(1),
    EAST(2),
    SOUTH(3),
    WEST(4);

    public final Integer direction;

    Direction(Integer direction) {
        this.direction = direction;
    }

    static Direction fromValue(Integer value) {
        for (Direction dir : Direction.values()) {
            if (dir.direction == value) return dir;
        }
        throw new IllegalArgumentException("Provide valid direction value between 1 and 4, value provided = " + value);
    }
}

