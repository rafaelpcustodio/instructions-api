package com.usecases;

import com.entity.Coordinate;
import com.entity.InstructionsInput;
import com.entity.PathSituationRecorder;
import com.interfaces.adapter.rest.dto.request.InstructionsRequestDTO;
import com.entity.InstructionsOutput;
import com.usecases.exceptions.NotValidStepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateInstructionsOutput {

    @Autowired
    private final CreateScenario createScenario;

    @Autowired
    private final ValidateStep validateStep;

    public CreateInstructionsOutput(final CreateScenario createScenario, ValidateStep validateStep) {
        this.createScenario = createScenario;
        this.validateStep = validateStep;
    }

    public InstructionsOutput execute(
            final InstructionsRequestDTO instructionsRequestDTO
    ) throws NotValidStepException {
        final InstructionsInput instructionsInput = new InstructionsInput(instructionsRequestDTO);
        final int[][] scenario = createScenario.execute(instructionsInput.getAreaSize(), instructionsInput.getOilPatches());
        return this.findOutput(instructionsInput.getNavigationInstructions(), scenario, instructionsInput.getStartingPosition());
    }

    private InstructionsOutput findOutput(
            final String navigationInstructions,
            final int[][] scenario,
            final Coordinate startingPosition
    ) throws NotValidStepException {
        final PathSituationRecorder pathSituationRecorder = new PathSituationRecorder(startingPosition.getX(), startingPosition.getY(), scenario);
        for (int currentCardinal = 0; currentCardinal < navigationInstructions.toCharArray().length; currentCardinal++) {
            validateStep.checkNextStep(
                    navigationInstructions.toUpperCase().charAt(currentCardinal),
                    pathSituationRecorder,
                    scenario
            );
        }
        return new InstructionsOutput(pathSituationRecorder.getLastPosition(), pathSituationRecorder.getPatchesQuantity());
    }
}
