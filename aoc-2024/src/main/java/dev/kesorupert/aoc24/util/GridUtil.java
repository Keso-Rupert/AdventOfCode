package dev.kesorupert.aoc24.util;

public class GridUtil {

    public static Coord getNextPosition(Direction direction, Coord currentPosition) {
        return switch(direction) {
            case NORTH -> new Coord(currentPosition.x(), currentPosition.y() - 1);
            case SOUTH -> new Coord(currentPosition.x(), currentPosition.y() + 1);
            case EAST -> new Coord(currentPosition.x() + 1, currentPosition.y());
            case WEST -> new Coord(currentPosition.x() - 1, currentPosition.y());
        };
    }

    public static boolean checkGridBoundaries(int[][] grid, Coord coordinates) {
        int rowLength = grid.length;
        int columnLength;
        if (grid.length > 0) {
            columnLength = grid[0].length;
        } else {
            return false;
        }

        return coordinates.x() >= 0 && coordinates.y() >= 0 && coordinates.x() < rowLength && coordinates.y() < columnLength;
    }

}
