package gmu.isa681.project.othelloserver.rest;

import java.util.Base64;
import java.util.Iterator;
import java.util.Optional;

import gmu.isa681.project.othelloserver.security.SaltedHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmu.isa681.project.othelloserver.convertor.PlayerEntityToAccountResponseConverter;
import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.exception.InvalidCredentialsException;
import gmu.isa681.project.othelloserver.model.request.LoginRequest;
import gmu.isa681.project.othelloserver.model.request.PlayerAccountRequest;
import gmu.isa681.project.othelloserver.model.response.AccountResponse;
import gmu.isa681.project.othelloserver.repository.PagablePlayerRepository;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;

@RestController
@RequestMapping(value = ResourceConstants.PLAYER_ACCOUNT_V1)
@CrossOrigin
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
		System.out.println("Get player ID rest method called");
		AccountResponse accountResponse = conversionService.convert(player.get(), AccountResponse.class);

		return new ResponseEntity<>(accountResponse, HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<AccountResponse> getUserAccounts(Pageable pageable) {

		Page<PlayerEntity> playerEntityList = pagablePlayerRepository.findAll(pageable);

		Converter<PlayerEntity, AccountResponse> converter = new PlayerEntityToAccountResponseConverter();

		return playerEntityList.map((e -> converter.convert(e)));
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccountResponse> createPlayerAccount(@RequestBody PlayerAccountRequest playerAccountRequest) {
		System.out.println("Creating player Account....");
		String errors = "Errors: ";
		Iterable<PlayerEntity> itr = playerRepository.findAll();
		Iterator<PlayerEntity> players = itr.iterator();

		while (players.hasNext()){
			PlayerEntity player = players.next();
			if(player.getUserName().equals(playerAccountRequest.getUserName())){
				errors= errors + "Username already exists, ";
			}
		}

		if (!playerAccountRequest.getUserName().matches("[a-zA-Z0-9]{5,15}")) {
			errors = errors + "Username must have length between 5-15 char, ";
		}
		if (!playerAccountRequest.getPassword().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")) {
			errors = errors + "Password format is invalid, ";
		}
		if (!playerAccountRequest.getFirstName().matches("[a-zA-Z\\\\._\\\\-]+")) {
			errors = errors + "Invalid first name, ";
		}
		if (!playerAccountRequest.getLastName().matches("[a-zA-Z\\\\._\\\\-]+")) {
			errors = errors + "Invalid last name ";
		}
		if (errors != "Errors: ") {
			System.out.println("Failed to create Account due to following reasons: "+errors);
			throw new InvalidCredentialsException(errors);
		}
		PlayerEntity playerEntity = conversionService.convert(playerAccountRequest, PlayerEntity.class);
		playerRepository.save(playerEntity);
		AccountResponse accountResponse = conversionService.convert(playerEntity, AccountResponse.class);
		System.out.println("Account Created");
		return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccountResponse> validateUser(@RequestBody LoginRequest loginRequest) {

		System.out.println("Attempting to Login....");
		Iterable<PlayerEntity> itr = playerRepository.findAll();
		Iterator<PlayerEntity> players = itr.iterator();

		PlayerEntity playerFound = null;
		String errorMessage = "";
		while (players.hasNext()) {
			PlayerEntity player = players.next();
			// System.out.println(player.getUserName());
			// System.out.println(player.getPassword());
			if (player.getUserName().equals(loginRequest.getUserName())) {
				byte[] salt = Base64.getDecoder().decode(player.getSalt());
				String pwd = SaltedHash.getSaltHashedPassword(loginRequest.getPassword(), salt);
				if (pwd != null && pwd.equals(player.getPassword())) {
					playerFound = player;
					break;
				}
				System.out.println("Failed to login. Invalid Credentials.");
				throw new InvalidCredentialsException("Incorrect password");
			}
			if (!players.hasNext()) {
				System.out.println("Failed to login. Invalid Credentials.");
				throw new InvalidCredentialsException("Incorrect user name");
			}

		}

		AccountResponse accountResponse = conversionService.convert(playerFound, AccountResponse.class);
		System.out.println("Login Successful");
		return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.ACCEPTED);

	}

}
