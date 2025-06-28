package com.example.junitspringtestexercises.exercise6;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }

}
