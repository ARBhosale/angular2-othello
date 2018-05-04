package gmu.isa681.project.othelloserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException(String errorMessage) {
		super(errorMessage);
	}

}
