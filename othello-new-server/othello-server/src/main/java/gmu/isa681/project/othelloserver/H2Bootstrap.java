package gmu.isa681.project.othelloserver;

import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.repository.GameRepository;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;

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

		userRepository.save(new UserEntity("talmeid2","hi Tyrone"));
		userRepository.save(new UserEntity("tkarshi","hi Tanmay"));
		userRepository.save(new UserEntity("anirsk","hi Aniket"));

		Iterable<UserEntity> itr1= userRepository.findAll();
		for(UserEntity userEntity:itr1){
			System.out.println(userEntity.getUsername());
		}


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
		playerRespository.save(new PlayerEntity("A","B","uc","pass1"));
		playerRespository.save(new PlayerEntity("D","E","uF", "pass2"));
		playerRespository.save(new PlayerEntity("G","H","uI","pass3"));
	}

}
