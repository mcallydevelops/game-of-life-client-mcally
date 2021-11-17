package com.mcally;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcally.models.World;
import com.mcally.models.WorldSubmission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameOfLifeServiceTest {

    private WorldService worldService;
    private GameOfLifeService service;

    @BeforeEach
    void setup() {
        this.worldService = mock(WorldService.class);
        this.service = new GameOfLifeService(worldService);
    }

    @Test
    void run() throws Exception {
        when(worldService.generateAndRetrieveWorld()).thenReturn(poorlyGenerateWorld());

        service.run();

        ArgumentCaptor<WorldSubmission> captor = ArgumentCaptor.forClass(WorldSubmission.class);
        verify(worldService).generateAndRetrieveWorld();
        verify(worldService).submitResult(captor.capture());
        WorldSubmission value = captor.getValue();
        assertEquals(2, value.getGenerations().size());
    }

    @Test
    void failingWorldTest() throws Exception {
        when(worldService.generateAndRetrieveWorld()).thenReturn(failingWorld());

        service.run();

    }

    /*
    Would never do this for real
     */
    private World poorlyGenerateWorld() throws Exception {
        String json = "{\n" +
                "  \"id\": \"abc123\",\n" +
                "  \"generationCount\": 2,\n" +
                "  \"size\": 5,\n" +
                "  \"world\": [[1,0,0,0,1],\n" +
                "            [1,0,0,1,1],\n" +
                "            [1,0,1,0,0],\n" +
                "            [1,1,0,1,1],\n" +
                "            [1,0,1,0,0]]\n" +
                "}\n";
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, World.class);
    }

    private World failingWorld() throws Exception {
        String failingWorld = "{\"id\":\"DnQjRbo1\",\"generationCount\":370,\"size\":30,\"world\":[[1,0,0,1,0,1,0,0,0,0,0,1,1,1,0,0,1,0,0,0,1,0,0,1,0,1,0,0,0,0],[1,1,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,0,0,0,1,0,1,1,0,1,1,0,1,1],[1,0,1,1,1,1,1,0,1,0,1,1,1,1,0,1,1,0,1,1,0,0,0,1,1,1,0,0,0,1],[0,1,0,0,0,0,1,1,1,0,0,0,1,0,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,1],[1,0,1,0,1,0,0,1,1,1,1,0,0,0,0,0,1,1,0,0,1,1,0,1,0,1,0,0,1,1],[0,1,0,0,0,1,1,1,0,1,1,1,1,0,1,0,0,0,1,1,1,1,0,1,0,0,0,0,0,0],[0,0,0,0,0,1,1,1,0,0,1,1,1,1,0,1,0,1,0,1,1,0,1,0,1,1,1,1,0,0],[1,0,1,1,1,1,1,1,0,0,1,1,0,0,1,0,0,0,0,0,1,1,0,0,1,0,1,0,0,1],[1,1,1,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,0,1,0],[0,0,1,0,1,0,1,1,0,1,1,1,1,0,0,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1],[1,1,0,1,0,1,1,1,1,0,1,0,1,0,0,0,0,0,0,1,0,0,0,1,1,1,1,1,1,0],[1,1,0,1,1,1,1,0,0,1,1,1,1,1,0,0,1,0,0,1,1,1,0,1,1,1,0,0,0,1],[0,0,1,0,0,0,0,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0,0,1,0,1,0,1,0,0],[0,0,0,1,1,1,1,0,1,1,1,1,0,0,1,0,0,0,1,1,0,1,0,1,0,0,1,1,0,0],[1,1,1,1,1,0,0,1,1,0,0,0,0,0,1,1,0,0,1,1,1,0,0,1,0,1,0,1,0,0],[0,0,0,0,1,0,0,1,1,0,1,0,0,1,0,1,0,1,1,1,1,0,0,0,1,1,0,0,0,1],[1,1,0,1,1,0,1,1,0,1,0,0,1,1,1,0,0,1,0,1,1,1,0,1,1,0,1,1,1,1],[1,0,1,1,0,1,1,0,1,0,0,1,1,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1],[1,0,0,0,0,0,0,0,1,1,0,1,0,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,1],[0,0,1,1,1,1,1,1,1,0,1,0,1,1,0,0,0,1,0,0,0,1,0,0,1,0,0,0,1,0],[0,0,0,0,1,0,1,1,0,1,1,1,1,0,0,1,1,0,0,1,0,0,1,1,0,1,0,1,1,1],[1,0,1,1,1,1,1,0,0,0,1,0,0,0,1,1,1,1,1,0,1,0,0,1,0,1,0,1,0,0],[0,0,1,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,1,1,0],[1,0,1,0,1,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1,0,1,1,1,0,0,0,1,1,1],[1,1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,0,1,1,0,0,0,1,1,0,0,1],[1,1,1,0,0,1,0,0,1,1,0,0,0,0,0,1,1,1,1,0,1,1,0,0,0,1,0,1,0,0],[0,1,0,1,0,1,0,0,1,1,1,0,1,0,1,1,0,1,1,0,1,0,0,0,1,1,1,1,1,0],[1,1,1,1,0,1,0,0,0,1,0,1,1,0,0,0,1,1,0,1,0,0,0,0,1,1,1,0,1,0],[0,0,0,1,1,0,1,1,0,0,1,1,0,0,0,1,1,0,0,1,1,1,0,1,1,1,1,0,1,1],[1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,1,0,0,0,0,0,1,1,0,0,1,1,1,0,1]]}\n";

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(failingWorld, World.class);
    }


    private WorldSubmission poorlyGenerateWorldSubmission() throws Exception {
        String json = "{\n" +
                "  \"id\": \"abc123\",\n" +
                "  \"generationCount\": 2,\n" +
                "  \"generations\": [\n" +
                "    [[1,0,0,0,1],\n" +
                "     [1,0,0,1,1],\n" +
                "     [1,0,1,0,0],\n" +
                "     [1,1,0,1,1],\n" +
                "     [1,0,1,0,0]],\n" +
                "    [[1,0,0,0,1],\n" +
                "     [1,0,0,1,1],\n" +
                "     [1,0,1,0,0],\n" +
                "     [1,1,0,1,1],\n" +
                "     [1,0,1,0,0]]n";
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, WorldSubmission.class);
    }

}