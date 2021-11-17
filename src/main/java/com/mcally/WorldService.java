package com.mcally;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcally.models.World;
import com.mcally.models.WorldSubmission;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorldService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WorldService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public World generateAndRetrieveWorld() throws JsonProcessingException {
        String rawResult = restTemplate.getForObject("https://game-of-life-service-ai3nmiz7aa-uc.a.run.app/world", String.class);
        return objectMapper.readValue(rawResult, World.class);
    }

    public String submitResult(WorldSubmission worldSubmission) {
        String result = restTemplate.postForObject("https://game-of-life-service-ai3nmiz7aa-uc.a.run.app/results", worldSubmission, String.class);
        return result;
    }
}
