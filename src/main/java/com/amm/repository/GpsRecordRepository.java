package com.amm.repository;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.RefMachTerminalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/8/2 11:20.
 * Explain:
 */

public interface GpsRecordRepository extends PagingAndSortingRepository<GpsRecordEntity, Integer>{

    List<GpsRecordEntity> findByGpsTimeAfterAndGpsTimeBefore(Date startTime, Date endTime);

    List<GpsRecordEntity> findByRefMachTerminalIdAndGpsTimeBetween(Integer id, Date startTime, Date endTime);

    List<GpsRecordEntity> findByRefMachTerminalId(Integer id);
    
}
