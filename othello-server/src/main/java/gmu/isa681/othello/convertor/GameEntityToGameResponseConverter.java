package gmu.isa681.othello.convertor;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.othello.entity.GameEntity;
import gmu.isa681.othello.model.response.GameResponse;

public class GameEntityToGameResponseConverter implements Converter<GameEntity, GameResponse> {
	@Override
	public GameResponse convert(GameEntity source) {

		GameResponse gameResponse = new GameResponse();

		return gameResponse;
	}
}
