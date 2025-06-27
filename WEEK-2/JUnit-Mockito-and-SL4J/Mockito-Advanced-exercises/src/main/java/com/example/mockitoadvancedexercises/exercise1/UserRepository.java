package com.example.mockitoadvancedexercises.exercise1;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, String>   {

    Optional<Users> findByFullname(String firstname);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPhone(Long phone);
}
