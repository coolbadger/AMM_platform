package com.amm.service.impl;

import com.amm.entity.GpsRecordEntity;
import com.amm.gps.WebRequest;
import com.amm.repository.GpsRecordRepository;
import com.amm.service.CoordinateConvertServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ThinkPad on 2017-03-09.
 */
@Component("CoordinateConvertServer")
@Scope("prototype")
public class CoordinateConvertImpl implements CoordinateConvertServer {

    @Autowired
    GpsRecordRepository gpsRecordRepository;

    public void convert() {

        List<GpsRecordEntity> list = gpsRecordRepository.get100UnConvert();
        for (int i=0;i<list.size();i++){
            GpsRecordEntity gpsRecordEntity = list.get(i);
            if(gpsRecordEntity.getLng()!=null && gpsRecordEntity.getLat()!=null){
                Double[] fixedGps = WebRequest.getGpsFixed(gpsRecordEntity.getLng().doubleValue(), gpsRecordEntity.getLat().doubleValue());
                gpsRecordEntity.setLngFixed(new BigDecimal(fixedGps[0]));
                gpsRecordEntity.setLatFixed(new BigDecimal(fixedGps[1]));
                gpsRecordEntity.setFlag("1");
                gpsRecordRepository.saveAndFlush(gpsRecordEntity);
            }
        }

    }

}
