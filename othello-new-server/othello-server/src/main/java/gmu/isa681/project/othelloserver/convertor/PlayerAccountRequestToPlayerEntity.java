package gmu.isa681.project.othelloserver.convertor;

import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.model.request.PlayerAccountRequest;
import gmu.isa681.project.othelloserver.security.SaltedHash;
import org.springframework.core.convert.converter.Converter;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class PlayerAccountRequestToPlayerEntity implements Converter<PlayerAccountRequest, PlayerEntity> {
    @Override
    public PlayerEntity convert(PlayerAccountRequest source) {
        PlayerEntity playerEntity= new PlayerEntity();
        playerEntity.setFirstName(source.getFirstName());
        playerEntity.setLastName(source.getLastName());
        playerEntity.setUserName(source.getUserName());

        //playerEntity.setPassword(source.getPassword());
        byte[] salt=new byte[16];
        Random r=new SecureRandom();
        r.nextBytes(salt);
        String pwd= SaltedHash.getSaltHashedPassword(source.getPassword(), salt);
        playerEntity.setPassword(pwd);
        playerEntity.setSalt(Base64.getEncoder().encodeToString(salt));
        return playerEntity;
    }
}
