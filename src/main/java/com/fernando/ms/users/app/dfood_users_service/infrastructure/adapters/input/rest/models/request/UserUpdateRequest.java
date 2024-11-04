package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    @NotBlank(message = "Field email cannot be null or blank")
    @Email(message = "Field email cannot valid")
    private String email;
}
