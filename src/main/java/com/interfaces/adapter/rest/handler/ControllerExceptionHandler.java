package com.interfaces.adapter.rest.handler;

import com.interfaces.adapter.rest.handler.error.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice("com.interfaces.adapter.rest")
public class ControllerExceptionHandler {

    private static final String REQUEST_ERROR_MESSAGE = "Request Error";

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    Error handle(final UnexpectedTypeException exception) {

        log.error(exception.getMessage());
        log.error(exception.getLocalizedMessage());
        return new Error(REQUEST_ERROR_MESSAGE, null);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Error handle(final MethodArgumentNotValidException exception) {
        final Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error(exception.getMessage());
        log.error(exception.getLocalizedMessage());
        log.error(exception.getLocalizedMessage());
        return new Error(REQUEST_ERROR_MESSAGE, errors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    Error handle(final HttpMessageNotReadableException exception) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("JSON", "Please retry again with a valid JSON");
        log.error(exception.getMessage());
        log.error(exception.getLocalizedMessage());
        return new Error(REQUEST_ERROR_MESSAGE, errors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    Error handle(final ArrayIndexOutOfBoundsException exception) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("JSON", "The provided navigation instructions is not a valid path for your scenario. Please check the areaSize and retry with a new instruction.");
        log.error(exception.getMessage());
        log.error(exception.getLocalizedMessage());
        return new Error(REQUEST_ERROR_MESSAGE, errors);
    }
}
