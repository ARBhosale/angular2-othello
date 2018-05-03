package gmu.isa681.project.othelloserver.repository;

import org.springframework.data.repository.CrudRepository;

import gmu.isa681.project.othelloserver.entity.PlayerEntity;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long>{
    Optional<PlayerEntity> findById (Long id);
}
