package com.example.spartans.service;

import com.example.spartans.entities.Spartan;
import com.example.spartans.repositories.SpartanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpartanService {
    @Autowired
    private SpartanRepository spartanRepo;

    public Spartan addSpartan(Spartan spartan) {
        try {
            return spartanRepo.save(spartan);
        } catch(IllegalArgumentException e) {
            // in case the given entity is null.
            e.printStackTrace();
            return null;
        }
    }

    public List<Spartan> getAllSpartans(){
        return spartanRepo.findAll();
    }

    public Optional<Spartan> getSpartanById(String id) {
        try {
            return spartanRepo.findById(id);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateSpartan(Spartan spartan){
        String message;

        try {
            if (spartanRepo.findById(spartan.id).isPresent()) {
                spartanRepo.save(spartan);
                message = String.format("%S ID Spartan has been updated.", spartan.id);
            } else {
                message = String.format("No spartan found with %S ID.", spartan.id);
            }

        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            message = "ID must not be null.";
        }

        return message;
    }

    public String deleteSpartanById(String id){
        String message;

        try {
            if (spartanRepo.findById(id).isPresent()) {
                spartanRepo.deleteById(id);
                message = String.format("%S ID Spartan has been deleted.", id);
            } else {
                message = String.format("No spartan found with %S ID.", id);
            }

        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            message = "ID must not be null.";
        }

        return message;
    }

    public List<Spartan> getSpartanByPartialName(String firstName, String lastName){
        return spartanRepo.findByFirstNameContainsOrLastNameContains(firstName, lastName);
    }
}
