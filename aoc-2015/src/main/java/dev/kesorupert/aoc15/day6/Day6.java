package dev.kesorupert.aoc15.day6;

import dev.kesorupert.aoc15.util.InputReader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Day6 {

    static Set<Coords> turnedOnLights = new HashSet<>();
    static Map<Coords, Integer> lightsAndBrightness = new HashMap<Coords, Integer>();

    public static void main(String[] args) {
        InputReader reader = new InputReader("aoc-2015/src/main/java/dev/kesorupert/aoc15/day6/input.txt");

        solvePart1(reader.getInputStreamForLines());
        solvePart2(reader.getInputStreamForLines());
    }

    private static void solvePart1(Stream<String> lines) {

        for (String line : (Iterable<String>) lines::iterator) {
            // Sneaky replace to make sure amount of words is the same for the input lines
            line = line.replace("turn on", "turnon").replace("turn off", "turnoff");

            Coords startingCoords = getStartingCoords(line);
            Coords endingCoords = getEndingCoords(line);

            for (int x = startingCoords.x(); x <= endingCoords.x(); x++) {
                for (int y = startingCoords.y(); y <= endingCoords.y(); y++) {
                    if (line.startsWith("turnon")) {
                        turnedOnLights.add(new Coords(x, y));
                    } else if ( line.startsWith("turnoff")){
                        turnedOnLights.remove(new Coords(x, y));
                    } else {
                        // Toggle behaviour
                        if (turnedOnLights.contains(new Coords(x, y))) {
                            turnedOnLights.remove(new Coords(x, y));
                        } else {
                            turnedOnLights.add(new Coords(x, y));
                        }
                    }
                }
            }
        }
        System.out.println(turnedOnLights.size());

    }

    private static Coords getStartingCoords(String line) {
        int startingX = Integer.parseInt(line.split(" ")[1].split(",")[0]);
        int startingY = Integer.parseInt(line.split(" ")[1].split(",")[1]);

        return new Coords(startingX, startingY);
    }

    private static Coords getEndingCoords(String line) {
        int endingX = Integer.parseInt(line.split(" ")[3].split(",")[0]);
        int endingY = Integer.parseInt(line.split(" ")[3].split(",")[1]);
        return new Coords(endingX, endingY);
    }

    private static void solvePart2(Stream<String> lines) {

        for (String line : (Iterable<String>) lines::iterator) {
            line = line.replace("turn on", "turnon").replace("turn off", "turnoff");

            Coords startingCoords = getStartingCoords(line);
            Coords endingCoords = getEndingCoords(line);

            for (int x = startingCoords.x(); x <= endingCoords.x(); x++) {
                for (int y = startingCoords.y(); y <= endingCoords.y(); y++) {
                    Coords currentCoords = new Coords(x, y);
                    lightsAndBrightness.putIfAbsent(currentCoords, 0);

                    if (line.startsWith("turnon")) {
                        lightsAndBrightness.put(currentCoords, lightsAndBrightness.get(currentCoords) + 1);
                    } else if ( line.startsWith("turnoff")){
                        lightsAndBrightness.put(currentCoords, Math.max(lightsAndBrightness.get(currentCoords) - 1, 0));
                    } else {
                        // Toggle behaviour
                        lightsAndBrightness.put(currentCoords, lightsAndBrightness.get(currentCoords) + 2);
                    }
                }
            }
        }
        System.out.println(lightsAndBrightness.values().stream().reduce(0, Integer::sum));
    }
}

record Coords(int x, int y) {}