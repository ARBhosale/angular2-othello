package gmu.isa681.project.othelloserver.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import gmu.isa681.project.othelloserver.convertor.PlayerEntityToAccountResponseConverter;
import gmu.isa681.project.othelloserver.model.request.PlayerAccountRequest;
import gmu.isa681.project.othelloserver.repository.PagablePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.model.response.AccountResponse;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;

@RestController
@RequestMapping(value=ResourceConstants.PLAYER_ACCOUNT_V1)
public class AccountResource {
	
	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PagablePlayerRepository pagablePlayerRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path="",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<AccountResponse> getUserAccounts(
			@RequestParam(value="Username")
					String Username,
			@RequestParam(value="password")
					String password, Pageable pageable){

		Page<PlayerEntity> playerEntityList= pagablePlayerRepository.findAll(pageable);

		Converter<PlayerEntity, AccountResponse> converter = new PlayerEntityToAccountResponseConverter();

		return playerEntityList.map((e->converter.convert(e)));
	}

	@RequestMapping(path="/{userId}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayerEntity> getUserById(
			@PathVariable
					Long userId){
		Optional<PlayerEntity> playerEntity = playerRepository.findById(userId);
		

		return new ResponseEntity<>(playerEntity.get(),HttpStatus.CREATED);
	}

	@RequestMapping(path="",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccountResponse> createPlayerAccount(
			@RequestBody
					PlayerAccountRequest playerAccountRequest){
		PlayerEntity playerEntity= conversionService.convert(playerAccountRequest, PlayerEntity.class);
		if(!playerEntity.getUserName().matches("[a-zA-Z0-9\\\\._\\\\-]{3,}")){
			return new ResponseEntity <>(HttpStatus.NO_CONTENT);
		}
		playerRepository.save(playerEntity);
		AccountResponse accountResponse= conversionService.convert(playerEntity, AccountResponse.class);
		return new ResponseEntity <>(accountResponse, HttpStatus.CREATED);
	}


}
