package com.ticketnerd.gameservice.repository;

import com.ticketnerd.gameservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByCodeIn(List<String> code);
}
