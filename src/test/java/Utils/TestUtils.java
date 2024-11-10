package Utils;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.TypeUser;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request.*;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.UserResponse;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.models.UserEntity;

public class TestUtils {
    public static User buildUserMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .passwordSalt("5S6p2d0eku23V4mltiAPFg==")
                .passwordHash("lxAE6YPCLwmntfINARfhSzW/l/9n7O5/OXFa2b9cQu4=")
                .fullName("Fernando Sialer")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static User buildUserAuthMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .password("admin")
                .build();
    }

    public static UserAuthRequest buildUserAuthRequestMock(){
        return UserAuthRequest.builder()
                .username("falex")
                .password("admin")
                .build();
    }


    public static ChangePasswordRequest buildUserChangePasswordMock(){
        return ChangePasswordRequest.builder()
                .password("fdfdf")
                .newPassword("fdfdf")
                .confirmPassword("fdfdf")
                .build();
    }

    public static User buildUserWithHashAndSaltMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .passwordSalt("5S6p2d0eku23V4mltiAPFg==")
                .passwordHash("lxAE6YPCLwmntfINARfhSzW/l/9n7O5/OXFa2b9cQu4=")
                .fullName("Fernando Sialer")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static User buildUserWithinHashAndSaltMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .passwordHash("fdfdf")
                .fullName("Fernando Sialer")
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
                .passwordSalt("5S6p2d0eku23V4mltiAPFg==")
                .passwordHash("lxAE6YPCLwmntfINARfhSzW/l/9n7O5/OXFa2b9cQu4=")
                .fullName("Fernando Sialer")
                .email("asialer05@hotmail.com")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.INACTIVE)
                .build();
    }

    public static UserUpdateRequest buildUserUpdateRequestMock(){
        return UserUpdateRequest.builder()
                .fullName("Fernando Sialer")
                .email("asialer05@hotmail.com")
                .build();
    }



    public static User buildUserUpdateMock(){
        return User.builder()
                .id(1L)
                .username("falex")
                .passwordSalt("5S6p2d0eku23V4mltiAPFg==")
                .passwordHash("lxAE6YPCLwmntfINARfhSzW/l/9n7O5/OXFa2b9cQu4=")
                .email("asialer06@hotmail.com")
                .fullName("Fernando Sialer")
                .typeUser(TypeUser.CLIENT)
                .verify(false)
                .statusUser(StatusUser.REGISTERED)
                .build();
    }

    public static UserEntity buildUserEntityMock(){
        return UserEntity.builder()
                .id(1L)
                .username("falex")
                .passwordSalt("5S6p2d0eku23V4mltiAPFg==")
                .passwordHash("lxAE6YPCLwmntfINARfhSzW/l/9n7O5/OXFa2b9cQu4=")
                .fullName("Fernando Sialer")
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
                .fullName("Fernando Sialer")
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
                .fullName("Fernando Sialer")
                .build();
    }

    public static UserDealerCreateRequest buildUserDealerCreateRequestMock(){
        return UserDealerCreateRequest.builder()
                .username("falex")
                .email("asialer05@hotmail.com")
                .password("password")
                .fullName("Fernando Sialer")
                .build();
    }

    public static UserResponse buildUserDealerResponseMock(){
        return UserResponse.builder()
                .id(1L)
                .username("falex")
                .email("asialer05@hotmail.com")
                .fullName("Fernando Sialer")
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
                .fullName("Fernando Sialer")
                .typeUser(TypeUser.DEALER)
                .verify(false)
                .statusUser(StatusUser.INACTIVE)
                .build();
    }



}
