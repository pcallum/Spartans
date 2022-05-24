package com.example.spartans.controllers;

import com.example.spartans.entities.Spartan;
import com.example.spartans.repositories.SpartanRepository;
import com.example.spartans.service.SpartanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spartan")
public class SpartanController {
    @Autowired
    private SpartanService service;
//    @Autowired
//    SpartanRepository repository;

    @PostMapping("/add")
    public Spartan addSpartan(@RequestBody Spartan spartan){
//        return repository.save(spartan);
        return service.addSpartan(spartan);
    }

    @GetMapping("/getAll")
    public List<Spartan> getAllSpartans() {
//        return repository.findAll();
        return service.getAllSpartans();
    }

    @GetMapping("/getById/{id}")
    public Optional<Spartan> getSpartan(@PathVariable String id) {
//        return repository.findById(id);
        return service.getSpartanById(id);
    }

    @PutMapping("/update")
    public String updateSpartan(@RequestBody Spartan spartan){
        return service.updateSpartan(spartan);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSpartanById(@PathVariable String id){
        return service.deleteSpartanById(id);
    }
}
