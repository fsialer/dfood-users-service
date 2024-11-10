package com.fernando.ms.users.app.dfood_users_service.application.services;

import com.fernando.ms.users.app.dfood_users_service.application.ports.input.UserInputPort;
import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.CredentialFailedException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserEmailAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserUsernameAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.domain.model.enums.StatusUser;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserInputPort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordUtils passwordUtils;
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
        String salt= passwordUtils.generateSalt();
        user.setPasswordHash(passwordUtils.hashPassword(user.getPassword(),salt));
        user.setPasswordSalt(salt);
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
                    userUpdated.setFullName(user.getFullName());
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

    @Override
    public User inactive(Long id) {
        return userPersistencePort.findById(id)
                .map(userUpdated->{
                    userUpdated.setStatusUser(StatusUser.INACTIVE);
                    return userPersistencePort.save(userUpdated);

                }).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User changePassword(Long id,User user) {
        return userPersistencePort.findById(id)
                .map(userUpdated->{
                    if(!passwordUtils.validatePassword(user.getPassword(),userUpdated.getPasswordSalt(),userUpdated.getPasswordHash())){
                        throw new CredentialFailedException();
                    }
                    String salt= passwordUtils.generateSalt();
                    userUpdated.setPasswordHash(passwordUtils.hashPassword(user.getNewPassword(),salt));
                    userUpdated.setPasswordSalt(salt);
                    return userPersistencePort.save(userUpdated);
                }).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User authentication(User user) {
        return userPersistencePort.findByUsername(user.getUsername())
                .map(userObtained->{
                    if(!passwordUtils.validatePassword(user.getPassword(),userObtained.getPasswordSalt(),userObtained.getPasswordHash())){
                        throw new CredentialFailedException();
                    }
                    return userObtained;
                })
                .orElseThrow(UserNotFoundException::new);
    }


}
