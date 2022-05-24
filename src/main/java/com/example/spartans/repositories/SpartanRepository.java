package com.example.spartans.repositories;

import com.example.spartans.entities.Spartan;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpartanRepository extends MongoRepository<Spartan, String> {
    List<Spartan> findByCourse(String course);

    List<Spartan> findByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
