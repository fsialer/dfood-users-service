package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.repository;

import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
}
