package gmu.isa681.project.othelloserver.repository.game;

import org.springframework.data.repository.CrudRepository;

import gmu.isa681.project.othelloserver.entity.game.BoardEntity;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
}
