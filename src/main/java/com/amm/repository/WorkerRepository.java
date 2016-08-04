package com.amm.repository;

import com.amm.entity.WorkerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by csw on 2016/8/4 12:51.
 * Explain:
 */
public interface WorkerRepository extends PagingAndSortingRepository<WorkerEntity, Integer> {
}
