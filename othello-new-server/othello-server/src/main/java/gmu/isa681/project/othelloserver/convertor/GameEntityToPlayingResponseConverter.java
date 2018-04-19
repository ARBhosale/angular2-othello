package gmu.isa681.project.othelloserver.convertor;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.model.Links;
import gmu.isa681.project.othelloserver.model.Self;
import gmu.isa681.project.othelloserver.model.response.PlayingResponse;
import gmu.isa681.project.othelloserver.rest.ResourceConstants;

public class GameEntityToPlayingResponseConverter implements Converter<GameEntity, PlayingResponse> {

	@Override
	public PlayingResponse convert(GameEntity source) {
		// TODO Auto-generated method stub

		PlayingResponse playingResponse = new PlayingResponse(source.getId());
		playingResponse.setGameNumber(source.getId());
		playingResponse.setHasBlack(true);

		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourceConstants.GAME_PLAYING_V1 + "/" + source.getId());
		links.setSelf(self);
		
		playingResponse.setLinks(links);

		return playingResponse;
	}

}
