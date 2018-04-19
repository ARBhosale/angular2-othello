package gmu.isa681.project.othelloserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import gmu.isa681.project.othelloserver.entity.GameEntity;

public interface PageableGameRepository extends PagingAndSortingRepository<GameEntity, Long> {

	Page<GameEntity> findById(Long id, Pageable page);
}
