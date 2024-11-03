package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response;

import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.enums.ErrorType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String code;
    private ErrorType type; //Functional, System
    private String message;
    private List<String> details;
    private String timestamp;
}
