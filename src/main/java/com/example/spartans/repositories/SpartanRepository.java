package com.example.spartans.repositories;

import com.example.spartans.entities.Spartan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpartanRepository extends MongoRepository<Spartan, String> {
    List<Spartan> findByCourse(String course);
}
