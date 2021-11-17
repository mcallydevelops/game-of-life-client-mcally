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
                        try {
                            int neighbors = 0;
                            //horizontal neighbors
                            neighbors += getHorizontalNeighbors(world, prevGen, i, j, neighbors);
                            //vertical neighbors
                            neighbors += getVerticalNeighbors(world, prevGen, i, j, neighbors);
                            //diagonal neighbors
                            neighbors += getDiagonalNeighbors(world, prevGen, i, j, neighbors);

                            newGen[i][j] = runConwaysRules(prevGen[i][j], neighbors);

                        } catch (Exception exception) {
                            throw exception;
                        }
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

    private int getVerticalNeighbors(World world, int[][] prevGen, int i, int j, int neighbors) {
        if (i == 0) {
            neighbors += prevGen[i + 1][j];
        } else if (i == world.getSize() - 1) {
            neighbors += prevGen[i - 1][j];
        } else {
            neighbors = neighbors + prevGen[i - 1][j] + prevGen[i + 1][j];
        }
        return neighbors;
    }

    private int getHorizontalNeighbors(World world, int[][] prevGen, int i, int j, int neighbors) {
        if (j == 0) {
            neighbors += prevGen[i][j + 1];
        } else if (j == world.getSize() - 1) {
            neighbors += prevGen[i][j - 1];
        } else {
            neighbors = neighbors + prevGen[i][j + 1] + prevGen[i][j - 1];
        }
        return neighbors;
    }

    private int getDiagonalNeighbors(World world, int[][] prevGen, int i, int j, int neighbors) {
        return 0;
    }

}
