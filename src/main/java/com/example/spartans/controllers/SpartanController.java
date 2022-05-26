package com.example.spartans.controllers;

import com.example.spartans.entities.AdminRequest;
import com.example.spartans.entities.Spartan;
import com.example.spartans.payload.request.LoginRequest;
import com.example.spartans.repositories.UserRepository;
import com.example.spartans.service.SpartanService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> addSpartan(@RequestBody AdminRequest request,
                                             @RequestParam String api) {
        // extracting data from request
        LoginRequest login = request.getLoginRequest();
        Spartan spartan = request.getSpartan();

        // authorization
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, true);

        if (response.getStatusCodeValue() == 200) {
            return parseResult(service.addSpartan(spartan));
        } else {
            return response;
        }
//    public Spartan addSpartan(@RequestBody Spartan spartan){
//        return service.addSpartan(spartan);
    }

    @GetMapping()
    public ResponseEntity<String> getAllSpartans(@RequestBody LoginRequest login,
                                                 @RequestParam String api) {
        // authorization
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, false);

        if (response.getStatusCodeValue() == 200) {
            return parseResult(service.getAllSpartans());
        } else {
            return response;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getSpartan(@PathVariable String id,
                                             @RequestBody LoginRequest login,
                                             @RequestParam String api) {
        // authorization
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, false);

        if (response.getStatusCodeValue() == 200) {
            return parseResult(service.getSpartanById(id));
        } else {
            return response;
        }
    }

    @PutMapping()
    public ResponseEntity<String> updateSpartan(@RequestBody AdminRequest request,
                                @RequestParam String api) {
        LoginRequest login = request.getLoginRequest();
        Spartan spartan = request.getSpartan();

        // authorization
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, true);

        if (response.getStatusCodeValue() == 200) {
            return parseResult(service.updateSpartan(spartan));
        } else {
            return response;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpartanById(@PathVariable String id,
                                                    @RequestBody LoginRequest login,
                                                    @RequestParam String api) {
        // authorization
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, true);

        if (response.getStatusCodeValue() == 200) {
            return parseResult(service.deleteSpartanById(id));
        } else {
            return response;
        }

    }

    // cannot be "/{name}" only, as you cant tell if the string refers to the id or
    // name
    @GetMapping("/name/{name}")
    public ResponseEntity<String> getByPartialName(@PathVariable String name,
                                                   @RequestBody LoginRequest login,
                                                   @RequestParam String api) {
        // authorization
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, false);

        if (response.getStatusCodeValue() == 200) {
            return parseResult(service.getSpartanByPartialName(name, name));
        } else {
            return response;
        }
    }

    @GetMapping("/date/{dateString}")
    public ResponseEntity<String> getAfterStartDate(@PathVariable String dateString,
                                                    @RequestBody LoginRequest login,
                                                    @RequestParam String api) {
        // authorisation
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, false);

        if(response.getStatusCodeValue() == 200) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                return parseResult(service.getAfterStartDate(date));
            } catch (ParseException e) {
                e.printStackTrace();
                // the given string could not be converted to date
                HttpHeaders header = new HttpHeaders();
                header.add("content-type", "application/json");
                return new ResponseEntity<>("{\"message\":\"Invalid date format.\"}", header, HttpStatus.BAD_REQUEST);
            }
        } else {
            return response;
        }
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<String> getByCourse(@PathVariable String course,
                                              @RequestBody LoginRequest login,
                                              @RequestParam String api) {
        // authorisation
        ResponseEntity<String> response = AuthorisationController.checkAuthorisation(login, api, userRepo, false);

        if (response.getStatusCodeValue() == 200) {
            return parseResult(service.findByCourse(course));
        } else {
            return response;
        }
    }

    public static ResponseEntity<String> parseResult(List<Spartan> result){
        String json = new Gson().toJson(result);

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> parseResult(Optional<Spartan> result){
        String json = new Gson().toJson(result.get());

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> parseResult(Spartan result){
        String json = new Gson().toJson(result);

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> parseResult(String message){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        return new ResponseEntity<>(String.format("{\"message\":\"%S\"}", message), headers, HttpStatus.OK);
    }
}
