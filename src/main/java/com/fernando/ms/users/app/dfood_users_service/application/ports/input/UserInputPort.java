package com.fernando.ms.users.app.dfood_users_service.application.ports.input;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;

import java.util.List;

public interface UserInputPort {

    List<User> findAll();
    User findById(Long id);
}
