package com.entity;

import com.interfaces.adapter.rest.dto.request.InstructionsRequestDTO;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class InstructionsInput {
    private final Coordinate areaSize;
    private final Coordinate startingPosition;
    private final List<Coordinate> oilPatches;
    private final String navigationInstructions;

    public InstructionsInput(final InstructionsRequestDTO instructionsRequestDTO) {
        if(isInvalidInput(instructionsRequestDTO)) {
            throw new IllegalArgumentException();
        }
        this.areaSize = new Coordinate(instructionsRequestDTO.getAreaSize().get(0), instructionsRequestDTO.getAreaSize().get(1));
        this.startingPosition = new Coordinate(instructionsRequestDTO.getStartingPosition().get(0), instructionsRequestDTO.getStartingPosition().get(1));
        final List<Coordinate> oilPatchesList = new ArrayList<>();
        instructionsRequestDTO.getOilPatches().forEach(patch -> {
            if(isInvalidPatchCoordinate(patch)) {
                throw new IllegalArgumentException();
            }
            oilPatchesList.add(new Coordinate(patch.get(0), patch.get(1))); }
        );
        this.oilPatches = oilPatchesList;
        this.navigationInstructions = instructionsRequestDTO.getNavigationInstructions();
    }

    private boolean isInvalidInput(final InstructionsRequestDTO instructionsRequestDTO) {
        return instructionsRequestDTO.getAreaSize().size() != 2 ||
                instructionsRequestDTO.getStartingPosition().size() != 2;
    }

    private boolean isInvalidPatchCoordinate(
            final List<Integer> patch
    ) {
        return patch.size() != 2;
    }
}
