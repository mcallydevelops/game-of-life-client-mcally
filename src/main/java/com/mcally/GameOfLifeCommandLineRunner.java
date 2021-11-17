package com.mcally;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameOfLifeCommandLineRunner implements CommandLineRunner {

    private final GameOfLifeService service;

    public GameOfLifeCommandLineRunner(GameOfLifeService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        service.run();
    }
}
