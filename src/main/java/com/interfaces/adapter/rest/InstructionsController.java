package com.interfaces.adapter.rest;

import com.interfaces.adapter.rest.dto.request.InstructionsRequestDTO;
import com.interfaces.adapter.rest.dto.response.InstructionsResponseDTO;
import com.entity.InstructionsOutput;
import com.usecases.CreateInstructionsOutput;
import com.usecases.exceptions.NotValidStepException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/instructions")
@Api(value = "INSTRUCTIONS", tags = {"INSTRUCTIONS"})
public class InstructionsController {

    @Autowired
    private CreateInstructionsOutput createInstructionsOutput;

    @ApiOperation(
            value = "Location creation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instructions created"),
            @ApiResponse(code = 400, message = "Invalid request info"),
    })
    @PostMapping
    InstructionsResponseDTO createInstructions(
            @RequestBody @Valid final InstructionsRequestDTO instructionsRequestDTO
    ) throws NotValidStepException {
        final InstructionsOutput instructionsOutput = createInstructionsOutput.execute(instructionsRequestDTO);
        return new InstructionsResponseDTO(instructionsOutput);
    }
}
