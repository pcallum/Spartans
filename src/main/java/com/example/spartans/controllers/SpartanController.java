package com.example.spartans.controllers;

import com.example.spartans.entities.Spartan;
import com.example.spartans.service.SpartanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spartans")
public class SpartanController {
    @Autowired
    private SpartanService service;

    @PostMapping()
    public Spartan addSpartan(@RequestBody Spartan spartan) {
        return service.addSpartan(spartan);
    }

    @GetMapping()
    public List<Spartan> getAllSpartans() {
        // authorization
        return service.getAllSpartans();
    }

    @GetMapping("/{id}")
    public Optional<Spartan> getSpartan(@PathVariable String id) {
        return service.getSpartanById(id);
    }

    @PutMapping()
    public String updateSpartan(@RequestBody Spartan spartan) {
        return service.updateSpartan(spartan);
    }

    @DeleteMapping("/{id}")
    public String deleteSpartanById(@PathVariable String id) {
        return service.deleteSpartanById(id);
    }

    // cannot be "/{name}" only, as you cant tell if the string refers to the id or
    // name
    @GetMapping("/name/{name}")
    public List<Spartan> getByPartialName(@PathVariable String name) {
        return service.getSpartanByPartialName(name, name);
    }

}
