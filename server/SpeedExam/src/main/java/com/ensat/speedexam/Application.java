package com.ensat.speedexam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        // start().run();
    }

    // For testing
    static CommandLineRunner start(){
        return args -> {
        };

    }

}
