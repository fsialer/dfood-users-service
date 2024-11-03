package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRestMapper {

    List<UserResponse> toUsersResponse(List<User> users);

    UserResponse toUserResponse(User user);


}
