package gmu.isa681.project.othelloserver.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import gmu.isa681.project.othelloserver.entity.UserEntity;

public interface PageableUserRepository extends PagingAndSortingRepository<UserEntity, String> {

	UserEntity findByUsernameAndPassword(String username, String password);
	
}
