package com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest;


import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserEmailAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserUsernameAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Collections;

import static com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.enums.ErrorType.FUNCTIONAL;

import static com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.input.rest.models.enums.ErrorType.SYSTEM;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserUsernameAlreadyExistsException.class)
    public ErrorResponse handleUserUsernameAlreadyExistsException(UserUsernameAlreadyExistsException e) {
        return ErrorResponse.builder()
                .code(USERS_USERNAME_ALREADY_EXISTS.getCode())
                .type(FUNCTIONAL)
                .message(USERS_USERNAME_ALREADY_EXISTS.getMessage())
                .timestamp(LocalDate.now().toString())
                .details(Collections.singletonList(e.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ErrorResponse handleUserEmailUserAlreadyExistsException(UserEmailAlreadyExistsException e) {
        return ErrorResponse.builder()
                .code(USERS_EMAIL_USER__ALREADY_EXISTS.getCode())
                .type(FUNCTIONAL)
                .message(USERS_EMAIL_USER__ALREADY_EXISTS.getMessage())
                .timestamp(LocalDate.now().toString())
                .details(Collections.singletonList(e.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ErrorResponse.builder()
                .code(USERS_BAD_PARAMETERS.getCode())
                .type(FUNCTIONAL)
                .message(USERS_BAD_PARAMETERS.getMessage())
                .details(bindingResult.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {

        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .type(SYSTEM)
                .message(INTERNAL_SERVER_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
    }
}
