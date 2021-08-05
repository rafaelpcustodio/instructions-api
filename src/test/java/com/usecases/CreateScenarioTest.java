package com.usecases;

import com.entity.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

@DisplayName("Create scenario unit test")
@ExtendWith(MockitoExtension.class)
public class CreateScenarioTest {

    @InjectMocks
    private CreateScenario createScenario;

    @Test
    public void shouldCreateExpectedScenarioCorrectlyWithSameXYDimensions() {
        final Coordinate area = new Coordinate(3,3);
        final List<Coordinate> pitches = List.of(new Coordinate(1,1), new Coordinate(0,0));
        final int[][] createdScenario = createScenario.execute(area, pitches);
        Assertions.assertEquals(1, createdScenario[0][0]);
        Assertions.assertEquals(1, createdScenario[1][1]);
        Assertions.assertEquals(0, createdScenario[0][1]);
        Assertions.assertEquals(3, createdScenario.length);
        Assertions.assertEquals(3, createdScenario[0].length);
    }

    @Test
    public void shouldCreateExpectedScenarioCorrectlyWithBiggerYDimension() {
        final Coordinate area = new Coordinate(2,3);
        final List<Coordinate> pitches = List.of(new Coordinate(1,0), new Coordinate(1,2));
        final int[][] createdScenario = createScenario.execute(area, pitches);
        Assertions.assertEquals(1, createdScenario[1][0]);
        Assertions.assertEquals(1, createdScenario[1][2]);
        Assertions.assertEquals(0, createdScenario[1][1]);
        Assertions.assertEquals(2, createdScenario.length);
        Assertions.assertEquals(3, createdScenario[0].length);
    }

    @Test
    public void shouldCreateExpectedScenarioCorrectlyWithBiggerXDimension() {
        final Coordinate area = new Coordinate(3,2);
        final List<Coordinate> pitches = List.of(new Coordinate(1,0), new Coordinate(2,1));
        final int[][] createdScenario = createScenario.execute(area, pitches);
        Assertions.assertEquals(1, createdScenario[1][0]);
        Assertions.assertEquals(1, createdScenario[2][1]);
        Assertions.assertEquals(0, createdScenario[0][0]);
        Assertions.assertEquals(3, createdScenario.length);
        Assertions.assertEquals(2, createdScenario[0].length);
    }
}
