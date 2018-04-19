package gmu.isa681.project.othelloserver.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.model.response.AccountResponse;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;

@RestController
@RequestMapping(value=ResourceConstants.PLAYER_ACCOUNT_V1)
public class AccountResource {
	
	@Autowired
	PlayerRepository playerRepository;
	
	@RequestMapping(path="", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccountResponse> getAllPlayers() {
		PlayerEntity playerEntity = new PlayerEntity("x","y","uz");
		playerRepository.save(playerEntity);
		
		Optional<PlayerEntity> optionalPlayerEntity2 = playerRepository.findById(playerEntity.getId());
		PlayerEntity playerEntity2 = optionalPlayerEntity2.get();
		
		AccountResponse accountResponse = new AccountResponse(playerEntity2.getUserName(), playerEntity2.getFirstName());
		return new ResponseEntity<>(accountResponse, HttpStatus.OK);
	}

}
