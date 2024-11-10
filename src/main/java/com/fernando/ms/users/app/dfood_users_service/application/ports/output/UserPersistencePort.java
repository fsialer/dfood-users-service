package com.fernando.ms.users.app.dfood_users_service.application.ports.output;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void delete(Long id);
    Optional<User> findByUsername(String username);
}
