package com.usecases;

import com.entity.PathSituationRecorder;
import com.usecases.exceptions.NotValidStepException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Validate step unit test")
@ExtendWith(MockitoExtension.class)
public class ValidateStepTest {

    @InjectMocks
    private ValidateStep validateStep;

    @Test
    public void shouldAcceptValidToTheNorth() throws NotValidStepException {
        final Character instructionCardinal = 'N';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(1, 0, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
    }

    @Test
    public void shouldAcceptValidToTheSouth() throws NotValidStepException {
        final Character instructionCardinal = 'S';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 1, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
    }

    @Test
    public void shouldAcceptValidToTheEast() throws NotValidStepException {
        final Character instructionCardinal = 'E';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 0, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
    }

    @Test
    public void shouldAcceptValidToTheWest() throws NotValidStepException {
        final Character instructionCardinal = 'W';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(1, 0, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
    }

    @Test
    public void shouldThrowExceptionWithNotValidStepToTheSouth() throws NotValidStepException {
        final Character instructionCardinal = 'S';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 0, scenario);
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario));
    }

    @Test
    public void shouldThrowExceptionWithNotValidStepToTheEast() throws NotValidStepException {
        final Character instructionCardinal = 'E';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(1, 0, scenario);
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario));
    }

    @Test
    public void shouldThrowExceptionWithNotValidStepToTheNorth() throws NotValidStepException {
        final Character instructionCardinal = 'N';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 1, scenario);
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario));
    }

    @Test
    public void shouldThrowExceptionWithNotValidStepToTheWest() throws NotValidStepException {
        final Character instructionCardinal = 'W';
        final int[][] scenario = new int[2][2];
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 1, scenario);
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario));
    }

    @Test
    public void shouldCountPitchCorrectlyWithOnePitch() throws NotValidStepException {
        final Character instructionCardinal = 'N';
        final int[][] scenario = new int[][] {{0,1,0}, {0,0,0}, {0,0,0}};
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 0, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
        Assertions.assertEquals(1, pathSituationRecorder.getPatchesQuantity());
    }

    @Test
    public void shouldCountTwoPitchesCorrectlyOnTheStartPositionAndAfterMove() throws NotValidStepException {
        final Character instructionCardinal = 'N';
        final int[][] scenario = new int[][] {{1,1,0}, {0,0,0}, {0,0,0}};
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 0, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
        Assertions.assertEquals(2, pathSituationRecorder.getPatchesQuantity());
    }

    @Test
    public void shouldNotCountPitchCorrectly() throws NotValidStepException {
        final Character instructionCardinal = 'N';
        final int[][] scenario = new int[][] {{0,0,0}, {1,0,0}, {0,0,0}};
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 0, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
        Assertions.assertEquals(0, pathSituationRecorder.getPatchesQuantity());
    }

    @Test
    public void shouldCountOnlyStartPitchCorrectly() throws NotValidStepException {
        final Character instructionCardinal = 'N';
        final int[][] scenario = new int[][] {{1,0,0}, {0,0,0}, {0,0,0}};
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(0, 0, scenario);
        validateStep.checkNextStep(instructionCardinal, pathSituationRecorder, scenario);
        Assertions.assertEquals(1, pathSituationRecorder.getPatchesQuantity());
    }
}
