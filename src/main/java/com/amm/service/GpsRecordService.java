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

    List<GpsRecordEntity> findGpsRecordByTimeScope(Date startTime, Date endTime);
}
