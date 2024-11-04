package com.fernando.ms.users.app.dfood_users_service.infrastructure.input.rest;

import Utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.UserRestAdapter;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserClientCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserDealerCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserUpdateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void shouldReturnAnUserClientWhenSaveUser() throws Exception {

        User user= TestUtils.buildUserMock();
        UserClientCreateRequest userClientCreateRequest = TestUtils.buildUserClientCreateRequestMock();
        UserResponse usersResponse= TestUtils.buildUserResponseMock();

        when(userInputPort.save(any(User.class)))
                .thenReturn(user);

        when(mapper.toUser(any(UserClientCreateRequest.class)))
                .thenReturn(user);

        when(mapper.toUserResponse(any(User.class)))
                .thenReturn(usersResponse);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userClientCreateRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

        Mockito.verify(userInputPort,times(1)).save(any(User.class));
        Mockito.verify(mapper,times(1)).toUser(any(UserClientCreateRequest.class));
        Mockito.verify(mapper,times(1)).toUserResponse(any(User.class));
    }

    @Test
    void shouldReturnAnUserDealerWhenSaveUser() throws Exception {

        User user= TestUtils.buildUserMock();
        UserDealerCreateRequest userDealerCreateRequest = TestUtils.buildUserDealerCreateRequestMock();
        UserResponse usersResponse= TestUtils.buildUserDealerResponseMock();

        when(userInputPort.save(any(User.class)))
                .thenReturn(user);

        when(mapper.toUser(any(UserDealerCreateRequest.class)))
                .thenReturn(user);

        when(mapper.toUserResponse(any(User.class)))
                .thenReturn(usersResponse);

        mockMvc.perform(post("/users/dealer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDealerCreateRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

        Mockito.verify(userInputPort,times(1)).save(any(User.class));
        Mockito.verify(mapper,times(1)).toUser(any(UserDealerCreateRequest.class));
        Mockito.verify(mapper,times(1)).toUserResponse(any(User.class));
    }

    @Test
    void shouldReturnAnUserWhenUpdateUser() throws Exception {

        User user= TestUtils.buildUserMock();
        UserUpdateRequest userUpdateRequest = TestUtils.buildUserUpdateRequestMock();
        UserResponse usersResponse= TestUtils.buildUserDealerResponseMock();

        when(userInputPort.update(anyLong(),any(User.class)))
                .thenReturn(user);

        when(mapper.toUser(any(UserUpdateRequest.class)))
                .thenReturn(user);

        when(mapper.toUserResponse(any(User.class)))
                .thenReturn(usersResponse);

        mockMvc.perform(put("/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

        Mockito.verify(userInputPort,times(1)).update(anyLong(),any(User.class));
        Mockito.verify(mapper,times(1)).toUser(any(UserUpdateRequest.class));
        Mockito.verify(mapper,times(1)).toUserResponse(any(User.class));
    }
}
