package gmu.isa681.project.othelloserver.rest;


import gmu.isa681.project.othelloserver.convertor.UserEntityToUserAccountResponseConverter;
import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.model.request.UserAccountRequest;
import gmu.isa681.project.othelloserver.model.response.UserAccountRespose;
import gmu.isa681.project.othelloserver.repository.PagableUserRepository;
import gmu.isa681.project.othelloserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ResourceConstants.USER_ACCOUNT_V1)
public class UserResource {

    @Autowired
    PagableUserRepository pagableUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConversionService conversionService;

    @RequestMapping(path="",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<UserAccountRespose> getUserAccounts(
            @RequestParam(value="Username")
            String Username,
            @RequestParam(value="password")
            String password, Pageable pageable){

        Page<UserEntity> userEntityList= pagableUserRepository.findAll(pageable);
        Converter<UserEntity, UserAccountRespose> converter = new UserEntityToUserAccountResponseConverter();

        return userEntityList.map((e->converter.convert(e)));
    }

    @RequestMapping(path="/{userId}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserEntity> getUserById(
            @PathVariable
            Long userId){
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        return new ResponseEntity<>(userEntity.get(),HttpStatus.CREATED);
    }
    @RequestMapping(path="",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserAccountRespose> createUserAccount(
            @RequestBody
            UserAccountRequest userAccountRequest){
        UserEntity userEntity= conversionService.convert(userAccountRequest, UserEntity.class);
        if(!userEntity.getUsername().matches("[a-zA-Z0-9\\\\._\\\\-]{3,}")){
            return new ResponseEntity <>(HttpStatus.NO_CONTENT);
        }
        userRepository.save(userEntity);
        UserAccountRespose userAccountRespose= conversionService.convert(userEntity, UserAccountRespose.class);
        return new ResponseEntity <>(userAccountRespose, HttpStatus.CREATED);
    }



    @RequestMapping(path="",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserAccountRespose> updateUserAccount(
            @RequestBody
                    UserAccountRequest userAccountRequest){

        return new ResponseEntity <>(new UserAccountRespose(), HttpStatus.OK);
    }

    @RequestMapping(path= "/{reservationId}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserAccount(
            @PathVariable
            long reservationId) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
