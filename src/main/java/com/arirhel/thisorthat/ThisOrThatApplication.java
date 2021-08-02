package com.arirhel.thisorthat;

import com.arirhel.thisorthat.service.ShuffleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
// todo when adding frontend, remove application.properties:spring.main.web-application-type=NONE
public class ThisOrThatApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ThisOrThatApplication.class);

    @Autowired
    ShuffleService shuffleService;

    public static void main(String[] args) {
        log.info("Starting ThisOrThatApplication");
        SpringApplication.run(ThisOrThatApplication.class, args);
        log.info("Application finished");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running ThisOrThatApplication with default dataset: Choice Cuisines");
        final List<String> cuisines = Arrays.asList("Italian", "Thai", "French", "Japanese", "Lebanese", "Spanish",
                "German", "Korean", "West African", "Australian", "Caribbean", "Greek", "Filipino", "Vietnamese",
                "Indian", "Mexican", "Indonesian", "Brazilian", "Chinese", "American");
        shuffleService.shuffle(cuisines);
        log.info(cuisines.toString());
    }
}
