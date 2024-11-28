package dev.kesorupert.aoc15.day7;

import dev.kesorupert.aoc15.util.InputReader;

import java.util.HashMap;
import java.util.stream.Stream;

public class Day7 {

    static HashMap<String, Integer> wireSignalMap = new HashMap<>();

    void main(){
        InputReader reader = new InputReader("aoc-2015/src/main/java/dev/kesorupert/aoc15/day7/input.txt");

        solvePart1(reader.getInputStreamForLines());
        solvePart2(reader.getInputStreamForLines());
    }

    static void solvePart1(Stream<String> lines){
        for (String line : (Iterable<String>) lines::iterator) {
            String[] parts = line.split("->");
            String instructions = parts[0].trim();
            String targetWire = parts[1].trim();

            if (instructions.contains("AND")) {
                String[] inputWires = instructions.split("AND");

            } else if (instructions.contains("OR")) {

            } else if (instructions.contains("LSHIFT")) {

            } else if (instructions.contains("RSHIFT")) {

            } else if (instructions.contains("NOT")) {

            } else {
                // check if instructions are only numbers, or input is a wire
                if (instructions.matches("-?\\d+(\\.\\d+)?")) {
                    wireSignalMap.put(targetWire, Integer.parseInt(instructions));
                }
            }
        }

        System.out.println(wireSignalMap);
    }

    static void solvePart2(Stream<String> lines){

    }

}
