package gmu.isa681.project.othelloserver.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	ConversionService conversionService;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AccountResponse>> getAllPlayers() {

		Iterable<PlayerEntity> playerIter = playerRepository.findAll();

		ArrayList<AccountResponse> accounts = new ArrayList();
		for (PlayerEntity player : playerIter) {
			AccountResponse accountResponse = conversionService.convert(player, AccountResponse.class);
			accounts.add(accountResponse);
		}

		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(path = "/{playerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccountResponse> getPlayerById(@PathVariable Long playerId) {
		Optional<PlayerEntity> player = playerRepository.findById(playerId);
		AccountResponse accountResponse = conversionService.convert(player.get(), AccountResponse.class);

		return new ResponseEntity<>(accountResponse, HttpStatus.OK);
	}

}
