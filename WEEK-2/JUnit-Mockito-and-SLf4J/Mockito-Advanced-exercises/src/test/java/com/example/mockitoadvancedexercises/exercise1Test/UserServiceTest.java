package com.example.mockitoadvancedexercises.exercise1Test;


import org.junit.jupiter.api.Test;

import com.example.mockitoadvancedexercises.exercise1.UserRepository;
import com.example.mockitoadvancedexercises.exercise1.UserService;
import com.example.mockitoadvancedexercises.exercise1.Users;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class UserServiceTest {
    @Test
    public void testGetUserEmailByFullname() {
        UserRepository mockRepo = mock(UserRepository.class);

        Users mockUser = new Users("1", "sree", "sree@gmail.com", 1234567890L);
        when(mockRepo.findByFullname("sree")).thenReturn(Optional.of(mockUser));

        UserService service = new UserService(mockRepo);
        String email = service.getUserEmailByFullname("sree");

        assertEquals("sree@gmail.com", email);
    }

    @Test
    public void testGetUserEmailByFullname_NotFound() {
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.findByFullname("sreeshanth")).thenReturn(Optional.empty());

        UserService service = new UserService(mockRepo);
        String email = service.getUserEmailByFullname("sreeshanth");

        assertEquals("Not found", email);
    }
}
