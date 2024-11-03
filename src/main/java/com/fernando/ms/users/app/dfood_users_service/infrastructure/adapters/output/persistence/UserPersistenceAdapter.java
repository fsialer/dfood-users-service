package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence;

import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserJpaRepository jpaRepository;
    private final UserPersistenceMapper mapper;
    @Override
    public List<User> findAll() {
        return mapper.toUsers(jpaRepository.findAll());
    }
}
