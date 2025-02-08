package org.sbertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SberTestApplication {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SberTestApplication.class, args);
    }
}