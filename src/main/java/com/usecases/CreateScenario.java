package com.usecases;

import com.entity.Coordinate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreateScenario {

    private static final Integer PITCH_IS_PRESENT = 1;

    public int[][] execute(
            final Coordinate area,
            final List<Coordinate> oilPatches
    ) {
        final int[][] scenario = new int[area.getX()][area.getY()];
        oilPatches.forEach(patch -> scenario[patch.getX()][patch.getY()] = PITCH_IS_PRESENT);
        return scenario;
    }
}
