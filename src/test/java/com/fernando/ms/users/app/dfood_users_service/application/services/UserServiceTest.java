package com.fernando.ms.users.app.dfood_users_service.application.services;

import Utils.TestUtils;
import com.fernando.ms.users.app.dfood_users_service.application.ports.output.UserPersistencePort;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
