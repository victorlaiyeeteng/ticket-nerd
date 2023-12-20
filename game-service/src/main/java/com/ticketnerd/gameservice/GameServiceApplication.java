package com.ticketnerd.gameservice;

import com.ticketnerd.gameservice.model.Game;
import com.ticketnerd.gameservice.repository.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GameServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(GameRepository gameRepository) {
		return args -> {
			Game game = new Game();
			game.setCode("Lakers 25/12/2023");
			game.setTotalSeats(1000);

			Game game1 = new Game();
			game1.setCode("Nets 30/12/2023");
			game1.setTotalSeats(800);

			gameRepository.save(game);
			gameRepository.save(game1);
		};
	}
}
