package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest;

import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserClientCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserDealerCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserUpdateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestAdapter {
    private final UserInputPort userInputPort;
    private final UserRestMapper userRestMapper;
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok().body(userRestMapper.toUsersResponse(userInputPort.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(userRestMapper.toUserResponse(userInputPort.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserClientCreateRequest userClientCreateRequest){
        UserResponse user=userRestMapper.toUserResponse(userInputPort.save(userRestMapper.toUser(userClientCreateRequest)));
        return ResponseEntity.created(URI.create("/users/".concat(user.getId().toString()))).body(user);
    }

    @PostMapping("/dealer")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserDealerCreateRequest userDealerCreateRequest){

        UserResponse user=userRestMapper.toUserResponse(userInputPort.save(userRestMapper.toUser(userDealerCreateRequest)));
        return ResponseEntity.created(URI.create("/users/".concat(user.getId().toString()))).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        UserResponse user=userRestMapper.toUserResponse(userInputPort.update(id,userRestMapper.toUser(userUpdateRequest)));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userInputPort.delete(id);
    }
}
