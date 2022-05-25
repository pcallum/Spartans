package com.example.spartans.ControllerTest;


import com.example.spartans.entities.Spartan;
import com.example.spartans.repositories.SpartanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@DataMongoTest
//@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class SpartanTest {

    @Autowired
    private SpartanRepository spartanRepository;

    @Test
    public void checkSpartanIsFound() {
        //Spartan spartan = new Spartan();
        //spartanRepository.save(spartan);
        Spartan result = spartanRepository.findById("628caae9342e5fbc7c4bd383").get();
        Assertions.assertEquals("Kelner", result.getLastName());
    }

    @Test
    void addSpartan() {
        Spartan spartan = new Spartan();
        spartan.setFirstName("michel");
        spartan.setLastName("jean");
        spartan.setCourse("c#");
        spartan.setEmail("michel@gmail.com");
        spartan.setStartDate("05/12/1999");
        spartanRepository.save(spartan);

    }

    @Test
    public  void checkSpartanIsEdited() {
        Spartan spartan = new Spartan();
        spartan.setFirstName("michel");
        spartan.setLastName("jean");
        spartan.setCourse("c#");
        spartan.setEmail("michel@gmail.com");
        spartan.setStartDate("05/12/1999");
        spartanRepository.save(spartan);
        Spartan spartanUpdated = spartanRepository.findById("").get();
        Assertions.assertEquals("Updated jean", spartanUpdated.getLastName());
    }

    @Test
    public void checkSpartanIsRemoved() {
        spartanRepository.deleteById("628caae9342e5fbc7c4bd383");
        boolean result = spartanRepository.findById("628caae9342e5fbc7c4bd383").isEmpty();
        Assertions.assertTrue(result);
    }

}
