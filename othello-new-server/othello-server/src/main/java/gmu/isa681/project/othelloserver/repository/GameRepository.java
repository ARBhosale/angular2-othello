package gmu.isa681.project.othelloserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gmu.isa681.project.othelloserver.entity.GameEntity;

public interface GameRepository extends CrudRepository<GameEntity, Long>{
	Optional<GameEntity> findById(Long id);
}
