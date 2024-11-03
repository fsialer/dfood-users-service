package com.fernando.ms.users.app.dfood_users_service.infrastructure.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {
    USERS_NOT_FOUND("USERS_MS_001", "User not found."),
    USERS_BAD_PARAMETERS("USERS_MS_002", "Invalid parameters for creation student."),
    INTERNAL_SERVER_ERROR("USERS_MS_003", "Internal server error.");

    private final String code;
    private final String message;
}
