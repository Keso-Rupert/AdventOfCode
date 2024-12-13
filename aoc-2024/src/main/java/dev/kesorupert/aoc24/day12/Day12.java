package dev.kesorupert.aoc24.day12;

import dev.kesorupert.aoc24.util.Coord;
import dev.kesorupert.aoc24.util.Direction;
import dev.kesorupert.aoc24.util.GridUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day12 {

    public static void main(String[] args) throws IOException {
        String[] input = Files.lines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day12/test.txt")).toArray(String[]::new);
        char[][] gardenMap = GridUtil.getCharGrid(input);

        solvePart1(gardenMap);

    }

    static void solvePart1(char[][] gardenMap) {
        long totalPrice = 0L;

        // We will store the regions in a Map, with a List containing a Set of all Coords in one region
        Map<Character, List<Set<Coord>>> regionMap = getAllRegions(gardenMap);


        System.out.println("Part 1: " + totalPrice);
    }

    static Map<Character, List<Set<Coord>>> getAllRegions(char[][] gardenMap) {
        Map<Character, List<Set<Coord>>> regionMap = new HashMap<>();


        for (int y = 0; y < gardenMap.length; y++) {
            characterLoop:
            for (int x = 0; x < gardenMap[y].length; x++) {
                char currentChar = gardenMap[y][x];
                boolean added = false;
                // First check if the character doesn't already exist and compute it if it doesn't
                if (!regionMap.containsKey(currentChar)) {
                    regionMap.computeIfAbsent(currentChar, regionList -> new ArrayList<>()).add(new HashSet<>(Set.of(new Coord(x, y))));
                } else {

                    // Then we get all Sets of coords and check if it should be added to one of the Sets if it is adjacent to one of the Coords
                    Coord currentCoord = new Coord(x, y);
                    List<Set<Coord>> regionsList = regionMap.get(currentChar);

                    // Create temporary list and set to avoid Concurrent Modification Exception
                    List<Set<Coord>> newRegionsList = new ArrayList<>();
                    for (Set<Coord> set : regionsList) {
                        newRegionsList.addAll(regionsList);
                        // If the Coord already exists, we can stop this iteration and start checking the next character
                        if (set.contains(currentCoord)) {
                            continue characterLoop;
                        }

                        Set<Coord> newSet = new HashSet<>();

                        newSet = checkAdjecentPlots(gardenMap, currentCoord, newSet);

                        for (Coord coordToCheck : set) {
                            // Check all around our current position
                            for (Direction direction : Direction.values()) {
                                Coord nextPossiblePosition = GridUtil.getNextPosition(direction, currentCoord);
                                if (!GridUtil.checkWithinGridBoundaries(gardenMap, nextPossiblePosition)) continue;
                                // If we have found a coordinate that is in the set, we can add the current coordinate to that set
                                if (coordToCheck.equals(nextPossiblePosition)) {
                                    newSet.addAll(set);
                                    newSet.add(currentCoord);
                                    added = true;
                                    break;
                                }
                            }
                            if (added) break;
                        }
                        if (added) {
                            set.addAll(newSet);
                            break;
                        } else {
                            // We have found a character that forms a separate region and should be in its own set
                            newRegionsList.add(new HashSet<>(Set.of(currentCoord)));
                            added = true;
                            break;
                        }
                    }
                    if (added) {
                        regionsList = new ArrayList<>(newRegionsList);
                    }
                }
            }
        }


        return regionMap;

    }

    static Set<Coord> checkAdjecentPlots(char[][] gardenMap, Coord currentCoord, Set<Coord> set) {

    }

    static int calculateArea(Set<Coord> region) {

        return 0;
    }

    static int calculateCircumference(Set<Coord> region) {

        return 0;
    }


}
