package com.example.spartans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.example.spartans.repositories")
@ComponentScan("com.example.spartans.*")
public class SpartansApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartansApplication.class, args);
    }

}
