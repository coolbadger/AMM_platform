package com.amm.repository;

import com.amm.entity.MachineEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by csw on 2016/8/4 13:14.
 * Explain:
 */
public interface MachineRepository extends PagingAndSortingRepository<MachineEntity, Integer> {
    List<MachineEntity> findAllByActive(Boolean active);

    MachineEntity findById(Integer id);

    List<MachineEntity> findByOrgId(Integer orgId);

}
