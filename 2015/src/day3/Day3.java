package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Day3 {

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        // Naive solution? create a hashmap<Coords : NrOfVisits>, go through input
        // For each direction, either add to the hashmap, or increment the nr of visits
        // Then filter on NrOfVisits > 1 and return length of hashmapc
        HashMap<Coords, Integer> visitsMap = new HashMap<>();
        Coords currentCoords = new Coords(0,0);
        visitsMap.put(currentCoords, 1);

        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/day3/input.txt"))) {
            int character;
            while ((character = reader.read()) != -1) {

                currentCoords = updateCoords((char) character, currentCoords);

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
        // Extract method that checks the coords, so we can update two separate coords
        // On uneven read nrs we update santa, on even we update robosanta
        // Extract checking coords since we need to do it twice
        // Checking of visits remains the same

        HashMap<Coords, Integer> visitsMap = new HashMap<>();
        Coords santaCoords = new Coords(0,0);
        Coords roboCoords = new Coords(0,0);
        visitsMap.put(santaCoords, 2);

        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/day3/input.txt"))) {
            int character;
            int position = 0;

            while ((character = reader.read()) != -1) {

                if(position % 2 == 1) {
                    santaCoords = updateCoords((char) character, santaCoords);
                    checkCoords(santaCoords, visitsMap);
                } else {
                    roboCoords = updateCoords((char) character, roboCoords);
                    checkCoords(roboCoords, visitsMap);
                }

                position++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(visitsMap.entrySet().stream().filter( coordsEntry -> coordsEntry.getValue() >= 1).count());

    }

    private static Coords updateCoords(Character character, Coords currentCoords) {
        return switch (character) {
            case '>' -> new Coords(currentCoords.x() + 1, currentCoords.y());
            case '<' -> new Coords(currentCoords.x() - 1, currentCoords.y());
            case 'v' -> new Coords(currentCoords.x(), currentCoords.y() - 1);
            case '^' -> new Coords(currentCoords.x(), currentCoords.y() + 1);
            default -> {
                System.out.println("This should not happen!");
                yield new Coords(0,0);
            }
        };
    }

    private static void checkCoords(Coords coords, HashMap<Coords, Integer> visitsMap) {
        if (visitsMap.containsKey(coords)) {
            visitsMap.put(coords, visitsMap.get(coords) + 1);
        } else {
            visitsMap.put(coords, 1);
        }
    }

}

record Coords(int x, int y) {}
