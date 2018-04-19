package gmu.isa681.othello.convertor;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.othello.entity.GameEntity;
import gmu.isa681.othello.model.request.GameRequest;
;

public class GameRequestToGameEntityConverter implements Converter<GameRequest, GameEntity>  {
	@Override
    public GameEntity convert(GameRequest source) {

		GameEntity gameEntity = new GameEntity();
        

        return gameEntity;
    }
}
