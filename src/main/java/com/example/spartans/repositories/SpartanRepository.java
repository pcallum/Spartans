package com.example.spartans.repositories;

import com.example.spartans.entities.Spartan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpartanRepository extends MongoRepository<Spartan, String> {
}
