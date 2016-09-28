package com.amm.gps;

import com.amm.entity.GpsRecordEntity;
import com.amm.service.GpsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by badger on 2016/9/28.
 */
@Component
public class DataRequest {

    @Autowired
    private GpsRecordService gpsRecordService;

    public String[] getUnConvertedGPS() {
        List<GpsRecordEntity> gpsRecordEntityList = gpsRecordService.findAllGpsRecord();
        for (GpsRecordEntity gpsRecord : gpsRecordEntityList) {
            System.out.println("id:" + gpsRecord.getId() + "lng:" + gpsRecord.getLng() + "lat:" + gpsRecord.getLat());
        }
        return null;
    }
}
