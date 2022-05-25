package com.example.spartans.controllers;

import com.example.spartans.entities.User;
import com.example.spartans.repositories.UserRepository;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class APIkeyControllerTest {

    @MockBean
    UserRepository mockRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void generateAPIkey() {
        APIkeyController APIkeyController = new APIkeyController();
        byte[] key = APIkeyController.generateAPIkey();
        boolean generated = key.length != 0;
        Assertions.assertTrue(generated);

    }

    @Test
    void setApiKey() {
        User user = new User("1", "em@m.com", "pass", null, "admin");
        Mockito.when(mockRepository.findById("1")).thenReturn(Optional.of(user));


        APIkeyController apIkeyController=new APIkeyController();
        apIkeyController.userRepo=applicationContext.getBean(UserRepository.class);
        ResponseEntity<String> response = apIkeyController.setApiKey("1");
        boolean set= response.getStatusCode() == HttpStatus.ACCEPTED;
        Assertions.assertTrue(set);
    }
}