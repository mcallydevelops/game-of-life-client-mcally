package com.mcally.models;

import java.util.List;

public class WorldSubmission {

    private String id;
    private Integer generationCount;
    private List<Integer[][]> generations;

    public WorldSubmission() {
    }

    public WorldSubmission(String id, Integer generationCount, List<Integer[][]> generations) {
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

    public Integer getGenerationCount() {
        return generationCount;
    }

    public void setGenerationCount(Integer generationCount) {
        this.generationCount = generationCount;
    }

    public List<Integer[][]> getGenerations() {
        return generations;
    }

    public void setGenerations(List<Integer[][]> generations) {
        this.generations = generations;
    }
}

