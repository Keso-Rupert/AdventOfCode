package dev.kesorupert.aoc24.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day9/input.txt"));
//        input = "14113"; // solution should be 132
        solvePart1(input);

    }
    
    static void solvePart1(String input) {
        List<Integer> blocksAndIds = createFileAndFreeBlocks(input);
        List<Integer> blocksAndIdsCompressed = moveBlocksToFreeSpace(blocksAndIds);
        long results = calculateChecksum(blocksAndIdsCompressed);

//        System.out.println(blocksAndIds); // This is correct
//        System.out.println(blocksAndIdsCompressed);
        System.out.println("Part 1: " + results);
    }

    static List<Integer> createFileAndFreeBlocks(String input) {
        List<Integer> blocksAndIds = new ArrayList<>();
        boolean file = true;
        int blockId = 0;

        for (int i = 0; i < input.length(); i++) {
            int blockFileLength = Integer.parseInt(input.substring(i, i+1));

            for (int j = 0; j < blockFileLength; j++) {
                if (file) {
                    blocksAndIds.add(blockId);
                } else {
                    blocksAndIds.add(-1);
                }
            }

            // If we've read a file block, increment block ID
            if (file) blockId++;

            // Switch file boolean
            file = !file;
        }
        return blocksAndIds;
    }

    private static List<Integer> moveBlocksToFreeSpace(List<Integer> blocksAndIds) {
        // MISTAKE IS THAT IM SUPPOSED TO MOVE ENTIRE BLOCKS, so 8 9 and then 10 will be two characters!!

        List<Integer> compressedFile = new ArrayList<>();

        int reverseFileIndex = blocksAndIds.size()-1;

        // Loop through String that is being compressed
        for (int index = 0; index < blocksAndIds.size(); index++) {

            if (reverseFileIndex < index ) {
                break;
            }

            // If we find a number, we can simply move it
            if (blocksAndIds.get(index) != -1) {
                compressedFile.add(index, blocksAndIds.get(index));
            } else {
                // Else we find numbers starting from the end
                while(blocksAndIds.get(reverseFileIndex) == -1) {
                    reverseFileIndex--;
                }

                // If we find an empty place, replace that with a number
                compressedFile.add(index, blocksAndIds.get(reverseFileIndex));
                reverseFileIndex--;

            }
        }

        return compressedFile;

    }

    private static long calculateChecksum(List<Integer> blocksAndIds) {
        long result = 0;
        for (int c = 0; c < blocksAndIds.size(); c++) {
            if (blocksAndIds.get(c) != -1) {
                result += ((long) c * blocksAndIds.get(c));
            } else {
                // When we've reached '.' we have reached the end
                return result;
            }
        }

        return result;
    }

}
