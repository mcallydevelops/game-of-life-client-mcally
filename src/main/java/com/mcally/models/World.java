package com.mcally.models;

public class World {

    private String id;
    private Integer generationCount;
    private Integer size;
    private Integer[][] world;

    public World() {
    }

    public World(String id, Integer generationCount, Integer size, Integer[][] world) {
        this.id = id;
        this.generationCount = generationCount;
        this.size = size;
        this.world = world;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer[][] getWorld() {
        return world;
    }

    public void setWorld(Integer[][] world) {
        this.world = world;
    }
}
