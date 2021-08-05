package com.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
public class Coordinate {
    private final Integer x;
    private final Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate cardinalToCoordinate(
            final Character cardinalDirection
    ) {
        return getCardinalToCoordinateMap(cardinalDirection);
    }

    private static Coordinate getCardinalToCoordinateMap(
            final Character cardinalDirection
    ) {
        final Map<Character, Coordinate> cardinalToCoordinateMap = new HashMap<>();
        cardinalToCoordinateMap.put('S', new Coordinate(0, -1));
        cardinalToCoordinateMap.put('N', new Coordinate(0, 1));
        cardinalToCoordinateMap.put('W', new Coordinate(-1, 0));
        cardinalToCoordinateMap.put('E', new Coordinate(1, 0));
        return cardinalToCoordinateMap.get(cardinalDirection);
    }
}
