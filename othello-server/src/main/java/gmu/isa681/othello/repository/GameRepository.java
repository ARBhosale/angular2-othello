package gmu.isa681.othello.repository;

import gmu.isa681.othello.entity.GameEntity;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GameEntity,Long> {

}
