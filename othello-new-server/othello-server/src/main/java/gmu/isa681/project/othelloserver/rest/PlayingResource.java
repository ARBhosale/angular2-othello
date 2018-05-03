package gmu.isa681.project.othelloserver.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.entity.game.BoardEntity;
import gmu.isa681.project.othelloserver.exception.BoardNotFoundException;
import gmu.isa681.project.othelloserver.exception.GameNotFoundException;
import gmu.isa681.project.othelloserver.exception.InvalidOperationException;
import gmu.isa681.project.othelloserver.exception.PlayerNotFoundException;
import gmu.isa681.project.othelloserver.model.Board;
import gmu.isa681.project.othelloserver.model.BoardState;
import gmu.isa681.project.othelloserver.model.DiscType;
import gmu.isa681.project.othelloserver.model.request.game.play.JoinGameRequest;
import gmu.isa681.project.othelloserver.model.request.game.play.MoveRequest;
import gmu.isa681.project.othelloserver.model.request.game.play.NewGameRequest;
import gmu.isa681.project.othelloserver.model.response.game.PlayingResponse;
import gmu.isa681.project.othelloserver.repository.GameRepository;
import gmu.isa681.project.othelloserver.repository.PageableGameRepository;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;
import gmu.isa681.project.othelloserver.repository.game.BoardRepository;

@RestController
@RequestMapping(ResourceConstants.GAME_PLAYING_V1)
public class PlayingResource {

	@Autowired
	PageableGameRepository pageableGameRepository;

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<PlayingResponse> getAvailableGames(Pageable pageable) {

		Page<GameEntity> gameEntityList = pageableGameRepository.findAll(pageable);

		return gameEntityList.map((e -> conversionService.convert(e, PlayingResponse.class)));
	}

	@RequestMapping(path = "/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GameEntity> getGameById(@PathVariable Long gameId) {
		GameEntity game = checkForValidGame(gameId);
		return new ResponseEntity<>(game, HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> createNewGame(@RequestBody NewGameRequest newGameRequest) {
		checkForValidPlayer(newGameRequest.getRequestSenderPlayerId());
		GameEntity game = conversionService.convert(newGameRequest, GameEntity.class);
		gameRepository.save(game);
		PlayingResponse playingResponse = conversionService.convert(game, PlayingResponse.class);
		return new ResponseEntity<>(playingResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> joinGame(@RequestBody JoinGameRequest joinGameRequest) {
		GameEntity game = checkForValidGame(joinGameRequest.getGameId());
		if (game.getGameCreatorPlayerId().equals(joinGameRequest.getRequestSenderPlayerId())) {
			throw new InvalidOperationException("Wanna play with yourself?");
		}
		PlayerEntity player = checkForValidPlayer(joinGameRequest.getRequestSenderPlayerId());
		game.addOtherPlayer(player);

		BoardState boardState = new BoardState(game);
		Board board = boardState.initialize(null);

		BoardEntity boardEntity = conversionService.convert(board, BoardEntity.class);
		boardRepository.save(boardEntity);

		game.setCurrentBoardId(boardEntity.getId());
		game.updateScores(board);
		game.setCurrentTurn(DiscType.Black);
		gameRepository.save(game);

		PlayingResponse playingResponse = conversionService.convert(game, PlayingResponse.class);
		return new ResponseEntity<>(playingResponse, HttpStatus.OK);
	}

	@RequestMapping(path = "/play", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> playMove(@RequestBody MoveRequest moveRequest) {
		GameEntity game = checkForValidGame(moveRequest.getGameId());
		PlayerEntity player = checkForValidPlayer(moveRequest.getPlayerId());
		BoardEntity boardEntity = checkForValidBoard(game.getCurrentBoardId());

		BoardState boardState = new BoardState(game);
		Board board = boardState.initialize(boardEntity);
		Board newBoard = boardState.setBoardPiece(moveRequest.getInsertAtRow(), moveRequest.getInsertAtColumn());

		if (null == newBoard) {
			throw new InvalidOperationException(board.getMoveResultMessage());
		} else {
			BoardEntity newBoardEntity = conversionService.convert(newBoard, BoardEntity.class);
			boardRepository.save(newBoardEntity);
			game.setCurrentBoardId(newBoardEntity.getId());
		}

		gameRepository.save(game);
		PlayingResponse playingResponse = conversionService.convert(game, PlayingResponse.class);
		return new ResponseEntity<>(playingResponse, HttpStatus.CREATED);
	}

	private BoardEntity checkForValidBoard(Long boardId) {
		Optional<BoardEntity> board = boardRepository.findById(boardId);
		if (!board.isPresent()) {
			throw new BoardNotFoundException(boardId.toString());
		} else {
			return board.get();
		}
	}

	private PlayerEntity checkForValidPlayer(Long playerId) {
		Optional<PlayerEntity> player = playerRepository.findById(playerId);
		if (!player.isPresent()) {
			throw new PlayerNotFoundException(playerId.toString());
		} else {
			return player.get();
		}
	}

	private GameEntity checkForValidGame(Long gameId) {
		Optional<GameEntity> game = gameRepository.findById(gameId);
		if (!game.isPresent()) {
			throw new GameNotFoundException(gameId.toString());
		} else {
			return game.get();
		}
	}

}
