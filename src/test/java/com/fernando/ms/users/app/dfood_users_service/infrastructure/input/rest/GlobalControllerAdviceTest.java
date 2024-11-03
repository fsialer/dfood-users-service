package com.fernando.ms.users.app.dfood_users_service.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.UserRestAdapter;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.fernando.ms.users.app.dfood_users_service.infrastructure.utils.ErrorCatalog.USERS_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserRestAdapter.class})
public class GlobalControllerAdviceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserInputPort userInputPort;

    @MockBean
    private UserRestMapper userRestMapper;

    @Test
    void whenThrowsProductNotFoundExceptionThenReturnNotFound() throws Exception {
        when(userInputPort.findById(anyLong()))
                .thenThrow(new UserNotFoundException());
        mockMvc.perform(get("/users/{id}",2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result->{
                    ErrorResponse errorResponse=objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertAll(
                            ()->assertEquals(USERS_NOT_FOUND.getCode(),errorResponse.getCode()),
                            ()->assertEquals(FUNCTIONAL,errorResponse.getType()),
                            ()->assertEquals(USERS_NOT_FOUND.getMessage(),errorResponse.getMessage()),
                            ()->assertNotNull(errorResponse.getTimestamp())
                    );
                });
    }

}
