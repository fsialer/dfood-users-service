package com.fernando.ms.users.app.dfood_users_service.domain.exceptions;

public class UserUsernameAlreadyExistsException extends RuntimeException{
    public UserUsernameAlreadyExistsException(String username){
        super("User username: " + username + " already exists!");
    }
}
