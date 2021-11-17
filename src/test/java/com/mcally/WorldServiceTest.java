package com.mcally;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcally.models.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WorldServiceTest {


    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private WorldService worldService;


    @BeforeEach
    void setup() {
        this.restTemplate = mock(RestTemplate.class);
        this.objectMapper = new ObjectMapper();
        this.worldService = new WorldService(restTemplate, objectMapper);
    }

    @Test
    void generateAndRetrieveWorld() throws Exception {
        when(restTemplate.getForObject(anyString(), any())).thenReturn("{\n" +
                "  \"id\": \"abc123\",\n" +
                "  \"generationCount\": 5,\n" +
                "  \"size\": 5,\n" +
                "  \"world\": [[1,2,3,4,5],\n" +
                "            [1,0,0,1,1],\n" +
                "            [1,0,1,0,0],\n" +
                "            [1,1,0,1,1],\n" +
                "            [1,0,1,0,0]]\n" +
                "}\n");

        World world = worldService.generateAndRetrieveWorld();

        assertEquals("abc123", world.getId());
        assertEquals(5, world.getGenerationCount());
        assertEquals(5, world.getSize());
        assertEquals(1, world.getWorld()[0][0]);
        assertEquals(2, world.getWorld()[0][1]);
        assertEquals(3, world.getWorld()[0][2]);
        assertEquals(4, world.getWorld()[0][3]);
        assertEquals(5, world.getWorld()[0][4]);
    }


}