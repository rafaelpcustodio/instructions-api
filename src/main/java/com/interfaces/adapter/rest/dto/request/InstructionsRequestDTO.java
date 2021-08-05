package com.interfaces.adapter.rest.dto.request;

import com.interfaces.adapter.rest.constraint.ValidateInstruction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@ApiModel(description = "Representation of an instruction request")
public class InstructionsRequestDTO {
    @Size(min = 2, max = 2, message = "Must contain exactly 2 integers inside")
    @NotNull(message = "Must not be null or empty")
    @ApiModelProperty(value = "Area size in x and y coordinate of the desired scenario.", required = true, example = "[1,2]")
    private final List<Integer> areaSize;

    @Size(min = 2, max = 2, message = "Must contain exactly 2 integers inside")
    @NotNull(message = "Must not be null or empty")
    @ApiModelProperty(value = "Starting position in x and y coordinate where the steps will begin.", required = true, example = "[1,2]")
    private final List<Integer> startingPosition;

    @NotEmpty(message = "Must be a collection of valid lists with exactly 2 integers inside")
    @ApiModelProperty(value = "Location in x and y coordinate of the patches present in the scenario.", required = true, example = "[ [1,2], [2,3] ]")
    private final List<@Size(min = 2, max = 2, message = "Values inside oilPatches must contain exactly 2 integers inside")List<Integer>> oilPatches;

    @ValidateInstruction
    @ApiModelProperty(value = "Navigation instructions following cardinal pattern.", required = true, example = "NSWE")
    private final String navigationInstructions;

    public InstructionsRequestDTO(List<Integer> areaSize, List<Integer> startingPosition, List<List<Integer>> oilPatches, String navigationInstructions) {
        this.areaSize = areaSize;
        this.startingPosition = startingPosition;
        this.oilPatches = oilPatches;
        this.navigationInstructions = navigationInstructions;
    }
}
