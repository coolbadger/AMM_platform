package com.amm.repository;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.RefMachTerminalEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/8/2 12:25.
 * Explain:
 */
public interface RefMachTerminalRepository extends PagingAndSortingRepository<RefMachTerminalEntity, Integer> {

    RefMachTerminalEntity findById(Integer Id);

}
