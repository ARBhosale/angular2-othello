package gmu.isa681.project.othelloserver.repository;

import gmu.isa681.project.othelloserver.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagableUserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Page<UserEntity> findById(Long id, Pageable pageable);
}
