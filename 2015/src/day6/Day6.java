package day6;

import util.InputReader;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class Day6 {

    static Set<Coords> turnedOnLights = new HashSet<>();

    public static void main(String[] args) {
        InputReader reader = new InputReader("2015/src/day6/input.txt");

        solvePart1(reader.getInputStreamForLines());
//        solvePart2(reader.getInputStreamForLines());
    }


    private static void solvePart1(Stream<String> lines) {


        for (String line : (Iterable<String>) lines::iterator) {
            // Sneaky replace to make sure amount of words is the same for the input lines
            line = line.replace("turn on", "turnon").replace("turn off", "turnoff");

            Coords startingCoords = getStartingCoords(line);
            Coords endingCoords = getEndingCoords(line);

            if (line.startsWith("turnon")) {
                turnOnLightAtCoords(startingCoords, endingCoords);
            } else if ( line.startsWith("turnoff")){
                turnOffLightAtCoords(startingCoords, endingCoords);
            } else {
                // Toggle behaviour
                for (int x = startingCoords.x(); x <= endingCoords.x(); x++) {
                    for (int y = startingCoords.y(); y <= endingCoords.y(); y++) {
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

    private static void turnOnLightAtCoords(Coords startingCoords, Coords endingCoords) {

        for (int x = startingCoords.x(); x <= endingCoords.x(); x++) {
            for (int y = startingCoords.y(); y <= endingCoords.y(); y++) {
                turnedOnLights.add(new Coords(x, y));
            }
        }
    }

    private static void turnOffLightAtCoords(Coords startingCoords, Coords endingCoords) {
        for (int x = startingCoords.x(); x <= endingCoords.x(); x++) {
            for (int y = startingCoords.y(); y <= endingCoords.y(); y++) {
                turnedOnLights.remove(new Coords(x, y));
            }
        }
    }

    private static void solvePart2(Stream<String> lines) {


    }
}

record Coords(int x, int y) {}