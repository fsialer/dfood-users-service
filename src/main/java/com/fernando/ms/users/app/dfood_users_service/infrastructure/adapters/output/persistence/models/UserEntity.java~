package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.models;

import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwordHash;
    private String passwordSalt;
    private String email;
    private String fullName;
    private TypeUser typeUser;
    private Boolean verify;
    private StatusUser statusUser;
    private LocalDate createAt;
    private LocalDate updateAt;
}
