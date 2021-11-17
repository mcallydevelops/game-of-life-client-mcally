package com.mcally;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameOfLifeController {


    private final GameOfLifeService gameOfLifeService;

    public GameOfLifeController(GameOfLifeService gameOfLifeService) {
        this.gameOfLifeService = gameOfLifeService;
    }

    @GetMapping
    public ResponseEntity<String> runGame() throws JsonProcessingException {
        String result = gameOfLifeService.run();
        String url = result.substring(22);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url);
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }
}
