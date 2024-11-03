package com.fernando.ms.users.app.dfood_users_service.infrastructure.input.rest;

import Utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.UserRestAdapter;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestAdapter.class)
public class UserRestAdapterTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserInputPort userInputPort;

    @MockBean
    private UserRestMapper mapper;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper=new ObjectMapper();
    }

    @Test
    void shouldAListUsersWhenHaveData() throws Exception {

        User user= TestUtils.buildUserMock();
        List<UserResponse> usersResponse= Collections.singletonList(TestUtils.buildUserResponseMock());

        when(userInputPort.findAll())
                .thenReturn(Collections.singletonList(user));

        when(mapper.toUsersResponse(anyList()))
                .thenReturn(usersResponse);

        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(1))
                .andDo(print());

        Mockito.verify(userInputPort,times(1)).findAll();
        Mockito.verify(mapper,times(1)).toUsersResponse(anyList());
    }

    @Test
    void shouldAUsersWhenUserFindById() throws Exception {

        User user= TestUtils.buildUserMock();
        UserResponse usersResponse= TestUtils.buildUserResponseMock();

        when(userInputPort.findById(anyLong()))
                .thenReturn(user);

        when(mapper.toUserResponse(any(User.class)))
                .thenReturn(usersResponse);

        mockMvc.perform(get("/users/{id}",1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").exists())
                .andDo(print());

        Mockito.verify(userInputPort,times(1)).findById(anyLong());
        Mockito.verify(mapper,times(1)).toUserResponse(any(User.class));
    }
}
