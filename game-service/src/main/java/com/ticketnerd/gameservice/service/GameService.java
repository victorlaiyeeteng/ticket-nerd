package com.ticketnerd.gameservice.service;

import com.ticketnerd.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public boolean isAvailable(String code) {
        return gameRepository.findByCode(code).isPresent();
    }
}
