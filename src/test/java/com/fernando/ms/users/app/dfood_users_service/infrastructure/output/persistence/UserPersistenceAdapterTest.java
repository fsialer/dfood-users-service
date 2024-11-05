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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

    @Test
    void WhenHaveDataUserThenReturnUser(){
        UserEntity userEntity= TestUtils.buildUserEntityMock();
        User user=TestUtils.buildUserMock();
        when(userJpaRepository.findById(anyLong()))
                .thenReturn(Optional.of(userEntity));
        when(userPersistenceMapper.toUser(any(UserEntity.class)))
                .thenReturn(user);

         Optional<User> userResponse=userPersistenceAdapter.findById(1L);

         assertAll(
                 ()->assertNotNull(userResponse),
                 ()->assertTrue(userResponse.isPresent())
         );

        Mockito.verify(userJpaRepository,times(1)).findById(anyLong());
        Mockito.verify(userPersistenceMapper,times(1)).toUser(any(UserEntity.class));
    }

    @Test
    void whenSaveUserThenReturnAnUser(){
        UserEntity userEntity= TestUtils.buildUserEntityMock();
        User userNew=TestUtils.buildUserMock();
        when(userJpaRepository.save(any(UserEntity.class)))
                .thenReturn(userEntity);
        when(userPersistenceMapper.toUser(any(UserEntity.class)))
                .thenReturn(userNew);
        when(userPersistenceMapper.toUserEntity(any(User.class)))
                .thenReturn(userEntity);
        User user=userPersistenceAdapter.save(userNew);

        assertAll(
                ()->assertNotNull(user),
                ()->assertEquals(userNew.getId(),user.getId())
        );
        Mockito.verify(userJpaRepository,times(1)).save(any(UserEntity.class));
        Mockito.verify(userPersistenceMapper,times(1)).toUser(any(UserEntity.class));
        Mockito.verify(userPersistenceMapper,times(1)).toUserEntity(any(User.class));
    }

    @Test
    void whenUsernameExistThenReturnTrue(){
        when(userJpaRepository.existsByUsernameIgnoreCase(anyString())).thenReturn (true);
        assertTrue(userJpaRepository.existsByUsernameIgnoreCase("falex"));
        Mockito.verify(userJpaRepository,times(1)).existsByUsernameIgnoreCase(anyString());
    }

    @Test
    void whenUsernameNotExistThenReturnFalse(){
        when(userJpaRepository.existsByUsernameIgnoreCase(anyString())).thenReturn (false);
        assertFalse(userJpaRepository.existsByUsernameIgnoreCase("falex"));
        Mockito.verify(userJpaRepository,times(1)).existsByUsernameIgnoreCase(anyString());
    }

    @Test
    void whenUserEmailExistThenReturnTrue(){
        when(userJpaRepository.existsByEmailIgnoreCase(anyString())).thenReturn (true);
        assertTrue(userJpaRepository.existsByEmailIgnoreCase("asialer05@hotmail.com"));
        Mockito.verify(userJpaRepository,times(1)).existsByEmailIgnoreCase(anyString());
    }

    @Test
    void whenUserEmailNotExistThenReturnFalse(){
        when(userJpaRepository.existsByEmailIgnoreCase(anyString())).thenReturn (false);
        assertFalse(userJpaRepository.existsByEmailIgnoreCase("asialer05@hotmail.com"));
        Mockito.verify(userJpaRepository,times(1)).existsByEmailIgnoreCase(anyString());
    }

    @Test
    void shouldVoidWhenDeleteAnUser(){
        doNothing().when(userJpaRepository).deleteById(anyLong());
        userPersistenceAdapter.delete(1L);
        Mockito.verify(userJpaRepository,times(1)).deleteById(anyLong());
    }
}
