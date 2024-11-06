package Utils;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.TypeUser;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserClientCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserDealerCreateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.UserUpdateRequest;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.models.UserEntity;

public class TestUtils {
    public static User buildUserMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .password("123456")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static User buildUserInactiveMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .password("123456")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.INACTIVE)
                .build();
    }

    public static UserUpdateRequest buildUserUpdateRequestMock(){
        return UserUpdateRequest.builder()

                .email("asialer05@hotmail.com")
                .build();
    }



    public static User buildUserUpdateMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .password("123456")
                .email("asialer06@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static UserEntity buildUserEntityMock(){
        return UserEntity.builder()
                .id(1L)
                .username("falex")
                .password("123456")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static UserResponse buildUserResponseMock(){
        return UserResponse.builder()
                .id(1L)
                .username("falex")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static UserClientCreateRequest buildUserClientCreateRequestMock(){
        return UserClientCreateRequest.builder()
                .username("falex")
                .email("asialer05@hotmail.com")
                .password("password")
                .build();
    }

    public static UserDealerCreateRequest buildUserDealerCreateRequestMock(){
        return UserDealerCreateRequest.builder()
                .username("falex")
                .email("asialer05@hotmail.com")
                .password("password")
                .build();
    }

    public static UserResponse buildUserDealerResponseMock(){
        return UserResponse.builder()
                .id(1L)
                .username("falex")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.DEALER)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static UserResponse buildUserInactiveResponseMock(){
        return UserResponse.builder()
                .id(1L)
                .username("falex")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.DEALER)
                .verify(false)
                .statusUser(StatusUser.INACTIVE)
                .build();
    }



}
