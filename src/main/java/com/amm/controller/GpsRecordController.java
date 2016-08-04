package com.amm.controller;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.WorkerEntity;
import com.amm.entity.client.GpsRecord;
import com.amm.service.GpsRecordService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csw on 2016/8/2 11:12.
 * Explain:
 */

@RestController
@RequestMapping("api/gpsRecords")
public class GpsRecordController extends BaseController{

    @Autowired
    private GpsRecordService gpsRecordService;

    @RequestMapping(method = RequestMethod.GET)
    public List<GpsRecord> findAll(){

        List<GpsRecordEntity> gpsRecordEntityList = gpsRecordService.findAllGpsRecord();

        List<GpsRecord> gpsRecordList = new ArrayList<GpsRecord>();

        for (GpsRecordEntity gpsRecordEntity : gpsRecordEntityList) {
            GpsRecord gpsRecord = new GpsRecord();
            gpsRecord.setId(gpsRecordEntity.getId());
            gpsRecord.setAccuracy(gpsRecordEntity.getAccuracy());
            gpsRecord.setAlt(gpsRecordEntity.getAlt());
            gpsRecord.setGpsTime(gpsRecordEntity.getGpsTime());
            gpsRecord.setLocalTime(gpsRecordEntity.getLocalTime());
            gpsRecord.setLng(gpsRecordEntity.getLng());
            gpsRecord.setSpeed(gpsRecordEntity.getSpeed());
            gpsRecord.setWorkerId(gpsRecordEntity.getWorkerByWorkerId().getId());
            gpsRecord.setBaseOrgId(gpsRecordEntity.getWorkerByWorkerId().getBaseOrgByOrgId().getId());
            gpsRecord.setRefMachTerminalByRefMachTerminalId(gpsRecordEntity.getRefMachTerminalByRefMachTerminalId().getId());
            gpsRecord.setMachTerminalByMachTerminalId(gpsRecordEntity.getRefMachTerminalByRefMachTerminalId().getMachTerminalByMachTerminalId().getId());
            gpsRecord.setMachineByMachId(gpsRecordEntity.getRefMachTerminalByRefMachTerminalId().getMachTerminalByMachTerminalId().getMachineByMachId().getId());
            gpsRecord.setMachCode(gpsRecordEntity.getRefMachTerminalByRefMachTerminalId().getMachTerminalByMachTerminalId().getMachineByMachId().getMachCode());
            gpsRecord.setMachName(gpsRecordEntity.getRefMachTerminalByRefMachTerminalId().getMachTerminalByMachTerminalId().getMachineByMachId().getMachName());
            gpsRecordList.add(gpsRecord);
        }

        return gpsRecordList;
    }

    @RequestMapping(value = "/refMachTerminal/{id}", method = RequestMethod.GET)
    public List<GpsRecordEntity> findByRefMachTerminalID(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return gpsRecordService.findByRefMachTerminalID(id);
    }
}
