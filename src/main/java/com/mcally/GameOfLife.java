package com.mcally;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameOfLife implements CommandLineRunner {

    private final WorldService worldService;

    public GameOfLife(WorldService worldService) {
        this.worldService = worldService;
    }

    @Override
    public void run(String... args) throws Exception {
        worldService.generateAndRetrieveWorld();
    }
}
