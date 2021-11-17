package com.mcally.models;

public class World {

    private String id;
    private int generationCount;
    private int size;
    private int[][] world;

    public World() {
    }

    public World(String id, int generationCount, int size, int[][] world) {
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

    public int getGenerationCount() {
        return generationCount;
    }

    public void setGenerationCount(int generationCount) {
        this.generationCount = generationCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getWorld() {
        return world;
    }

    public void setWorld(int[][] world) {
        this.world = world;
    }
}
