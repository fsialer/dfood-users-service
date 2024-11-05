package com.fernando.ms.users.app.dfood_users_service.application.services;

import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserEmailAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserUsernameAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserInputPort {

    private final UserPersistencePort userPersistencePort;
    @Override
    public List<User> findAll() {
        return userPersistencePort.findAll();
    }

    @Override
    public User findById(Long id) {
        return userPersistencePort.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User save(User user) {
        if(userPersistencePort.existsByUsername(user.getUsername())){
            throw new UserUsernameAlreadyExistsException(user.getUsername());
        }
        if(userPersistencePort.existsByEmail(user.getEmail())){
            throw new UserEmailAlreadyExistsException(user.getEmail());
        }

        return userPersistencePort.save(user);
    }

    @Override
    public User update(Long id, User user) {
        return userPersistencePort.findById(id)
                .map(userUpdated->{
                    if(!userUpdated.getEmail().equals(user.getEmail())){
                        if(userPersistencePort.existsByEmail(user.getEmail())){
                            throw new UserEmailAlreadyExistsException(user.getEmail());
                        }
                        userUpdated.setEmail(user.getEmail());
                    }
                    return userPersistencePort.save(userUpdated);

                }).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        if(userPersistencePort.findById(id).isEmpty()){
            throw new UserNotFoundException();
        }
        userPersistencePort.delete(id);
    }
}
