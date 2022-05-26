/* package com.example.spartans.ControllerTest;

import com.example.spartans.controllers.SpartanController;
import com.example.spartans.entities.Spartan;
import com.example.spartans.repositories.SpartanRepository;
import com.example.spartans.service.SpartanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

@SpringBootTest
class SpartanTest {

    @MockBean
    SpartanRepository spartanRepository;

    @MockBean
    SpartanService spartanService;

    @MockBean
    SpartanController spartanController;

    @Test
    void checkSpartanIsFound() {
        Spartan spartan = new Spartan();

        Mockito.when(spartanRepository.findById("628caae9342e5fbc7c4bd383")).thenReturn(Optional.of(spartan));
        Assertions.assertEquals(spartanRepository.findById("628caae9342e5fbc7c4bd383"), Optional.of(spartan));
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
        Assertions.assertEquals("michel", spartan.getFirstName());
    }

    @Test
    void checkSpartanIsEdited() {
        Spartan spartan = new Spartan();
        spartan.setId("628caae9342e5fbc7c4bd383");
        spartan.setFirstName("Adriena updated");
        spartanRepository.save(spartan);
        Spartan spartanUpdated = spartanRepository.findById("628caae9342e5fbc7c4bd383").get();
        Assertions.assertEquals("Adriena updated", spartanUpdated.getFirstName());
    }

    @Test
    void checkSpartanIsRemoved() {
        spartanRepository.deleteById("628caae9342e5fbc7c4bd383");
        boolean result = spartanRepository.findById("628caae9342e5fbc7c4bd383").isEmpty();
        Assertions.assertTrue(result);
    }
} */