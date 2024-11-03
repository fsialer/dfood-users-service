package com.fernando.ms.users.app.dfood_users_service.application.services;

import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserInputPort {

    private final UserPersistencePort userPersistencePort;
    @Override
    public List<User> findAll() {
        return userPersistencePort.findAll();
    }
}
