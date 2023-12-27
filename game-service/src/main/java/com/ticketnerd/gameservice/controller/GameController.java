package com.ticketnerd.gameservice.controller;

import com.ticketnerd.gameservice.dto.GameResponse;
import com.ticketnerd.gameservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameResponse> isAvailable(@RequestParam List<String> code) {
        return gameService.isAvailable(code);
    }

}
