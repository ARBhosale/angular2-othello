package gmu.isa681.project.othelloserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException(String playerId) {
		super("The player with id: " + playerId + " was not found");
	}

}
