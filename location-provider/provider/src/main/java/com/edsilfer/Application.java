package com.edsilfer;

import com.edsilfer.controller.AdbAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AdbAPI provideAdbAPI() {
        return new AdbAPI();
    }

}