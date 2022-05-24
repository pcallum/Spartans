package com.example.spartans.service;

import com.example.spartans.entities.Spartan;
import com.example.spartans.repositories.SpartanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpartanService {
    @Autowired
    private SpartanRepository spartanRepo;

    public Spartan addSpartan(Spartan spartan) {
        Spartan savedRecord = new Spartan();

        try {
            savedRecord = spartanRepo.save(spartan);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            // in case the given entity is null.
        }

        return savedRecord;
    }

    public List<Spartan> getAllSpartans(){
        return spartanRepo.findAll();
    }

    public Optional<Spartan> getSpartanById(String id) {
        return spartanRepo.findById(id);
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
                message = String.format("%S ID Spartan has been updated.", id);
            } else {
                message = String.format("No spartan found with %S ID.", id);
            }

        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            //id - must not be null.
            message = "ID must not be null.";
        }

        return message;
    }
}
