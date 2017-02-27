package com.amm.service;

import com.amm.entity.GpsRecordEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/8/2 11:17.
 * Explain:
 */
public interface GpsRecordService {
    List<GpsRecordEntity> findAllGpsRecord();

    List<GpsRecordEntity> findByRefMachTerminalID(Integer id);

    GpsRecordEntity create(GpsRecordEntity gpsRecordEntity);
    GpsRecordEntity findOne(Integer id);

    List<GpsRecordEntity> findGpsRecordByTimeScope(Date startTime, Date endTime);

    List<GpsRecordEntity> findByRefMachTerminalIDAndTimeScope(Integer id, Date startTime, Date endTime);

    List<GpsRecordEntity> findByLatFixedIsNullOrderbyGpsTimeAsc();

    GpsRecordEntity updateGpsRecord(GpsRecordEntity gpsRecord);

    List<GpsRecordEntity> getFirst();

    List<GpsRecordEntity> getFinishingData();

    void updateState(String state);

}
