package gmu.isa681.project.othelloserver.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import gmu.isa681.project.othelloserver.convertor.PlayerEntityToAccountResponseConverter;
import gmu.isa681.project.othelloserver.model.request.PlayerAccountRequest;
import gmu.isa681.project.othelloserver.repository.PagablePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gmu.isa681.project.othelloserver.convertor.PlayerEntityToAccountResponseConverter;
import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.model.response.AccountResponse;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;

@RestController
@RequestMapping(value = ResourceConstants.PLAYER_ACCOUNT_V1)
public class AccountResource {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PagablePlayerRepository pagablePlayerRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "/{playerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccountResponse> getPlayerById(@PathVariable Long playerId) {
		Optional<PlayerEntity> player = playerRepository.findById(playerId);
		AccountResponse accountResponse = conversionService.convert(player.get(), AccountResponse.class);

		return new ResponseEntity<>(accountResponse, HttpStatus.OK);
	}

	@RequestMapping(path="",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<AccountResponse> getUserAccounts(Pageable pageable){

		Page<PlayerEntity> playerEntityList= pagablePlayerRepository.findAll(pageable);

		Converter<PlayerEntity, AccountResponse> converter = new PlayerEntityToAccountResponseConverter();

		return playerEntityList.map((e->converter.convert(e)));
	}


	
	@RequestMapping(path="/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccountResponse> validateUser(
//	public String validateUser(
			@RequestBody
			PlayerAccountRequest playerAccountRequest)
	{
		PlayerEntity playerEntity= conversionService.convert(playerAccountRequest, PlayerEntity.class);
		
		if(!playerEntity.getUserName().matches("[a-zA-Z0-9\\\\._\\\\-]{1,}")){
			return new ResponseEntity <AccountResponse>(HttpStatus.NO_CONTENT);
		}
	//	System.out.println("requested username : "+playerEntity.getUserName());
	//	AccountResponse accountResponse = conversionService.convert(playerEntity, AccountResponse.class); 
	
		
		Iterable<PlayerEntity>itr= playerRepository.findAll();
		Iterator<PlayerEntity> it= itr.iterator();
		
	//	System.out.println("Requested Id: "+playerEntity.getId());
		
		int flag=0;
		PlayerEntity player = new PlayerEntity();
		while(it.hasNext()){
			player=it.next();
		//	System.out.println(player.getUserName());
		//	System.out.println(player.getPassword());
			if(player.getUserName().equals(playerEntity.getUserName()))
				if(player.getPassword().equals(playerEntity.getPassword()))
					{
						flag=1;
						break;
					}
		}
		
		if(flag==0)
			return null;
		
		AccountResponse accountResponse = conversionService.convert(player, AccountResponse.class);
		
	//	System.out.println("Response Id: "+player.getId());
		//return new ResponseEntity<>(accountResponse, HttpStatus.OK);	
	//	System.out.println(new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.ACCEPTED));
	//	System.out.println(accountResponse.getFullName());
		return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.ACCEPTED);
		
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
