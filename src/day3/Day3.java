package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Day3 {

    static HashMap<Coords, Integer> visitsMap = new HashMap<>();

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        // Naive solution? create a hashmap<Coords : NrOfVisits>, go through input
        // For each direction, either add to the hashmap, or increment the nr of visits
        // Then filter on NrOfVisits > 1 and return length of hashmapc

        Coords currentCoords = new Coords(0,0);
        visitsMap.put(currentCoords, 1);

        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/day3/input.txt"))) {
            int character;
            while ((character = reader.read()) != -1) {

                currentCoords = switch ((char) character) {
                    case '>' -> new Coords(currentCoords.x() + 1, currentCoords.y());
                    case '<' -> new Coords(currentCoords.x() - 1, currentCoords.y());
                    case 'v' -> new Coords(currentCoords.x(), currentCoords.y() - 1);
                    case '^' -> new Coords(currentCoords.x(), currentCoords.y() + 1);
                    default -> {
                        System.out.println("This should not happen!");
                        yield new Coords(0,0);
                    }
                };

                if (visitsMap.containsKey(currentCoords)) {
                    visitsMap.put(currentCoords, visitsMap.get(currentCoords) + 1);
                } else {
                    visitsMap.put(currentCoords, 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(visitsMap.entrySet().stream().filter( coordsEntry -> coordsEntry.getValue() >= 1).count());

    }

    private static void solvePart2() {
    }

}

record Coords(int x, int y) {}
