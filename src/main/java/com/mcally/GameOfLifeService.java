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

    public String run() throws JsonProcessingException {
        World world = worldService.generateAndRetrieveWorld();

        List<int[][]> result = new ArrayList<>();

        for (int generation = 0; generation < world.getGenerationCount(); ++generation) {
            if (generation == 0) {
                result.add(world.getWorld());
            } else {
                int[][] prevGen = result.get(generation - 1);
                int[][] newGen = new int[world.getSize()][world.getSize()];
                for (int i = 0; i < world.getSize(); ++i) {
                    for (int j = 0; j < world.getSize(); ++j) {
                        int neighbors = 0;
                        for (int iOne = -1; iOne <= 1; ++iOne) {
                            for (int jOne = -1; jOne <= 1; ++jOne) {
                                if (isArrayIndexSafe(iOne + i, jOne + j, world.getSize())) {
                                    neighbors += prevGen[iOne + i][jOne + j];
                                }
                            }
                        }


                        neighbors -= prevGen[i][j];
                        newGen[i][j] = runConwaysRules(prevGen[i][j], neighbors);
                    }
                }
                result.add(newGen);
            }


        }

        return worldService.submitResult(new WorldSubmission(world.getId(), world.getGenerationCount(), result));
    }

    private boolean shouldDieByUnderpopulation(int neighbors, int previousValue) {
        return neighbors < 2 && previousValue == 1;
    }

    private boolean shouldDieByOverpopulation(int neighbors, int previousValue) {
        return neighbors > 3 && previousValue == 1;
    }

    private boolean shouldLiveOn(int neighbors, int previousValue) {
        return (neighbors == 2 || neighbors == 3) && previousValue == 1;
    }

    private boolean shouldComeToLife(int neighbors, int previousValue) {
        return neighbors == 3 && previousValue == 0;
    }

    private int runConwaysRules(int previousValue, int neighbors) {
        if (shouldDieByUnderpopulation(neighbors, previousValue) || shouldDieByOverpopulation(neighbors, previousValue)) {
            return 0;
        } else if (shouldLiveOn(neighbors, previousValue) || shouldComeToLife(neighbors, previousValue)) {
            return 1;
        }
        return 0;
    }

    private boolean isArrayIndexSafe(int i, int j, int size) {
        return i >= 0 && i < size && j >= 0 && j < size;
    }
}
