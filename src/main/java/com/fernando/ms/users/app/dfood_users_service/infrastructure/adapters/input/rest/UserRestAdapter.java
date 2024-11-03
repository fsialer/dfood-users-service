package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest;

import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestAdapter {
    private final UserInputPort userInputPort;
    private final UserRestMapper userRestMapper;
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok().body(userRestMapper.toUsersResponse(userInputPort.findAll()));
    }
}
