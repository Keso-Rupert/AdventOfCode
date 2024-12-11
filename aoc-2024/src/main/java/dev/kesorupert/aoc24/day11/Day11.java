package dev.kesorupert.aoc24.day11;

import java.util.ArrayList;
import java.util.List;

public class Day11 {

    public static void main(String[] args) {
        String input = "28591 78 0 3159881 4254 524155 598 1";
//        input = "125 17";
        int blinks = 25;

        List<Long> stones = new ArrayList<>();
        for (String in : input.split(" ")) {
            stones.add(Long.parseLong(in));
        }

        for (int i = 0; i < blinks; i++) {
            System.out.println("Blink " + i+1);
            List<Long> newStones = new ArrayList<>();
            while(newStones.size() < stones.size()) newStones.add(0L);
            int listSize = stones.size();
            int newListSize = listSize;
            int index = 0;
            int newIndex = 0;
            while (true) {
                if (index >= listSize) break;
                Long stoneValue = stones.get(index);
                String stoneString = String.valueOf(stoneValue);
                int stoneLength = stoneString.length();

                if (stoneValue == 0) {
                    newStones.set(newIndex, 1L);
                } else if (stoneLength % 2 == 0) {
                    Long stoneA = Long.parseLong(stoneString.substring(0, stoneLength/2));
                    Long stoneB = Long.parseLong(stoneString.substring(stoneLength/2));
                    newStones.add(newIndex, stoneA);
                    newListSize++;
                    newIndex++;
                    newStones.set(newIndex, stoneB);
                } else {
                    newStones.set(newIndex, stoneValue * 2024);
                }
                newIndex++;
                index++;
            }
            stones = newStones;
            listSize = newListSize;
        }

        System.out.println("Part 1: " + stones.size());

    }

}
