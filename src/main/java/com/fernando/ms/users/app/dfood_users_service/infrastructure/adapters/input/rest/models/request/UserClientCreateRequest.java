package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserClientCreateRequest {
    @NotBlank(message = "Field username cannot be null or blank")
    private String username;
    @NotBlank(message = "Field password cannot be null or blank")
    private String password;
    @NotBlank(message = "Field fullName cannot be null or blank")
    private String fullName;
    @NotBlank(message = "Field email cannot be null or blank")
    @Email(message = "Field email cannot valid")
    private String email;
}
