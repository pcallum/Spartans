package com.example.spartans.repositories;

import com.example.spartans.entities.Spartan;
import com.example.spartans.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
