package com.example.junitspringtestexercises.exercise7Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.example.junitspringtestexercises.exercise2.User;
import com.example.junitspringtestexercises.exercise2.UserRepository;

@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(new User(1L, "Alice"));
        userRepository.save(new User(2L, "Bob"));
    }

    @Test
    void testFindByName() {
        List<User> users = userRepository.findByName("Alice");
        assertEquals(1, users.size());
        assertEquals("Alice", users.get(0).getName());
    }

}
