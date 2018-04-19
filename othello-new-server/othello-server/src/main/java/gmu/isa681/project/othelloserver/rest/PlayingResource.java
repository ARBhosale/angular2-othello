package gmu.isa681.project.othelloserver.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmu.isa681.project.othelloserver.model.request.PlayingRequest;
import gmu.isa681.project.othelloserver.model.response.PlayingResponse;

@RestController
@RequestMapping(ResourceConstants.GAME_PLAYING_V1)
public class PlayingResource {

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> getAvailableGames() {
		return new ResponseEntity<>(new PlayingResponse(), HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> createNewGame(@RequestBody PlayingRequest playingRequest) {
		return new ResponseEntity<>(new PlayingResponse(), HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> joinGame(@RequestBody PlayingRequest playingRequest) {
		return new ResponseEntity<>(new PlayingResponse(), HttpStatus.OK);
	}
	
}
