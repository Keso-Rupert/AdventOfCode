package dev.kesorupert.aoc15.day4;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "yzbqklnj";

        solvePart1(input);
        solvePart2(input);
    }

    public static void solvePart1(String input) throws NoSuchAlgorithmException {
        solvePuzzleWithLeadingZeroes(input, "00000");
    }

    public static void solvePart2(String input) throws NoSuchAlgorithmException {
        // ok interesting to see how much longer it takes with the simple approach
        solvePuzzleWithLeadingZeroes(input, "000000");
        // Took 38 seconds instead of 1.5...
    }

    private static void solvePuzzleWithLeadingZeroes(String input, String leadingZeroes) throws NoSuchAlgorithmException {
        int i = 1;
        boolean notFound = false;
        long start = System.currentTimeMillis();

        while (notFound == false) {
            String result = createMd5Hash(input + i);

            if (result.startsWith(leadingZeroes)) {
                notFound = true;
                System.out.println("Found leading zeroes, lowest number is: " + i);
            }
            i++;
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time elapsed: " + timeElapsed);
    }

    public static String createMd5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(input.getBytes());
        byte[] hashInBytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
