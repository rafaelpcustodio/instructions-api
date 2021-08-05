package com.entity;

import lombok.Getter;

@Getter
public class InstructionsOutput {
    private final Coordinate finalPosition;
    private final Integer oilPatchesCleaned;

    public InstructionsOutput(final Coordinate finalPosition, final Integer oilPatchesCleaned) {
        this.finalPosition = finalPosition;
        this.oilPatchesCleaned = oilPatchesCleaned;
    }
}
