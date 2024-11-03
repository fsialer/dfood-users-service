package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.mapper;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.models.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    List<User> toUsers(List<UserEntity> users);
}
