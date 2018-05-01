package gmu.isa681.project.othelloserver.rest;


import gmu.isa681.project.othelloserver.model.request.UserAccountRequest;
import gmu.isa681.project.othelloserver.model.response.UserAccountRespose;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ResourceConstants.USER_ACCOUNT_V1)
public class UserResource {

    @RequestMapping(path="",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserAccountRespose> getUserAccounts(
            @RequestParam(value="Username")
            String Username,
            @RequestParam(value="password")
            String password){

        return new ResponseEntity <>(new UserAccountRespose(), HttpStatus.OK);
    }

    @RequestMapping(path="",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserAccountRespose> createUserAccount(
            @RequestBody
            UserAccountRequest userAccountRequest){

        return new ResponseEntity <>(new UserAccountRespose(), HttpStatus.CREATED);
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
