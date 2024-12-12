package dev.kesorupert.aoc24.util;

import java.util.Objects;

public enum Direction {
    NORTH(1),
    EAST(2),
    SOUTH(3),
    WEST(4);

    public final Integer direction;

    Direction(Integer direction) {
        this.direction = direction;
    }

    public static Direction fromValue(Integer value) {
        for (Direction dir : Direction.values()) {
            if (Objects.equals(dir.direction, value)) return dir;
        }
        throw new IllegalArgumentException("Provide valid direction value between 1 and 4, value provided = " + value);
    }
}

