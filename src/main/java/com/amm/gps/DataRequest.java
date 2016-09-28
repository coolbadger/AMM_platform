package com.amm.gps;

import com.amm.entity.GpsRecordEntity;
import com.amm.service.GpsRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by badger on 2016/9/28.
 */
public class DataRequest {
    @Autowired
    private GpsRecordService gpsRecordService;

    public String[] getUnConvertedGPS(){
        List<GpsRecordEntity> gpsRecordEntityList = gpsRecordService.findAllGpsRecord();
        return null;
    }
}
