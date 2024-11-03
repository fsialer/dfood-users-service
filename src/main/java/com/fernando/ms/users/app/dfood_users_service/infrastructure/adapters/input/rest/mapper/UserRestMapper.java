package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.mapper;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.TypeUser;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserClientCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserDealerCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRestMapper {

    List<UserResponse> toUsersResponse(List<User> users);

    UserResponse toUserResponse(User user);

    @Mapping(target = "typeUser", expression = "java(mapTypeUserClient())")
    @Mapping(target = "statusUser", expression = "java(mapStatusUser())")
    @Mapping(target = "verify", expression = "java(mapVerify())")
    User toUser(UserClientCreateRequest user);

    @Mapping(target = "typeUser", expression = "java(mapTypeUserDealer())")
    @Mapping(target = "statusUser", expression = "java(mapStatusUser())")
    @Mapping(target = "verify", expression = "java(mapVerify())")
    User toUser(UserDealerCreateRequest user);

    default TypeUser mapTypeUserClient() {
        return TypeUser.CLIENT;
    }

    default StatusUser mapStatusUser() {
        return StatusUser.REGISTERED;
    }

    default Boolean mapVerify() {
        return false;
    }



    default TypeUser mapTypeUserDealer() {
        return TypeUser.DEALER;
    }


}
