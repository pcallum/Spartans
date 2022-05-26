package com.example.spartans.controllers;

import com.example.spartans.entities.User;
import com.example.spartans.payload.request.LoginRequest;
import com.example.spartans.repositories.UserRepository;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@SpringBootTest
@RunWith(SpringRunner.class)
class APIkeyControllerTest {

    @MockBean
    UserRepository mockRepository;

    @MockBean
    ResponseEntity<String> res = null;

    @Spy
    private LoginController loginController;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void generateAPIkey() throws Exception {
        ApiKeyController APIkeyController = new ApiKeyController();

        Method method = ApiKeyController.class.getDeclaredMethod("generateAPIkey", null);
        method.setAccessible(true);
        byte[] key = (byte[]) method.invoke(APIkeyController, null);

        boolean generated = key.length != 0;
        Assertions.assertTrue(generated);

    }


    @Test
    void getApiKey() throws Exception {
        ApiKeyController APIkeyController = spy(new ApiKeyController());
        APIkeyController.userRepo = applicationContext.getBean(UserRepository.class);
        User user = new User("628ca23c64b79fb389ff9ab3", "ahaglington2@washington.edu", "1YgxO9", null, "admin");
        Mockito.when(mockRepository.findById("1")).thenReturn(Optional.of(user));
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(user.getEmail());
        loginRequest.setPassword(user.getPassword());
        Mockito.when(res.getStatusCodeValue()).thenReturn(201);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        doReturn(Optional.of(user)).when(mockRepository).findByEmail(user.getEmail());

        Mockito.doReturn(201).when(res).getStatusCodeValue();
        Method method = ApiKeyController.class.getDeclaredMethod("getApiKey", String.class, LoginRequest.class);
        method.setAccessible(true);

        ResponseEntity<String> response = (ResponseEntity<String>) method.invoke(APIkeyController, "628ca23c64b79fb389ff9ab3", loginRequest);

        boolean get = response.getStatusCodeValue() == 201 || response.getStatusCodeValue() == 200;
        Assertions.assertTrue(get);
    }


    @Test
    void setApiKey() throws Exception {
        ApiKeyController APIkeyController = spy(new ApiKeyController());
        APIkeyController.userRepo = applicationContext.getBean(UserRepository.class);
        User user = new User("628ca23c64b79fb389ff9ab3", "ahaglington2@washington.edu", "1YgxO9", null, "admin");
        Mockito.when(mockRepository.findById("1")).thenReturn(Optional.of(user));
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(user.getEmail());
        loginRequest.setPassword(user.getPassword());
        Mockito.when(res.getStatusCodeValue()).thenReturn(201);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        doReturn(Optional.of(user)).when(mockRepository).findByEmail(user.getEmail());
        Mockito.doReturn(201).when(res).getStatusCodeValue();
        Method method = ApiKeyController.class.getDeclaredMethod("setApiKey", String.class, LoginRequest.class);
        method.setAccessible(true);
        ResponseEntity<String> response = (ResponseEntity<String>) method.invoke(APIkeyController, "628ca23c64b79fb389ff9ab3", loginRequest);
        System.out.println(response.getStatusCodeValue());
        boolean set = response.getStatusCodeValue() == 201 || response.getStatusCodeValue() == 200;
        Assertions.assertTrue(set);
    }
}