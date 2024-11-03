package com.fernando.ms.users.app.dfood_users_service.infrastructure.input.rest;

import Utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserEmailAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserUsernameAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.UserRestAdapter;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserClientCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.enums.ErrorType.SYSTEM;
import static com.fernando.ms.users.app.dfood_users_service.infrastructure.utils.ErrorCatalog.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @Test
    void whenThrowsMethodArgumentNotValidExceptionThenReturnBadRequest() throws Exception {

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ErrorResponse errorResponse = objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertAll(
                            () -> assertThat(errorResponse.getCode()).isEqualTo(USERS_BAD_PARAMETERS.getCode()),
                            () -> assertThat(errorResponse.getType()).isEqualTo(FUNCTIONAL),
                            () -> assertThat(errorResponse.getMessage()).isEqualTo(USERS_BAD_PARAMETERS.getMessage()),
                            () -> assertThat(errorResponse.getDetails()).isNotNull(),
                            () -> assertThat(errorResponse.getTimestamp()).isNotNull()
                    );
                })
                .andDo(print());
    }

    @Test
    void whenThrowsUserEmailUserAlreadyExistsExceptionThenReturnBadRequest() throws Exception {
        UserClientCreateRequest userRequest = TestUtils.buildUserClientCreateRequestMock();
        User user = TestUtils.buildUserMock();

        when(userRestMapper.toUser(any(UserClientCreateRequest.class)))
                .thenReturn(user);

        when(userInputPort.save(any(User.class)))
                .thenThrow(new UserEmailAlreadyExistsException(userRequest.getEmail()));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ErrorResponse errorResponse = objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertEquals(USERS_EMAIL_USER__ALREADY_EXISTS.getCode(), errorResponse.getCode());
                    assertEquals(FUNCTIONAL, errorResponse.getType());
                    assertEquals(USERS_EMAIL_USER__ALREADY_EXISTS.getMessage(), errorResponse.getMessage());
                    assertEquals("User email: " + userRequest.getEmail() + " already exists!", errorResponse.getDetails().get(0));
                    assertNotNull(errorResponse.getTimestamp());
                })
                .andDo(print());
    }

    @Test
    void whenThrowsUserUsernameAlreadyExistsExceptionThenReturnBadRequest() throws Exception {
        UserClientCreateRequest userRequest = TestUtils.buildUserClientCreateRequestMock();
        User user = TestUtils.buildUserMock();

        when(userRestMapper.toUser(any(UserClientCreateRequest.class)))
                .thenReturn(user);

        when(userInputPort.save(any(User.class)))
                .thenThrow(new UserUsernameAlreadyExistsException(userRequest.getUsername()));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ErrorResponse errorResponse = objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertEquals(USERS_USERNAME_ALREADY_EXISTS.getCode(), errorResponse.getCode());
                    assertEquals(FUNCTIONAL, errorResponse.getType());
                    assertEquals(USERS_USERNAME_ALREADY_EXISTS.getMessage(), errorResponse.getMessage());
                    assertEquals("User username: " + userRequest.getUsername() + " already exists!", errorResponse.getDetails().get(0));
                    assertNotNull(errorResponse.getTimestamp());
                })
                .andDo(print());
    }

    @Test
    void whenThrowsGenericException_thenReturnInternalServerErrorResponse() throws Exception {
        when(userInputPort.findAll())
                .thenThrow(new RuntimeException("Generic error"));

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> {
                    ErrorResponse errorResponse = objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertThat(errorResponse.getCode()).isEqualTo(INTERNAL_SERVER_ERROR.getCode());
                    assertThat(errorResponse.getType()).isEqualTo(SYSTEM);
                    assertThat(errorResponse.getMessage()).isEqualTo(INTERNAL_SERVER_ERROR.getMessage());
                    assertThat(errorResponse.getDetails().get(0)).isEqualTo("Generic error");
                    assertThat(errorResponse.getTimestamp()).isNotNull();
                })
                .andDo(print());
    }

}
