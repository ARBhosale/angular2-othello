package gmu.isa681.project.othelloserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.repository.GameRepository;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;
import gmu.isa681.project.othelloserver.repository.UserRepository;

@Component
public class H2Bootstrap implements CommandLineRunner{
	
	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	PlayerRepository playerRespository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Getting games");
		gameRepository.save(new GameEntity());
		gameRepository.save(new GameEntity());
		gameRepository.save(new GameEntity());
		
		Iterable<GameEntity> itr = gameRepository.findAll();
		System.out.println("Printing games");
		for(GameEntity game: itr) {
			System.out.println(game.getId());
		}
		
		System.out.println("Creating 3 users");
		playerRespository.save(new PlayerEntity("A","B","uc"));
		playerRespository.save(new PlayerEntity("D","E","uF"));
		playerRespository.save(new PlayerEntity("G","H","uI"));
		
		
		System.out.println("Creating 3 user accounts");
		userRepository.save(new UserEntity("Aniket","aniket123"));
		userRepository.save(new UserEntity("Tyrone","tyrone123"));
		userRepository.save(new UserEntity("Tanmay","tanmay123"));
		
		Iterable<UserEntity> itr1 =userRepository.findAll();
		
		System.out.println("Printing the user accounts");
		for(UserEntity user: itr1) {
			System.out.println(user.getUsername());
		}
		
	}

}
