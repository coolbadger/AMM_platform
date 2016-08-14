package com.amm.repository;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.RefMachTerminalEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by csw on 2016/8/2 12:25.
 * Explain:
 */
public interface RefMachTerminalRepository extends PagingAndSortingRepository<RefMachTerminalEntity, Integer>{
}
