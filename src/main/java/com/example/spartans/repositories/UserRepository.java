package com.example.spartans.repositories;

import java.util.Optional;
import com.example.spartans.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
