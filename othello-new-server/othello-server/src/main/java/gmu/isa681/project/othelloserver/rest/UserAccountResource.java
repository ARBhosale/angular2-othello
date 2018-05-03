package gmu.isa681.project.othelloserver.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gmu.isa681.project.othelloserver.convertor.GameEntityToPlayingResponseConverter;
import gmu.isa681.project.othelloserver.convertor.UserEntityToUserAccountResponseConverter;
import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.UserEntity;
import gmu.isa681.project.othelloserver.model.request.UserAccountRequest;
import gmu.isa681.project.othelloserver.model.response.PlayingResponse;
import gmu.isa681.project.othelloserver.model.response.UserAccountResponse;
import gmu.isa681.project.othelloserver.repository.PageableUserRepository;
import gmu.isa681.project.othelloserver.repository.UserRepository;

@RestController
@RequestMapping(ResourceConstants.USER_ACCOUNT_V1)
public class UserAccountResource {

	@Autowired
	PageableUserRepository pageableUserRepository;
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	ConversionService conversionService;
	
	@RequestMapping(path="", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<UserAccountResponse> getUser(
			@RequestParam(value="username")
			String username,
			@RequestParam(value="password")
			String password, Pageable pageable)
	{
		Page<UserEntity> userEntityList= pageableUserRepository.findAll(pageable);
		
		Converter<UserEntity, UserAccountResponse> converter = new UserEntityToUserAccountResponseConverter();
		
	//	System.out.println(userEntityList.map((e->converter.convert(e))));
		return userEntityList.map((e->converter.convert(e)));
	}
	
	//try
/*	@RequestMapping(path="", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUserByNameAndPassword(
			@RequestBody
			UserAccountRequest userAccountRequest, Pageable pageable){
		
		UserEntity userEntity= conversionService.convert(userAccountRequest, UserEntity.class);
		
		Page<UserEntity> userEntityList= pageableUserRepository.findAll(pageable);
		
	//	List<UserEntity> userEntityList1= UserRepository.findAll();
		
	//	UserEntity userEntity1= pageableUserRepository.findByUsernameAndPassword(username, password);
		
	//	Converter<UserEntity, UserAccountResponse> converter = new UserEntityToUserAccountResponseConverter();
		//UserEntity userEntity = userRepository.findByUsername(username);
		
		Iterator itr = userEntityList.iterator();
		int i=0;
		while(itr.hasNext())
		{
			System.out.println(i);
			System.out.println(userEntityList);
			i++;
			if(itr.next().equals(userEntity))
				return "Success";
		}
		return "No Success";
		//return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}
	
	
	//try
	@RequestMapping(path="", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUserByUsernameAndPassword(
			@RequestBody
			UserAccountRequest userAccountRequest, Pageable pageable){
		
		UserEntity userEntity= conversionService.convert(userAccountRequest, UserEntity.class);
		
		String password;
		
	//	try {
		//	UserEntity user = userEntity;
		//	user
	//	}
	//	Page<UserEntity> userEntityList= pageableUserRepository.findAll(pageable);
		
	//	List<UserEntity> userEntityList1= UserRepository.findAll();
		
	//	UserEntity userEntity1= pageableUserRepository.findByUsernameAndPassword(username, password);
		
	//	Converter<UserEntity, UserAccountResponse> converter = new UserEntityToUserAccountResponseConverter();
		//UserEntity userEntity = userRepository.findByUsername(username);
		
	/*	Iterator itr = userEntityList.iterator();
		int i=0;
		while(itr.hasNext())
		{
			System.out.println(i);
			System.out.println(userEntityList);
			i++;
			if(itr.next().equals(userEntity))
				return "Success";
		}	*/
	//	return "No Success";
		//return new ResponseEntity<>(userEntity, HttpStatus.OK);
//	}
		
	
	@RequestMapping(path="/{username}/{password}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserEntity> getUserByName(
			@PathVariable
			String username,
			@PathVariable
			String password){
		
		UserEntity userEntity = userRepository.findByUsernameAndPassword(username, password);
		
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}
	
	
	
	
	
/*	@RequestMapping(path="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserAccountResponse> createUser(
			@RequestBody
			UserAccountRequest userAccountRequest)
	{
		UserEntity userEntity= conversionService.convert(userAccountRequest, UserEntity.class);
	//	userRepository.save(userEntity);
		
		UserAccountResponse userAccountResponse = conversionService.convert(userEntity, UserAccountResponse.class); 
		
		UserEntity user =  userRepository.findByUsername(userEntity.getUsername());
		
		System.out.println("Username "+ user.getPassword());
		return new ResponseEntity<>(userAccountResponse, HttpStatus.CREATED);
	} 
	*/
	
	//try
	@RequestMapping(path="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserAccountResponse> validateUser(
			@RequestBody
			UserAccountRequest userAccountRequest)
	{
		UserEntity userEntity= conversionService.convert(userAccountRequest, UserEntity.class);
		UserAccountResponse userAccountResponse = conversionService.convert(userEntity, UserAccountResponse.class); 
	//	userRepository.save(userEntity);
	//	Set<UserEntity> userEntityList= (Set<UserEntity>) userRepository.findAll();
	//	UserEntity userEntity1= pageableUserRepository.findByUsernameAndPassword(username, password);
		Iterable<UserEntity>itr= userRepository.findAll();
		Iterator<UserEntity> it= itr.iterator();
		
		System.out.println("Requested details: "+userEntity.getUsername()+" "+userEntity.getPassword());
		
		int flag=0;
		UserEntity user = new UserEntity();
		while(it.hasNext()){
			user=it.next();
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			if(user.getUsername().equals(userEntity.getUsername()))
				if(user.getPassword().equals(userEntity.getPassword()))
					flag=1;
		}
		
		if(flag==0)
			return null;
		
			return new ResponseEntity<>(userAccountResponse, HttpStatus.OK);	
		
	}		
	
	@RequestMapping(path="", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserAccountResponse> updateUser(
			@RequestBody
			UserAccountRequest userAccountRequest)
	{
		
		return new ResponseEntity<>(new UserAccountResponse(), HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<UserAccountResponse> deleteUser(
			@PathVariable
			long userId)
	{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
