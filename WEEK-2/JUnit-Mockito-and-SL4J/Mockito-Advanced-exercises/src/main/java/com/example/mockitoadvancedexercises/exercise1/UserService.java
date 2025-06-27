package com.example.mockitoadvancedexercises.exercise1;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserEmailByFullname(String fullname) {
        Optional<Users> user = userRepository.findByFullname(fullname);
        if (user.isPresent()) {
            return user.get().getEmail();
        } else {
            return "Not found";
        }
    }
}
