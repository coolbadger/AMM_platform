package com.amm.service.impl;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.RefMachTerminalEntity;
import com.amm.repository.GpsRecordRepository;
import com.amm.repository.RefMachTerminalRepository;
import com.amm.service.GpsRecordService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/8/2 11:17.
 * Explain:
 */
@Component("gpsRecordService")
@Scope("prototype")
public class GpsRecordServiceImpl extends BaseService implements GpsRecordService{

    @Autowired
    private GpsRecordRepository gpsRecordRepository;

    @Autowired
    private RefMachTerminalRepository refMachTerminalRepository;

    public List<GpsRecordEntity> findAllGpsRecord() {

        return (List<GpsRecordEntity>) gpsRecordRepository.findAll();
    }

    public List<GpsRecordEntity> findByRefMachTerminalID(Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        RefMachTerminalEntity refMachTerminalEntity = refMachTerminalRepository.findOne(id);

        return null;
    }

    @Transactional
    public GpsRecordEntity create(GpsRecordEntity gpsRecordEntity) {

        Validate.notNull(gpsRecordEntity, "The gpsRecordEntity must not be null, create failure.");

        GpsRecordEntity created = gpsRecordRepository.save(gpsRecordEntity);

        return created;
    }

    public List<GpsRecordEntity> findGpsRecordByTimeScope(Date startTime, Date endTime) {

        Validate.notNull(startTime, "The startTime must not be null, find failure.");
        Validate.notNull(endTime, "The endTime must not be null, find failure.");

        List<GpsRecordEntity> gpsRecordEntityList = gpsRecordRepository.findByGpsTimeAfterAndGpsTimeBefore(startTime, endTime);;

        return gpsRecordEntityList;
    }

    @Override
    public List<GpsRecordEntity> findByRefMachTerminalIDAndTimeScope(Integer id, Date startTime, Date endTime) {

        Validate.notNull(id, "The id must not be null, find failure.");
        Validate.notNull(startTime, "The startTime must not be null, find failure.");
        Validate.notNull(endTime, "The endTime must not be null, find failure.");

        return gpsRecordRepository.findByRefMachTerminalIdAndGpsTimeBetween(id, startTime, endTime);
    }
}
