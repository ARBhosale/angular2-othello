package gmu.isa681.project.othelloserver.convertor;

import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.model.request.UserAccountRequest;
import org.springframework.core.convert.converter.Converter;

public class UserAccountRequestToUserEntityConverter implements Converter<UserAccountRequest, UserEntity> {

    @Override
    public UserEntity convert(UserAccountRequest source) {

        UserEntity userEntity= new UserEntity();
        //userEntity.setId(source.getId());
        userEntity.setUsername(source.getUsername());
        userEntity.setData(source.getData());
        return userEntity;
    }
}
