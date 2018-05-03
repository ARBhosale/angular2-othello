package gmu.isa681.project.othelloserver.convertor;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.model.request.UserAccountRequest;

public class UserAccountRequestToUserEntityConverter implements Converter<UserAccountRequest, UserEntity> {

	@Override
	public UserEntity convert(UserAccountRequest source) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(source.getUsername());
		userEntity.setPassword(source.getPassword());
		
	//	if(source.getId() !=null)
	//		userEntity.setId(source.getId());
		
		return userEntity;
	}

}
