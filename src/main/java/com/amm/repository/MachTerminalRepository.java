package com.amm.repository;

import com.amm.entity.MachTerminalEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by csw on 2016/8/7 11:06.
 * Explain:
 */
public interface MachTerminalRepository extends PagingAndSortingRepository<MachTerminalEntity, Integer> {
    MachTerminalEntity findByMachId(Integer id);

    MachTerminalEntity findByTerminalId(Integer id);
}
