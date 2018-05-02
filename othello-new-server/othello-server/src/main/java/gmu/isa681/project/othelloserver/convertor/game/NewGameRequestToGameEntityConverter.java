package gmu.isa681.project.othelloserver.convertor.game;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.model.Links;
import gmu.isa681.project.othelloserver.model.Self;
import gmu.isa681.project.othelloserver.model.request.game.play.NewGameRequest;
import gmu.isa681.project.othelloserver.model.response.game.PlayingResponse;
import gmu.isa681.project.othelloserver.rest.ResourceConstants;

public class NewGameRequestToGameEntityConverter implements Converter<NewGameRequest, GameEntity> {

	@Override
	public GameEntity convert(NewGameRequest newGameRequest) {
		// TODO Auto-generated method stub

		GameEntity game = new GameEntity(newGameRequest.getRequestSenderPlayerId());

		if (newGameRequest.isBlack()) {
			game.setPlayerBlackId(newGameRequest.getRequestSenderPlayerId());
		} else {
			game.setPlayerWhiteId(newGameRequest.getRequestSenderPlayerId());
		}

		game.setTimeLimitInMinutes(newGameRequest.getGameTimeLimitInMinutes());

		return game;
	}

}
