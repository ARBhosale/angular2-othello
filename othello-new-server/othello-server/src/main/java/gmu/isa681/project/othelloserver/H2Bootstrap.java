package gmu.isa681.project.othelloserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gmu.isa681.project.othelloserver.repository.GameRepository;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;

@Component
public class H2Bootstrap implements CommandLineRunner {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRespository;


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("Creating 3 players");
		/*
		PlayerEntity player1 = new PlayerEntity("A", "B", "uc","p1");
		PlayerEntity player2 = new PlayerEntity("D", "E", "uF","p2");
		PlayerEntity player3 = new PlayerEntity("G", "H", "uI","p3");

		playerRespository.save(player1);
		playerRespository.save(player2);
		playerRespository.save(player3);

		System.out.println("Creating 1 game with first player created");

		GameEntity game1 = new GameEntity(player1.getId());

		System.out.println("A game started by: " + game1.getGameCreatorPlayerId());

		gameRepository.save(game1);
		*/
	}

}
