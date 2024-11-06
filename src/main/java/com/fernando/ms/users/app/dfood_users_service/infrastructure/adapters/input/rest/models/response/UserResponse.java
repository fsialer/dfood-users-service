package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response;

import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.TypeUser;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private TypeUser typeUser;
    private Boolean verify;
    private StatusUser statusUser;
}
