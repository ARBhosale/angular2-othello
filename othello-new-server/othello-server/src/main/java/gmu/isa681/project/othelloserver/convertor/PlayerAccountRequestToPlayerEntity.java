package gmu.isa681.project.othelloserver.convertor;

import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.model.request.PlayerAccountRequest;
import org.springframework.core.convert.converter.Converter;

public class PlayerAccountRequestToPlayerEntity implements Converter<PlayerAccountRequest, PlayerEntity> {
    @Override
    public PlayerEntity convert(PlayerAccountRequest source) {
        PlayerEntity playerEntity= new PlayerEntity();
        playerEntity.setFirstName(source.getFirstName());
        playerEntity.setLastName(source.getLastName());
        playerEntity.setUserName(source.getUserName());
        playerEntity.setPassword(source.getPassword());
        return playerEntity;
    }
}
