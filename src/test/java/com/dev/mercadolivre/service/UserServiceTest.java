package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.UserModel;
import com.dev.mercadolivre.repository.entity.UserEntity;
import com.dev.mercadolivre.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    public UserService userService ;
    @Mock
    public UserRepository userRepository;

    @Test
    public void testSaveUser() {

        String email = "marcosilva@gmail.com";
        UserModel newUser = new UserModel("marcosilva", "marcosilva", email, LocalDateTime.now(), "user");
        when(userRepository.findByEmail(email)).thenReturn(
                Optional.of(
                        new UserEntity(
                                newUser)));
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(newUser);
        });

        verify( userRepository ).findByEmail(anyString());
        verify( userRepository, never() ).save(any());
    }

}