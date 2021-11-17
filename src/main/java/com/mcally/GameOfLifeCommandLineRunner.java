package com.mcally;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GameOfLifeCommandLineRunner implements CommandLineRunner {

    private final GameOfLifeService service;

    public GameOfLifeCommandLineRunner(GameOfLifeService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
//        if(new ArrayList<>(args).contains(""))
//        service.run();
    }
}
