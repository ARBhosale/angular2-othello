package gmu.isa681.othello.web;
import org.springframework.web.bind.annotation.CrossOrigin;
 import java.util.Optional;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
 import org.springframework.http.ResponseEntity;

 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;

 import gmu.isa681.othello.entity.GameEntity;
 import gmu.isa681.othello.model.request.GameRequest;
 import gmu.isa681.othello.model.response.GameResponse;
 import gmu.isa681.othello.repository.GameRepository;
 import org.springframework.web.bind.annotation.RestController;



@RestController
public class MyRestController {

//	@Autowired
//	GameRepository gameRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
 @RequestMapping("/api/hello")
 public String greet() {
  return "Hello from the other side!!!";
 }

//	@RequestMapping(path="", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
// public ResponseEntity<GameResponse> createGame(@RequestBody GameRequest gameRequest){
//		
//		Long playerBlackId = gameRequest.getPlayerId();
//		
//		
//		
//		gameRepository.
//	}
	
	@RequestMapping(path="/getGame/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GameResponse> getGame(
			@PathVariable Long gameId) {
		
//		Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
//		if (null == gameEntity) {
//			return new GameEntity();
//		}
		
//		return new GameEntity();
		
		
		
		return new ResponseEntity<>(new GameResponse(),HttpStatus.OK);
		
	}
}
