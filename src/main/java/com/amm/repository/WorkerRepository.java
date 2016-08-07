package com.amm.repository;

import com.amm.entity.WorkerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by csw on 2016/8/4 12:51.
 * Explain:
 */
public interface WorkerRepository extends PagingAndSortingRepository<WorkerEntity, Integer> {
    WorkerEntity findByUserName(String userName);

    List<WorkerEntity> findAllByActive(Boolean active);
}
