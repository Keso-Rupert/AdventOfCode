package dev.kesorupert.aoc24.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day9 {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day9/test.txt"));
        
        solvePart1(input);

    }
    
    static void solvePart1(String input) {
        String blocksAndIds = createFileAndFreeBlocks(input);
        String blocksAndIdsCompressed = moveBlocksToFreeSpace(blocksAndIds);
        long results = calculateChecksum(blocksAndIdsCompressed);

        System.out.println(blocksAndIds);
        System.out.println(blocksAndIdsCompressed);
        System.out.println("Part 1: " + results);
    }

    private static long calculateChecksum(String blocksAndIds) {
        long result = 0;
        for (int c = 0; c < blocksAndIds.length(); c++) {
            if (blocksAndIds.charAt(c) != '.') {
                result += ((long) c * Integer.parseInt(String.valueOf(blocksAndIds.charAt(c)))); // OMG
            } else {
                // When we've reached '.' we have reached the end
                return result;
            }
        }

        return result;
    }

    private static String moveBlocksToFreeSpace(String blocksAndIds) {
        StringBuilder compressedString = new StringBuilder(blocksAndIds.length());

        int currentFileIndex = blocksAndIds.length()-1;

        // Loop through String that is being compressed
        for (int d = 0; d < blocksAndIds.length(); d++) {

            if (d > currentFileIndex) { // Why does this work on the test input but not on the full input?!
                break;
            }

            // If we find a number, we can simply move it
            if (blocksAndIds.charAt(d) != '.') {
                compressedString.replace(d, d+1, String.valueOf(blocksAndIds.charAt(d)));
            } else {
                // Else we find numbers starting from the end
                while(blocksAndIds.charAt(currentFileIndex) == '.') {
                    currentFileIndex--;
                }

                // If we find an empty place, replace that with a number
                compressedString.replace(d, d+1, String.valueOf(blocksAndIds.charAt(currentFileIndex)));
                currentFileIndex--;

            }
        }

        return compressedString.toString();

    }

    static String createFileAndFreeBlocks(String input) {
        StringBuilder blocksAndIds = new StringBuilder();
        boolean file = true;
        int blockId = 0;

        for (int i = 0; i < input.length(); i++) {
            int blockFileLength = Integer.parseInt(input.substring(i, i+1));

            for (int j = 0; j < blockFileLength; j++) {
                if (file) {
                    blocksAndIds.append(blockId);
                } else {
                    blocksAndIds.append('.');
                }
            }

            // If we've read a file block, increment block ID
            if (file) blockId++;

            // Switch file boolean
            file = !file;
        }
        return blocksAndIds.toString();
    }

}
