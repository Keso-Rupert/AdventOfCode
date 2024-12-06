package dev.kesorupert.aoc24.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {

    static List<List<Integer>> orderingRulesList = new ArrayList<>();
    static Map<Integer, List<Integer>> orderingRulesMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("aoc-2024/src/main/java/dev/kesorupert/aoc24/day5/input.txt"));
        List<List<Integer>> pageNumbersList = new ArrayList<>();
        List<List<Integer>> correctPageNumbersList = new ArrayList<>();
        List<List<Integer>> incorrectPageNumbersList = new ArrayList<>();

        int part1Total = 0;
        boolean rulesRead = false;

        // First separate and create the ordering rules and page numbers lists
        for (String line : lines) {
            if (line.isEmpty()) { // Why does System.lineSeparator() not work here?
                rulesRead = true;
                continue;
            }
            if (!rulesRead) {
                String[] rules = line.split("\\|");
                int rule1 = Integer.parseInt(rules[0]);
                int rule2 = Integer.parseInt(rules[1]);

                orderingRulesList.add(Stream.of(line.split("\\|")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new)));

                if (orderingRulesMap.containsKey(rule1)) {
                    List<Integer> newPageRules = orderingRulesMap.get(rule1);
                    newPageRules.add(rule2);
                    orderingRulesMap.put(rule1, newPageRules);
                } else {
                    orderingRulesMap.put(rule1, new ArrayList<>(List.of(rule2)));
                }
            } else {
                pageNumbersList.add(Stream.of(line.split(",")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new)));
            }
        }

        for (List<Integer> pageNumbers : pageNumbersList) {
            if (checkIfPageOrderCorrect(pageNumbers)) {
                correctPageNumbersList.add(pageNumbers);
            } else {
                // For part 2
                incorrectPageNumbersList.add(pageNumbers);
            }
        }

        for (List<Integer> pageNumbers : correctPageNumbersList) {
            part1Total += pageNumbers.get(pageNumbers.size() / 2);
        }

        System.out.println("Part 1: " + part1Total);

        int part2Total = 0;
        SortPagesByRules sortPagesByRules = new SortPagesByRules(orderingRulesMap);

        for (int i = 0 ; i < incorrectPageNumbersList.size() ; i++) {
            List<Integer> numbersList = incorrectPageNumbersList.get(i);

            numbersList.sort(sortPagesByRules);

            part2Total += numbersList.get(numbersList.size() / 2);
        }

        System.out.println("Part 2: " + part2Total);

    }

    private static boolean checkIfPageOrderCorrect(List<Integer> pageNumbers) {
        // We go through all rules to decide whether the numbers in the page numbers comply
        for (List<Integer> orderingRules : orderingRulesList) {
            Integer ruleNr1 = orderingRules.get(0); // is the number that should be before nr 2
            Integer ruleNr2 = orderingRules.get(1);

            if (pageNumbers.contains(ruleNr1) && pageNumbers.contains(ruleNr2)) {
                // Nr 1 should have a lower index than nr 2, otherwise it doesn't have the right order
                if (pageNumbers.indexOf(ruleNr1) > pageNumbers.indexOf(ruleNr2)) {
                    return false;
                }
            }
        }

        return true;
    }
}

class SortPagesByRules implements Comparator<Integer> {

    Map<Integer, List<Integer>> rulesMap;

    public SortPagesByRules(Map<Integer, List<Integer>> rulesMap) {
        this.rulesMap = rulesMap;
    }

    @Override
    public int compare(Integer currentPage, Integer nextPage) {
        // Return smaller when the currentpage has a rule that dictates it being before the nextPage
        if (rulesMap.get(currentPage).contains(nextPage)) {
            return -1;
        } else {
            return 1;
        }
    }
}
