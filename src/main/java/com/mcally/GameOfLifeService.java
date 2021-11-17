package com.mcally;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcally.models.World;
import com.mcally.models.WorldSubmission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameOfLifeService {
    private final WorldService worldService;

    public GameOfLifeService(WorldService worldService) {
        this.worldService = worldService;
    }

    public void run() throws JsonProcessingException {
        World world = worldService.generateAndRetrieveWorld();

        List<Integer[][]> result = new ArrayList<>();

        for(int i = 0; i < world.getGenerationCount(); ++i) {
            result.add(world.getWorld());
        }

        worldService.submitResult(new WorldSubmission(world.getId(), world.getGenerationCount(), result));

    }
}
