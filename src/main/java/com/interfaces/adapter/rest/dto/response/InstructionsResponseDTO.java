package com.interfaces.adapter.rest.dto.response;

import com.entity.InstructionsOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import java.util.List;

@Getter
@ApiModel(value = "Instructions response", description = "The information received after checking the instructions request.")
public class InstructionsResponseDTO {
      @ApiModelProperty(value = "Final position after making the cardinal steps in x and y coordinates.", required = true, example = "[1,2]")
      private final List<Integer> finalPosition;
      @ApiModelProperty(value = "The amount of oil patches found and cleaned during the instruction steps.", required = true, example = "2")
      private final Integer oilPatchesCleaned;

      public InstructionsResponseDTO(final InstructionsOutput instructionsOutput) {
            this.finalPosition = List.of(instructionsOutput.getFinalPosition().getX(), instructionsOutput.getFinalPosition().getY());
            this.oilPatchesCleaned = instructionsOutput.getOilPatchesCleaned();
      }
}
