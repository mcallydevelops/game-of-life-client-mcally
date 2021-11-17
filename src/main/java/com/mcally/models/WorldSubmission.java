package com.mcally.models;

import java.util.List;

public class WorldSubmission {

    private String id;
    private int generationCount;
    private List<int[][]> generations;

    public WorldSubmission() {
    }

    public WorldSubmission(String id, int generationCount, List<int[][]> generations) {
        this.id = id;
        this.generationCount = generationCount;
        this.generations = generations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public void setGenerationCount(int generationCount) {
        this.generationCount = generationCount;
    }

    public List<int[][]> getGenerations() {
        return generations;
    }

    public void setGenerations(List<int[][]> generations) {
        this.generations = generations;
    }
}

