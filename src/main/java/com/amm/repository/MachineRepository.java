package com.amm.repository;

import com.amm.entity.MachineEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by csw on 2016/8/4 13:14.
 * Explain:
 */
public interface MachineRepository extends PagingAndSortingRepository<MachineEntity, Integer> {
}
