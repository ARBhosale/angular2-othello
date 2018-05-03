package gmu.isa681.project.othelloserver.convertor;

import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.model.Links;
import gmu.isa681.project.othelloserver.model.Self;
import gmu.isa681.project.othelloserver.model.response.AccountResponse;
import gmu.isa681.project.othelloserver.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

public class PlayerEntityToAccountResponseConverter  implements Converter<PlayerEntity, AccountResponse> {
    @Override
    public AccountResponse convert(PlayerEntity source) {

        AccountResponse accountResponse= new AccountResponse();
        accountResponse.setId(source.getId());
        accountResponse.setUserName(source.getUserName());
        accountResponse.setFirstName(source.getFirstName());
        accountResponse.setLastName(source.getLastName());
        accountResponse.setWins(source.getWins());
        accountResponse.setLosses(source.getLosses());
        accountResponse.setFullName(source.getFirstName()+" "+source.getLastName());
        Links links= new Links();
        Self self= new Self();
        self.setRef(ResourceConstants.PLAYER_ACCOUNT_V1+"/"+source.getId());
        links.setSelf(self);
        accountResponse.setLink(links);
        return accountResponse;
    }
}
