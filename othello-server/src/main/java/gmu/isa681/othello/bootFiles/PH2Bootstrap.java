package gmu.isa681.othello.bootFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gmu.isa681.othello.entity.GameEntity;
import gmu.isa681.othello.repository.GameRepository;

@Component
public class PH2Bootstrap implements CommandLineRunner {

//	@Autowired
//	GameRepository gameRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Bootstrapping data: ");
		
//		gameRepository.save(new GameEntity());
		
		System.out.println("Printing out data: ");
		
		
//		Iterable<RoomEntity> itr = roomRepository.findAll();
		
		
//		for(RoomEntity room : itr) {
//			System.out.println(room.getRoomNumber());
//		}
	}

}

