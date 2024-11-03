package Utils;

import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.TypeUser;
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

}
