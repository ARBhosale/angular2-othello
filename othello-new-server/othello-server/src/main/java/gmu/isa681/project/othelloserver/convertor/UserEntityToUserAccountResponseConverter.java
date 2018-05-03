package gmu.isa681.project.othelloserver.convertor;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.model.Links;
import gmu.isa681.project.othelloserver.model.Self;
import gmu.isa681.project.othelloserver.model.response.UserAccountResponse;
import gmu.isa681.project.othelloserver.rest.ResourceConstants;

public class UserEntityToUserAccountResponseConverter implements Converter<UserEntity, UserAccountResponse> {

	@Override
	public UserAccountResponse convert(UserEntity source) {
		// TODO Auto-generated method stub
		
		UserAccountResponse userAccountResponse = new UserAccountResponse();
		userAccountResponse.setUsername(source.getUsername());
		userAccountResponse.setPassword(source.getPassword());
		
		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourceConstants.USER_ACCOUNT_V1+"/" + source.getUsername());
		links.setSelf(self);
		
		userAccountResponse.setLinks(links);
		return userAccountResponse;
	}

	
}
