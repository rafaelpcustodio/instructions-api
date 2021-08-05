package com.usecases;

import com.entity.Coordinate;
import com.entity.PathSituationRecorder;
import com.usecases.exceptions.NotValidStepException;
import org.springframework.stereotype.Service;

@Service
public class ValidateStep {

    private static final Integer PITCH_IS_PRESENT = 1;
    private static final Integer PITCH_IS_NOT_PRESENT = 0;

    public void checkNextStep(
            final Character instructionCardinal,
            final PathSituationRecorder pathSituationRecorder,
            final int[][] scenario
    ) throws NotValidStepException {
        final Coordinate nextStep = pathSituationRecorder.getNextCoordinate(instructionCardinal);
        final Integer nextX = nextStep.getX();
        final Integer nextY = nextStep.getY();
        if(isNotValidStep(nextX, nextY, scenario)) {
            throw new NotValidStepException();
        }
        if(existPatch(nextX, nextY, scenario)) {
            pathSituationRecorder.countPatch(new Coordinate(nextX, nextY));
            scenario[nextX][nextY] = PITCH_IS_NOT_PRESENT;
        }
    }

    private boolean existPatch(
            final Integer nextX,
            final Integer nextY,
            final int[][] scenario
    ) {
        return scenario[nextX][nextY] == PITCH_IS_PRESENT;
    }

    private boolean isNotValidStep(
            final Integer nextX,
            final Integer nextY,
            final int[][] scenario
    ) {
        return nextX >= scenario.length || nextY >= scenario[nextX].length;
    }
}
