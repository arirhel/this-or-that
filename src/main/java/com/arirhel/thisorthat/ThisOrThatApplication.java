package com.arirhel.thisorthat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// todo when adding frontend, remove application.properties:spring.main.web-application-type=NONE
public class ThisOrThatApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ThisOrThatApplication.class);

    public static void main(String[] args) {
        log.info("Starting ThisOrThatApplication");
        SpringApplication.run(ThisOrThatApplication.class, args);
        log.info("Application finished");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running ThisOrThatApplication");
    }
}
