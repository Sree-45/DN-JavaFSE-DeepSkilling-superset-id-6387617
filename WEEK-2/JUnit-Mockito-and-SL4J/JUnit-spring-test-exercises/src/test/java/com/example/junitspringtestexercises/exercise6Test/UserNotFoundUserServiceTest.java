package com.example.junitspringtestexercises.exercise6Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.junitspringtestexercises.exercise2.UserRepository;
import com.example.junitspringtestexercises.exercise2.UserService;
import com.example.junitspringtestexercises.exercise6.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserNotFoundUserServiceTest {
@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_UserNotFound_ThrowsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99L));
    }
}
