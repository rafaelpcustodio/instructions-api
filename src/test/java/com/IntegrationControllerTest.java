package com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationControllerTest {

    private static final MockHttpServletRequestBuilder REQUEST_BUILDER =
            request(HttpMethod.POST, "/instructions")
                    .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .accept(APPLICATION_JSON);

    @Autowired
    private MockMvc mockMvc;

    private static final String VALID_REQUEST =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";


    @Test
    void shouldAcceptValidRequest() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(VALID_REQUEST))
                .andExpect(status().isOk());
    }

    private static final String NOT_VALID_REQUEST_AREA_SIZE_NULL =
            "{\"areaSize\": null, \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_AREA_SIZE_EMPTY =
            "{\"areaSize\": [], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_AREA_SIZE_LIST_WITH_ONE_ELEMENT =
            "{\"areaSize\": [1], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_AREA_SIZE_LIST_WITH_THREE_ELEMENTS =
            "{\"areaSize\": [1,2,3], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String SIZE_ERROR_JSON_FROM_AREA_SIZE =
            "{\"message\": \"Request Error\",\"details\": {\"areaSize\": \"Must contain exactly 2 integers inside\"} }";

    @Test
    void shouldRejectNotValidRequestWithNullAreaSize() throws Exception {
        final String EXPECTED_INVALID_AREA_SIZE_ERROR = "{\"message\": \"Request Error\",\"details\": {\"areaSize\": \"Must not be null or empty\"} }";
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_AREA_SIZE_NULL))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(EXPECTED_INVALID_AREA_SIZE_ERROR));
    }

    @Test
    void shouldRejectNotValidRequestWithEmptyAreaSize() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_AREA_SIZE_EMPTY))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(SIZE_ERROR_JSON_FROM_AREA_SIZE));
    }

    @Test
    void shouldRejectNotValidRequestWithOneElementAreaSize() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_AREA_SIZE_LIST_WITH_ONE_ELEMENT))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(SIZE_ERROR_JSON_FROM_AREA_SIZE));
    }

    @Test
    void shouldRejectNotValidRequestWithThreeElementsAreaSize() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_AREA_SIZE_LIST_WITH_THREE_ELEMENTS))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(SIZE_ERROR_JSON_FROM_AREA_SIZE));
    }

    private static final String NOT_VALID_REQUEST_STARTING_POSITION_NULL =
            "{\"areaSize\": [5, 5], \"startingPosition\": null, \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_STARTING_POSITION_EMPTY =
            "{\"areaSize\": [5, 5], \"startingPosition\": [], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_STARTING_POSITION_WITH_ONE_ELEMENT =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_STARTING_POSITION_WITH_THREE_ELEMENTS =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1,2,3], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String SIZE_ERROR_JSON_FROM_STARTING_POSITION =
            "{\"message\": \"Request Error\",\"details\": {\"startingPosition\": \"Must contain exactly 2 integers inside\"} }";

    private static final String NULL_EMPTY_ERROR_JSON_FROM_STARTING_POSITION =
            "{\"message\": \"Request Error\",\"details\": {\"startingPosition\": \"Must not be null or empty\"} }";

    @Test
    void shouldRejectNotValidRequestNullStartingPosition() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_STARTING_POSITION_NULL))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(NULL_EMPTY_ERROR_JSON_FROM_STARTING_POSITION));
    }

    @Test
    void shouldRejectNotValidRequestEmptyStartingPosition() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_STARTING_POSITION_EMPTY))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(SIZE_ERROR_JSON_FROM_STARTING_POSITION));
    }

    @Test
    void shouldRejectNotValidRequestWithOneElementStartingPosition() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_STARTING_POSITION_WITH_ONE_ELEMENT))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(SIZE_ERROR_JSON_FROM_STARTING_POSITION));
    }

    @Test
    void shouldRejectNotValidRequestWithThreeElementsStartingPosition() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_STARTING_POSITION_WITH_THREE_ELEMENTS))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(SIZE_ERROR_JSON_FROM_STARTING_POSITION));
    }

    private static final String NOT_VALID_REQUEST_OIL_PATCHES_NULL =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": null, \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_OIL_PATCHES_EMPTY_LIST =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_OIL_PATCHES_EMPTY_LIST_INSIDE =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String NOT_VALID_REQUEST_OIL_PATCHES_NOT_VALID_LIST =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1,2], [1]], \"navigationInstructions\": \"NNESEESWNWW\"}";

    private static final String ERROR_OIL_PATCHES_NOT_A_COLLECTION =
            "{\"message\": \"Request Error\",\"details\": {\"oilPatches\": \"Must be a collection of valid lists with exactly 2 integers inside\"} }";

    @Test
    void shouldRejectNotValidRequestNullOilPatches() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_OIL_PATCHES_NULL))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_OIL_PATCHES_NOT_A_COLLECTION));
    }

    @Test
    void shouldRejectNotValidRequestEmptyOilPatches() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_OIL_PATCHES_EMPTY_LIST))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_OIL_PATCHES_NOT_A_COLLECTION));
    }

    @Test
    void shouldRejectNotValidRequestEmptyListOilPatches() throws Exception {
        final String ERROR_JSON_FROM_OIL_PATCHES =
                "{\"message\": \"Request Error\",\"details\": {\"oilPatches[0]\": \"Values inside oilPatches must contain exactly 2 integers inside\"} }";
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_OIL_PATCHES_EMPTY_LIST_INSIDE))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_JSON_FROM_OIL_PATCHES));
    }

    @Test
    void shouldRejectNotValidRequestNotValidListInsideOilPatches() throws Exception {
        final String ERROR_JSON_FROM_OIL_PATCHES =
                "{\"message\": \"Request Error\",\"details\": {\"oilPatches[1]\": \"Values inside oilPatches must contain exactly 2 integers inside\"} }";
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_OIL_PATCHES_NOT_VALID_LIST))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_JSON_FROM_OIL_PATCHES));
    }

    private static final String NOT_VALID_EMPTY_INSTRUCTIONS =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"\"}";

    private static final String NOT_VALID_REQUEST_NULL_INSTRUCTIONS =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": null}";

    private static final String NOT_VALID_REQUEST_WITH_NOT_ALLOWED_LETTERS =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"RT\"}";

    private static final String NOT_VALID_REQUEST_WITH_NOT_ALLOWED_LETTER =
            "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NSWERNS\"}";

    private static final String ERROR_JSON_FROM_INSTRUCTIONS =
            "{\"message\": \"Request Error\",\"details\": {\"navigationInstructions\": \"Invalid format for Navigation Instructions code. It should be a String of the following concatenated values: N or S or E or W\"} }";


    @Test
    void shouldRejectNotValidRequestNotValidEmptyInstructions() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_EMPTY_INSTRUCTIONS))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_JSON_FROM_INSTRUCTIONS));
    }

    @Test
    void shouldRejectNotValidRequestNotValidNullInstructions() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_NULL_INSTRUCTIONS))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_JSON_FROM_INSTRUCTIONS));
    }

    @Test
    void shouldRejectNotValidRequestWithNotValidLettersOnInstructions() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_WITH_NOT_ALLOWED_LETTERS))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_JSON_FROM_INSTRUCTIONS));
    }

    @Test
    void shouldRejectNotValidRequestWithNotValidLetterOnInstructions() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(NOT_VALID_REQUEST_WITH_NOT_ALLOWED_LETTER))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(ERROR_JSON_FROM_INSTRUCTIONS));
    }
}
