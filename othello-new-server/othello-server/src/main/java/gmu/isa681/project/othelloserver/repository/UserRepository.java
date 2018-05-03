package gmu.isa681.project.othelloserver.repository;

import gmu.isa681.project.othelloserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findById (Long id);
}
