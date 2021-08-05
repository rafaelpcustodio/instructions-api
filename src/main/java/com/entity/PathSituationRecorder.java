package com.entity;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PathSituationRecorder {
    private Integer currentX;
    private Integer currentY;
    private final List<Coordinate> patchPositionsFound;
    private Coordinate lastPosition;

    public PathSituationRecorder(final Integer x, final Integer y, final int[][] scenario) {
        final List<Coordinate> patchPositions = new ArrayList<>();
        if(scenario[x][y] == 1) {
            patchPositions.add(new Coordinate(x, y));
        }
        this.currentX = x;
        this.currentY = y;
        this.patchPositionsFound = patchPositions;
        this.lastPosition = new Coordinate(x, y);
    }

    public Integer getPatchesQuantity() {
        return this.getPatchPositionsFound().size();
    }

    public void countPatch(final Coordinate coordinate) {
        if(!this.patchPositionsFound.contains(coordinate)) {
            this.patchPositionsFound.add(coordinate);
        }
    }

    public Coordinate getNextCoordinate(
            final Character cardinalDirection
    ) {
        final Coordinate nextStep = Coordinate.cardinalToCoordinate(cardinalDirection);
        this.currentX = this.currentX + nextStep.getX();
        this.currentY = this.currentY + nextStep.getY();
        this.lastPosition = new Coordinate(this.currentX, this.currentY);
        return this.lastPosition;
    }
}
