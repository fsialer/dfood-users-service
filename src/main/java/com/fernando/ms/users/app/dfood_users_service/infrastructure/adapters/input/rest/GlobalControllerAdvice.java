package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest;


import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

import static com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.enums.ErrorType.FUNCTIONAL;

import static com.fernando.ms.users.app.dfood_users_service.infrastructure.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleStudentNotFoundException() {
        return ErrorResponse.builder()
                .code(USERS_NOT_FOUND.getCode())
                .type(FUNCTIONAL)
                .message(USERS_NOT_FOUND.getMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }
}
