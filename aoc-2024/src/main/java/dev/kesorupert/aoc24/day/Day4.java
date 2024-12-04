package dev.kesorupert.aoc24.day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        solvePart2(wordGrid);

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
                    if (checkDiagonalRightDown(wordGrid, x, y, "MAS")) totalXmasses++;
                    if (checkDiagonalRightUp(wordGrid, x, y, "MAS")) totalXmasses++;
                    if (checkDiagonalLeftDown(wordGrid, x, y, "MAS")) totalXmasses++;
                    if (checkDiagonalLeftUp(wordGrid, x, y, "MAS")) totalXmasses++;
                }
            }

        }

        System.out.println("Part 1: " + totalXmasses);
    }

    public static void solvePart2(char[][] wordGrid) {
        int totalXmasses = 0;
        List<Coord> listOfMasCoords = new ArrayList<>();

        for (int y = 0 ; y < rowLength; y++) {
            for (int x = 0 ; x < columnLength ; x++) {
                if (wordGrid[y][x] == 'M') {
                    if (checkDiagonalRightDown(wordGrid, x, y, "AS")) {
                        Coord centerMasCoord = new Coord(x+1, y+1);
                        if (listOfMasCoords.contains(centerMasCoord)) {
                            totalXmasses++;
                        } else {
                            listOfMasCoords.add(centerMasCoord);
                        }
                    }
                    if (checkDiagonalRightUp(wordGrid, x, y, "AS")) {
                        Coord centerMasCoord = new Coord(x+1, y-1);
                        if (listOfMasCoords.contains(centerMasCoord)) {
                            totalXmasses++;
                        } else {
                            listOfMasCoords.add(centerMasCoord);
                        }
                    }
                    if (checkDiagonalLeftDown(wordGrid, x, y, "AS")) {
                        Coord centerMasCoord = new Coord(x-1, y+1);
                        if (listOfMasCoords.contains(centerMasCoord)) {
                            totalXmasses++;
                        } else {
                            listOfMasCoords.add(centerMasCoord);
                        }
                    }
                    if (checkDiagonalLeftUp(wordGrid, x, y, "AS")) {
                        Coord centerMasCoord = new Coord(x-1, y-1);
                        if (listOfMasCoords.contains(centerMasCoord)) {
                            totalXmasses++;
                        } else {
                            listOfMasCoords.add(centerMasCoord);
                        }
                    }
                }
            }

        }

        System.out.println("Part 2: " + totalXmasses);
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

    private static boolean checkDiagonalRightUp(char[][] grid, int x, int y, String word) {
        // Check if there is room for the word
        int wordLength = word.length();
        
        if (x < rowLength - wordLength && y >= wordLength) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int i = 1; i <= wordLength; i++) {
                wordBuilder.append(grid[y-i][x+i]);
            }
            return wordBuilder.toString().equals(word);
        }
        return false;
    }

    private static boolean checkDiagonalRightDown(char[][] grid, int x, int y, String word) {
        // Check if there is room for the word
        int wordLength = word.length();
        if (x < rowLength - wordLength && y < columnLength - wordLength) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int i = 1; i <= wordLength; i++) {
                wordBuilder.append(grid[y+i][x+i]);
            }
            return wordBuilder.toString().equals(word);
        }
        return false;
    }

    private static boolean checkDiagonalLeftUp(char[][] grid, int x, int y, String word) {
        // Check if there is room for the word
        int wordLength = word.length();
        
        if (x >= wordLength && y >= wordLength) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int i = 1; i <= wordLength; i++) {
                wordBuilder.append(grid[y-i][x-i]);
            }
            return wordBuilder.toString().equals(word);
        }
        return false;
    }

    private static boolean checkDiagonalLeftDown(char[][] grid, int x, int y, String word) {
        // Check if there is room for the word
        int wordLength = word.length();
        
        if (x >= wordLength && y < columnLength - wordLength) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int i = 1; i <= wordLength; i++) {
                wordBuilder.append(grid[y+i][x-i]);
            }
            if ( wordBuilder.toString().equals(word) ) {
                return true;
            }
        }
        return false;
    }

}

record Coord (int x, int y) {}