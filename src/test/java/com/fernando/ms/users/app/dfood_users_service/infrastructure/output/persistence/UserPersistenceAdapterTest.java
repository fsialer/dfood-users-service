package com.fernando.ms.users.app.dfood_users_service.infrastructure.output.persistence;

import Utils.TestUtils;
import com.fernando.ms.users.app.dfood_users_service.domain.model.User;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.UserPersistenceAdapter;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.models.UserEntity;
import com.fernando.ms.users.app.dfood_users_service.infrastructure.adapters.output.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserPersistenceAdapterTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private UserPersistenceMapper userPersistenceMapper;

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    @Test
    void WhenDataUserExistThenReturnListUsers(){
        UserEntity userEntity= TestUtils.buildUserEntityMock();
        User user=TestUtils.buildUserMock();
        when(userJpaRepository.findAll())
                .thenReturn(Collections.singletonList(userEntity));
        when(userPersistenceMapper.toUsers(anyList()))
                .thenReturn(Collections.singletonList(user));

        List<User> users=userPersistenceAdapter.findAll();

        assertEquals(1,users.size());

        Mockito.verify(userJpaRepository,times(1)).findAll();
        Mockito.verify(userPersistenceMapper,times(1)).toUsers(anyList());
    }

    @Test
    void WhenHaveNotDataUserThenReturnVoid(){
        when(userJpaRepository.findAll())
                .thenReturn(Collections.emptyList());
        when(userPersistenceMapper.toUsers(anyList()))
                .thenReturn(Collections.emptyList());

        List<User> users=userPersistenceAdapter.findAll();

        assertEquals(0,users.size());

        Mockito.verify(userJpaRepository,times(1)).findAll();
        Mockito.verify(userPersistenceMapper,times(1)).toUsers(anyList());
    }
}
