package gmu.isa681.project.othelloserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidOperationException extends RuntimeException {

	public InvalidOperationException(String message) {
		super(message);
	}

}
