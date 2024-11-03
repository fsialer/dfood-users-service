package com.fernando.ms.users.app.dfood_users_service.infrastructure.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {
    USERS_NOT_FOUND("USERS_MS_001", "User not found."),
    USERS_USERNAME_ALREADY_EXISTS("USERS_MS_002", "Username already exists."),
    USERS_EMAIL_USER__ALREADY_EXISTS("USERS_MS_003", "Email already exists."),
    USERS_BAD_PARAMETERS("USERS_MS_003", "Invalid parameters for creation."),
    INTERNAL_SERVER_ERROR("USERS_MS_000", "Internal server error.");

    private final String code;
    private final String message;
}
