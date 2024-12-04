package dev.kesorupert.aoc24.day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day4 {

    static int rowLength = 0;
    static int columnLength = 0;

    public static void main(String[] args) throws IOException {
        String[] lines = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day/input.txt")).toArray(String[]::new);
        rowLength = lines.length;
        columnLength = lines[0].length();
        char[][] wordGrid = new char[rowLength][columnLength];

        for (int i = 0 ; i < rowLength; i++) {
            for (int j = 0 ; j < columnLength ; j++) {
                wordGrid[i][j] = lines[i].charAt(j);
                // I can also work from here, but I prefer the [i][j] instead of [i].charAt(j)
            }
        }

        solvePart1(wordGrid);

    }

    public static void solvePart1(char[][] wordGrid) {

        int totalXmasses = 0;

        for (int y = 0 ; y < rowLength; y++) {
            for (int x = 0 ; x < columnLength ; x++) {
                if (wordGrid[y][x] == 'X') {
                    if (checkLeftToRight(wordGrid, x, y)) totalXmasses++;
                    if (checkRightToLeft(wordGrid, x, y)) totalXmasses++;
                    if (checkUpToDown(wordGrid, x, y)) totalXmasses++;
                    if (checkDownToUp(wordGrid, x, y)) totalXmasses++;
                    if (checkDiagonalRightDown(wordGrid, x, y)) totalXmasses++;
                    if (checkDiagonalRightUp(wordGrid, x, y)) totalXmasses++;
                    if (checkDiagonalLeftDown(wordGrid, x, y)) totalXmasses++;
                    if (checkDiagonalLeftUp(wordGrid, x, y)) totalXmasses++;
                }
            }

        }

        System.out.println("Part 1: " + totalXmasses);
    }

    private static boolean checkLeftToRight(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (x < rowLength - 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y][x+i]);
            }
            return word.toString().equals("MAS");
        }
        return false;
    }

    private static boolean checkRightToLeft(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (x >= 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y][x-i]);
            }
            return word.toString().equals("MAS");
        }
        return false;
    }

    private static boolean checkUpToDown(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (y < columnLength - 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y+i][x]);
            }
            return word.toString().equals("MAS");
        }
        return false;
    }

    private static boolean checkDownToUp(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (y >= 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y-i][x]);
            }
            return word.toString().equals("MAS");
        }
        return false;
    }

    private static boolean checkDiagonalRightUp(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (x < rowLength - 3 && y >= 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y-i][x+i]);
            }
            return word.toString().equals("MAS");
        }
        return false;
    }

    private static boolean checkDiagonalRightDown(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (x < rowLength - 3 && y < columnLength - 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y+i][x+i]);
            }
            return word.toString().equals("MAS");
        }
        return false;
    }

    private static boolean checkDiagonalLeftUp(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (x >= 3 && y >= 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y-i][x-i]);
            }
            return word.toString().equals("MAS");
        }
        return false;
    }

    private static boolean checkDiagonalLeftDown(char[][] grid, int x, int y) {
        // Check if there is room for the word
        if (x >= 3 && y < columnLength - 3) {
            StringBuilder word = new StringBuilder();
            for (int i = 1; i <= 3 ; i++) {
                word.append(grid[y+i][x-i]);
            }
            if ( word.toString().equals("MAS") ) {
                return true;
            }
        }
        return false;
    }

}
