package com.fernando.ms.users.app.dfood_users_service.application.ports.output;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;

import java.util.List;

public interface UserPersistencePort {
    List<User> findAll();
}
