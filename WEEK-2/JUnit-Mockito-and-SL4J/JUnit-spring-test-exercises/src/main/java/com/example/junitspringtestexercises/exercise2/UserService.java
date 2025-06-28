package com.example.junitspringtestexercises.exercise2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
 @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                            .orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
