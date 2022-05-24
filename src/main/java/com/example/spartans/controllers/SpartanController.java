package com.example.spartans.controllers;
import com.example.spartans.entities.Spartan;
import com.example.spartans.repositories.SpartanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spartan")
public class SpartanController {
    @Autowired
    SpartanRepository repository;

    @PostMapping("/add")
    public Spartan addSpartan(@RequestBody Spartan spartan){
        return repository.save(spartan);
    }

    @GetMapping("/getAll")
    public List<Spartan> getAllSpartans() {
        return repository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Spartan> getSpartan(@PathVariable String id) {
        return repository.findById(id);
    }
}
