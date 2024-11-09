package com.fernando.ms.users.app.dfood_users_service.application.ports.input;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;

import java.util.List;

public interface UserInputPort {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User update(Long id, User user);
    void delete(Long id);
    User inactive(Long id);
    User changePassword(Long id,User user);
}
