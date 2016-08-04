package com.amm.repository;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.RefMachTerminalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by csw on 2016/8/2 11:20.
 * Explain:
 */

public interface GpsRecordRepository extends PagingAndSortingRepository<GpsRecordEntity, Integer>{

//    @Query(value = "select * from gps_record where ref_mach_terminal_id = :id")
    List<GpsRecordEntity> findByRefMachTerminalByRefMachTerminalId(RefMachTerminalEntity id);
}
