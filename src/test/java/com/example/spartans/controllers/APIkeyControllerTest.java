package com.example.spartans.controllers;

import com.example.spartans.entities.User;
import com.example.spartans.payload.request.LoginRequest;
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

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

@RunWith(SpringRunner.class)
class APIkeyControllerTest {

    @MockBean
    UserRepository mockRepository;

    @MockBean
    LoginController loginController;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void generateAPIkey() throws Exception {
        APIkeyController APIkeyController = new APIkeyController();

        Method method = APIkeyController.class.getDeclaredMethod("generateAPIkey",
                null);
        method.setAccessible(true);
        byte[] key = (byte[]) method.invoke(APIkeyController, null);

        boolean generated = key.length != 0;
        Assertions.assertTrue(generated);

    }

}