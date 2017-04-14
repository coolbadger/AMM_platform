package com.amm.repository;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.client.GpsData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/8/2 11:20.
 * Explain:
 */

public interface GpsRecordRepository extends PagingAndSortingRepository<GpsRecordEntity, Integer>{

    List<GpsRecordEntity> findByGpsTimeAfterAndGpsTimeBefore(Date startTime, Date endTime);

    List<GpsRecordEntity> findByRefMachTerminalIdAndGpsTimeBetweenAndLngFixedIsNotNull(Integer id, Date startTime, Date endTime);

    List<GpsRecordEntity> findByRefMachTerminalId(Integer id);

    List<GpsRecordEntity> findByLatFixedOrderByGpsTimeAsc(String str);

    @Query(value = "SELECT * FROM (SELECT * FROM gps_record WHERE lat_fixed IS NOT NULL ORDER BY gps_time DESC ) a GROUP BY ref_mach_terminal_id",nativeQuery = true)
    List<GpsRecordEntity> getFirst();

    @Query(value="SELECT * FROM gps_record WHERE state IS NULL OR state!=1",nativeQuery = true)
    List<GpsRecordEntity> getFinishingData();

    @Modifying @Query(value = "UPDATE gps_record set state=?1 ",nativeQuery = true) int updateState(String state);//

    @Query(value = "SELECT * FROM gps_record  WHERE flag IS NULL",nativeQuery = true)
    List<GpsRecordEntity> getUnConvert();

    public GpsRecordEntity saveAndFlush(GpsRecordEntity gpsRecordEntity);


}
