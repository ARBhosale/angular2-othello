package gmu.isa681.project.othelloserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gmu.isa681.project.othelloserver.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByUsernameAndPassword(String username, String Password);

	UserEntity findByUsername(String username);
}
