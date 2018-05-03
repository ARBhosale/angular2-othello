package gmu.isa681.project.othelloserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException {

	public GameNotFoundException(String gameId) {
		super("The game with id: " + gameId + " was not found");
	}

}
