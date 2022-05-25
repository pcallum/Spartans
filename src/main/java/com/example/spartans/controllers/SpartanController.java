package com.example.spartans.controllers;

import com.example.spartans.entities.Spartan;
import com.example.spartans.payload.request.LoginRequest;
import com.example.spartans.repositories.UserRepository;
import com.example.spartans.service.SpartanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spartans")
public class SpartanController {
    @Autowired
    private SpartanService service;

    @Autowired
    private UserRepository userRepo;

    @PostMapping()
    public Spartan addSpartan(@RequestBody Spartan spartan) {
        return service.addSpartan(spartan);
    }

    @GetMapping()
    public List<Spartan> getAllSpartans(@RequestBody LoginRequest loginRequest,
            @RequestParam String api) {
        // authorization
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(loginRequest, api, userRepo);
        System.out.println(response);

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

    @GetMapping("/date/{dateString}")
    public List<Spartan> getAfterStartDate(@PathVariable String dateString) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return service.getAfterStartDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
            // the given string could not be converted to date
        }

        return null;
    }

    @GetMapping("/course/{course}")
    public List<Spartan> getByCourse(@PathVariable String course) {
        return service.findByCourse(course);
    }
}
