package com.fernando.ms.users.app.dfood_users_service.domain.model;

import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.TypeUser;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class User {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private TypeUser typeUser;
    private Boolean verify;
    private StatusUser statusUser;
}
