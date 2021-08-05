package com.usecases;

import com.entity.Coordinate;
import com.entity.InstructionsOutput;
import com.interfaces.adapter.rest.dto.request.InstructionsRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Create Instruction output unit test")
@ExtendWith(MockitoExtension.class)
public class CreateInstructionsOutputTest {

    private final CreateInstructionsOutput underTest = new CreateInstructionsOutput(new CreateScenario(), new ValidateStep());

    @Test
    public void shouldCreateExpectedOutputWithTwoPitches() {
        final List<Integer> area = List.of(3,3);
        final List<Integer> startingPosition = List.of(0,0);
        final List<List<Integer>> pitches = List.of(List.of(1,1), List.of(0,0));
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "EN");
        final InstructionsOutput instructionsOutput = underTest.execute(instructionsRequestDTO);
        Assertions.assertEquals(new Coordinate(1,1), instructionsOutput.getFinalPosition());
        Assertions.assertEquals(2, instructionsOutput.getOilPatchesCleaned());
    }

    @Test
    public void shouldCreateExpectedOutputWithOnePitch() {
        final List<Integer> area = List.of(3,3);
        final List<Integer> startingPosition = List.of(0,0);
        final List<List<Integer>> pitches = List.of(List.of(0,1));
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNEE");
        final InstructionsOutput instructionsOutput = underTest.execute(instructionsRequestDTO);
        Assertions.assertEquals(new Coordinate(2,2), instructionsOutput.getFinalPosition());
        Assertions.assertEquals(1, instructionsOutput.getOilPatchesCleaned());
    }

    @Test
    public void shouldCreateExpectedOutputWithNonePitch() {
        final List<Integer> area = List.of(3,3);
        final List<Integer> startingPosition = List.of(0,0);
        final List<List<Integer>> pitches = List.of(List.of(1,0));
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNEE");
        final InstructionsOutput instructionsOutput = underTest.execute(instructionsRequestDTO);
        Assertions.assertEquals(new Coordinate(2,2), instructionsOutput.getFinalPosition());
        Assertions.assertEquals(0, instructionsOutput.getOilPatchesCleaned());
    }

    @Test
    public void shouldCreateExpectedOutputWithOnePitchOnStartingPositionOnly() {
        final List<Integer> area = List.of(3,3);
        final List<Integer> startingPosition = List.of(0,0);
        final List<List<Integer>> pitches = List.of(List.of(0,0));
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNEE");
        final InstructionsOutput instructionsOutput = underTest.execute(instructionsRequestDTO);
        Assertions.assertEquals(new Coordinate(2,2), instructionsOutput.getFinalPosition());
        Assertions.assertEquals(1, instructionsOutput.getOilPatchesCleaned());
    }

    @Test
    public void shouldNotCountPitchesAlreadyCounted() {
        final List<Integer> area = List.of(3,3);
        final List<Integer> startingPosition = List.of(0,0);
        final List<List<Integer>> pitches = List.of(List.of(0,0));
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NS");
        final InstructionsOutput instructionsOutput = underTest.execute(instructionsRequestDTO);
        Assertions.assertEquals(new Coordinate(0,0), instructionsOutput.getFinalPosition());
        Assertions.assertEquals(1, instructionsOutput.getOilPatchesCleaned());
    }

    @Test
    public void shouldBeOnCorrectPlaceAfterManySteps() {
        final List<Integer> area = List.of(5,5);
        final List<Integer> startingPosition = List.of(1,2);
        final List<List<Integer>> pitches = List.of();
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNESEESWNWW");
        final InstructionsOutput instructionsOutput = underTest.execute(instructionsRequestDTO);
        Assertions.assertEquals(new Coordinate(1,3), instructionsOutput.getFinalPosition());
        Assertions.assertEquals(0, instructionsOutput.getOilPatchesCleaned());
    }

    @Test
    public void shouldThrowExceptionIfStepOutOfPathToTheSouth() {
        final List<Integer> area = List.of(5,5);
        final List<Integer> startingPosition = List.of(1,2);
        final List<List<Integer>> pitches = List.of();
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNESEESWNWWSSSS");
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                underTest.execute(instructionsRequestDTO));
    }

    @Test
    public void shouldThrowExceptionIfStepOutOfPathToTheNorth() {
        final List<Integer> area = List.of(5,5);
        final List<Integer> startingPosition = List.of(1,2);
        final List<List<Integer>> pitches = List.of();
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNESEESWNWWNN");
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                underTest.execute(instructionsRequestDTO));
    }

    @Test
    public void shouldThrowExceptionIfStepOutOfPathToTheEast() {
        final List<Integer> area = List.of(5,5);
        final List<Integer> startingPosition = List.of(1,2);
        final List<List<Integer>> pitches = List.of();
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNESEESWNWWEEEE");
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                underTest.execute(instructionsRequestDTO));
    }

    @Test
    public void shouldThrowExceptionIfStepOutOfPathToTheWest() {
        final List<Integer> area = List.of(5,5);
        final List<Integer> startingPosition = List.of(1,2);
        final List<List<Integer>> pitches = List.of();
        final InstructionsRequestDTO instructionsRequestDTO =
                new InstructionsRequestDTO(area, startingPosition, pitches, "NNESEESWNWWWW");
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                underTest.execute(instructionsRequestDTO));
    }
}
