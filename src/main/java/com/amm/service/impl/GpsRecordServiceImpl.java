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

        return gpsRecordRepository.findByRefMachTerminalByRefMachTerminalId(refMachTerminalEntity);
    }
}
