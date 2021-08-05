package com.interfaces.adapter.rest.handler.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@ApiModel(description = "Error response")
@Getter
@ToString
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class Error {
    private final String message;
    private final Map<String, String> details;
}
