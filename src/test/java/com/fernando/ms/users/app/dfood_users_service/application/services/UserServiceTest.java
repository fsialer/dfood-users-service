package com.fernando.ms.users.app.dfood_users_service.application.services;

import Utils.TestUtils;
import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserEmailAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserUsernameAlreadyExistsException;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnListOfUserWhenListHaveData(){
        when(userPersistencePort.findAll())
                .thenReturn(Collections.singletonList(TestUtils.buildUserMock()));
        List<User> users=userService.findAll();
        assertEquals(1,users.size());
        Mockito.verify(userPersistencePort,times(1)).findAll();
    }

    @Test
    void shouldReturnVoidWhenNotExistUsers(){
        when(userPersistencePort.findAll())
                .thenReturn(Collections.emptyList());
        List<User> users=userService.findAll();
        assertEquals(0,users.size());
        Mockito.verify(userPersistencePort,times(1)).findAll();
    }

    @Test
    void shouldReturnUserWhenExistUserById(){
        User user=TestUtils.buildUserMock();
        when(userPersistencePort.findById(anyLong()))
                .thenReturn(Optional.of(user));
        User userResponse=userService.findById(1L);
        assertEquals(user,userResponse);
        Mockito.verify(userPersistencePort,times(1)).findById(anyLong());
    }

    @Test
    void shouldReturnUserNotFoundWhenNotExistUserById(){
        when(userPersistencePort.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userService.findById(2L));
        Mockito.verify(userPersistencePort,times(1)).findById(anyLong());
    }

    @Test
    void shouldReturnAnUserWhenSaveAnUser(){
        User userNew=TestUtils.buildUserMock();
        when(userPersistencePort.save(any(User.class)))
                .thenReturn(userNew);
        when(userPersistencePort.existsByUsername(anyString()))
                .thenReturn(false);
        when(userPersistencePort.existsByEmail(anyString()))
                .thenReturn(false);
        User user=userService.save(userNew);
        assertEquals(1L,user.getId());
        Mockito.verify(userPersistencePort,times(1)).existsByUsername(anyString());
        Mockito.verify(userPersistencePort,times(1)).existsByEmail(anyString());
        Mockito.verify(userPersistencePort,times(1)).save(any(User.class));
    }

    @Test
    void shouldReturnUserEmailAlreadyExistsExceptionWhenEmailExists(){
        User userNew=TestUtils.buildUserMock();

        when(userPersistencePort.existsByUsername(anyString()))
                .thenReturn(false);
        when(userPersistencePort.existsByEmail(anyString()))
                .thenReturn(true);
        assertThrows(UserEmailAlreadyExistsException.class,()->userService.save(userNew));
        Mockito.verify(userPersistencePort,times(1)).existsByUsername(anyString());
        Mockito.verify(userPersistencePort,times(1)).existsByEmail(anyString());
        Mockito.verify(userPersistencePort,times(0)).save(any(User.class));
    }

    @Test
    void shouldReturnUserUsernameAlreadyExistsExceptionWhenEmailExists(){
        User userNew=TestUtils.buildUserMock();
        when(userPersistencePort.existsByUsername(anyString()))
                .thenReturn(true);
        assertThrows(UserUsernameAlreadyExistsException.class,()->userService.save(userNew));
        Mockito.verify(userPersistencePort,times(1)).existsByUsername(anyString());
        Mockito.verify(userPersistencePort,times(0)).existsByEmail(anyString());
        Mockito.verify(userPersistencePort,times(0)).save(any(User.class));
    }

    @Test
    void shouldReturnAnUserWhenUpdateAnUser(){
        User userNew=TestUtils.buildUserMock();
        User userUpdated=TestUtils.buildUserUpdateMock();
        when(userPersistencePort.save(any(User.class)))
                .thenReturn(userUpdated);
        when(userPersistencePort.findById(anyLong()))
                .thenReturn(Optional.of(userNew));
        when(userPersistencePort.existsByEmail(anyString()))
                .thenReturn(false);
        User user=userService.update(1L,userUpdated);
        assertEquals(userNew.getId(),user.getId());
        assertEquals(userUpdated.getEmail(),user.getEmail());
        Mockito.verify(userPersistencePort,times(1)).existsByEmail(anyString());
        Mockito.verify(userPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(userPersistencePort,times(1)).save(any(User.class));
    }

    @Test
    void shouldReturnUserNotFoundExceptionWhenUpdateAnUser(){
        User userNew=TestUtils.buildUserMock();
        User userUpdated=TestUtils.buildUserUpdateMock();

        when(userPersistencePort.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userService.update(2L,userUpdated));

        Mockito.verify(userPersistencePort,times(0)).existsByEmail(anyString());
        Mockito.verify(userPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(userPersistencePort,times(0)).save(any(User.class));
    }

    @Test
    void shouldReturnEmailAlreadyExistsExceptionWhenUpdateAnUser(){
        User userNew=TestUtils.buildUserMock();
        User userUpdated=TestUtils.buildUserUpdateMock();

        when(userPersistencePort.findById(anyLong()))
                .thenReturn(Optional.of(userNew));
        when(userPersistencePort.existsByEmail(anyString()))
                .thenReturn(true);
        assertThrows(UserEmailAlreadyExistsException.class,()->userService.update(1L,userUpdated));

        Mockito.verify(userPersistencePort,times(1)).existsByEmail(anyString());
        Mockito.verify(userPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(userPersistencePort,times(0)).save(any(User.class));
    }
}
