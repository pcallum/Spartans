package com.example.spartans;

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

    @GetMapping("/getByCourse/{course}")
    public List<Spartan> getByCourse(@PathVariable String course) {
        return repository.findByCourse(course);
    }

    @GetMapping("/getAll")
    public List<Spartan> getAllSpartans() {
        return repository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Spartan> getSpartanById(@PathVariable String id) {
        return repository.findById(id);
    }
}
