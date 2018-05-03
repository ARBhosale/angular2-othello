package gmu.isa681.project.othelloserver.repository;

import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagablePlayerRepository extends PagingAndSortingRepository<PlayerEntity, Long> {

    Page<PlayerEntity> findById(Long id, Pageable pageable);
}
