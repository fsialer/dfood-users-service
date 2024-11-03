package com.fernando.ms.users.app.dfood_users_service.domain.exceptions;

public class UserEmailAlreadyExistsException extends RuntimeException{

    public UserEmailAlreadyExistsException(String email){
        super("User email: " + email + " already exists!");
    }

}
