package com.example.spartans.service;

import com.example.spartans.entities.Spartan;
import com.example.spartans.repositories.SpartanRepository;
import com.example.spartans.util.LogDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SpartanService {
    LogDriver log = new LogDriver();
    String className = "SpartanService";
    @Autowired
    private SpartanRepository spartanRepo;

    public Spartan addSpartan(Spartan spartan) {
        try {
            return spartanRepo.save(spartan);
        } catch(IllegalArgumentException e) {
            // in case the given entity is null.
            log.error(className, "something went wrong:", e);
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
            log.error(className, "something went wrong:", e);
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
            log.error(className, "ID must not be null.", e);
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
            log.error(className, message, e);
        }

        return message;
    }

    public List<Spartan> getSpartanByPartialName(String firstName, String lastName){
        return spartanRepo.findByFirstNameContainsOrLastNameContains(firstName, lastName);
    }

    public List<Spartan> getAfterStartDate(Date date){
        Sort sort = Sort.by(Sort.Direction.ASC, "startDate");

        return spartanRepo.findByStartDateAfter(date, sort);
//        return spartanRepo.findByStartDateAfter(date);
    }

    public List<Spartan> findByCourse(String course){
        return spartanRepo.findByCourse(course);
    }
}
