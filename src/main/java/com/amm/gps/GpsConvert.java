package com.amm.gps;

import com.amm.entity.GpsRecordEntity;
import com.amm.service.GpsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Badger on 16/9/18.
 */
@Component
public class GpsConvert {
    @Autowired
    private GpsRecordService gpsRecordService;

    private List<String> gpsGropList;

    public void generateGpsGroup() {
        List<GpsRecordEntity> gpsRecordEntityList = gpsRecordService.findAllGpsRecord();
        for (GpsRecordEntity gpsRecord : gpsRecordEntityList) {
            System.out.println("id:" + gpsRecord.getId() + "lng:" + gpsRecord.getLng() + "lat:" + gpsRecord.getLat());
        }
    }
}
