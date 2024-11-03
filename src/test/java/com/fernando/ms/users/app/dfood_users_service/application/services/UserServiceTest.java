package com.fernando.ms.users.app.dfood_users_service.application.services;

import Utils.TestUtils;
import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.exceptions.UserNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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
}
