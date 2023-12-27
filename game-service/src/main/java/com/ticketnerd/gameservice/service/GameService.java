package com.ticketnerd.gameservice.service;

import com.ticketnerd.gameservice.dto.GameResponse;
import com.ticketnerd.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameResponse> isAvailable(List<String> code) {
        return gameRepository.findByCodeIn(code).stream()
                .map(game ->
                    GameResponse.builder()
                            .code(game.getCode())
                            .isAvailable(game.getTotalSeats() > 0)
                            .build()
                ).toList();
    }
}
