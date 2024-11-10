package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence;

import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserJpaRepository jpaRepository;
    private final UserPersistenceMapper mapper;
    @Override
    public List<User> findAll() {
        return mapper.toUsers(jpaRepository.findAll());
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toUser);
    }

    @Override
    public User save(User user) {
        return mapper.toUser(jpaRepository.save(mapper.toUserEntity(user)));
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsernameIgnoreCase(username).map(mapper::toUser);
    }


}
