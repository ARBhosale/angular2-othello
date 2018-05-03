package gmu.isa681.project.othelloserver.convertor;

<<<<<<< HEAD
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

	
=======
import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.model.Links;
import gmu.isa681.project.othelloserver.model.Self;
import gmu.isa681.project.othelloserver.model.response.UserAccountRespose;
import gmu.isa681.project.othelloserver.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;


public class UserEntityToUserAccountResponseConverter implements Converter<UserEntity, UserAccountRespose> {
    @Override
    public UserAccountRespose convert(UserEntity source) {
        UserAccountRespose userAccountRespose= new UserAccountRespose();
        userAccountRespose.setUsername(source.getUsername());
        userAccountRespose.setData(source.getData());
        Links links= new Links();
        Self self= new Self();
        self.setRef(ResourceConstants.USER_ACCOUNT_V1+"/"+source.getId());
        links.setSelf(self);
        userAccountRespose.setLinks(links);
        return userAccountRespose;
    }
>>>>>>> TyroneNewServer
}
