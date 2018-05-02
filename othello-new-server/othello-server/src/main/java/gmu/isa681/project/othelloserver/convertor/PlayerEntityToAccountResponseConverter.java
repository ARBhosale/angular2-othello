package gmu.isa681.project.othelloserver.convertor;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.model.Links;
import gmu.isa681.project.othelloserver.model.Self;
import gmu.isa681.project.othelloserver.model.response.AccountResponse;
import gmu.isa681.project.othelloserver.model.response.game.PlayingResponse;
import gmu.isa681.project.othelloserver.rest.ResourceConstants;

public class PlayerEntityToAccountResponseConverter implements Converter<PlayerEntity, AccountResponse> {

	@Override
	public AccountResponse convert(PlayerEntity source) {
		// TODO Auto-generated method stub

		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setId(source.getId());
		accountResponse.setUserName(source.getUserName());
		accountResponse.setFullName(source.getFirstName() + " " + source.getLastName());

		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourceConstants.PLAYER_ACCOUNT_V1 + "/" + source.getId());
		links.setSelf(self);

		accountResponse.setLinks(links);

		return accountResponse;
	}

}
