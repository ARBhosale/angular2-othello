package gmu.isa681.project.othelloserver.convertor;

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
}
