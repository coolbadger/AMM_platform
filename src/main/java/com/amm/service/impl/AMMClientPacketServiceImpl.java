package com.amm.service.impl;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.TerminalEntity;
import com.amm.entity.WorkerEntity;
import com.amm.entity.client.GpsRecordSave;
import com.amm.repository.WorkerRepository;
import com.amm.service.AMMClientPacketService;
import com.amm.service.GpsRecordService;
import com.amm.service.TerminalService;
import com.amm.service.WorkerService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by csw on 2016/8/16 15:27.
 * Explain:
 */
@Component("ammClientPacketService")
@Scope("prototype")
public class AMMClientPacketServiceImpl extends BaseService implements AMMClientPacketService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private GpsRecordService gpsRecordService;

    @Autowired
    private WorkerService workerService;

    public WorkerEntity isLogin(String userName, String password, String terminalCode) {

        Validate.notNull(userName, "The userName must not be null, login failure.");
        Validate.notNull(password, "The password must not be null, login failure.");
        Validate.notNull(terminalCode, "The terminalCode must not be null, login failure.");

        WorkerEntity workerEntity = null;

        TerminalEntity terminalEntity =  terminalService.findByTerminalCode(terminalCode);
        Validate.notNull(terminalEntity, "The found terminalEntity must not be null, login failure.");

        Integer orgId = terminalEntity.getOrgId();

        workerEntity = workerRepository.findByUserNameAndPasswordAndOrgId(userName, password, orgId);

        return workerEntity;
    }

    public GpsRecordEntity save(GpsRecordSave gpsRecordSave) {

        Validate.notNull(gpsRecordSave, "The gpsRecordSave must not be null, create failure.");
        Validate.notNull(gpsRecordSave.getTerminalCode(), "The terminalCode of gpsRecordSave must not be null, create failure.");
        Validate.notNull(gpsRecordSave.getUserName(), "The userName of gpsRecordSave must not be null, create failure.");

        GpsRecordEntity gpsRecordEntity = new GpsRecordEntity();

        gpsRecordEntity.setLocalTime(gpsRecordSave.getLocalTime());
        gpsRecordEntity.setGpsTime(gpsRecordSave.getGpsTime());
        gpsRecordEntity.setAccuracy(gpsRecordSave.getAccuracy());
        gpsRecordEntity.setAlt(gpsRecordSave.getAlt());
        gpsRecordEntity.setLat(gpsRecordSave.getLat());
        gpsRecordEntity.setLng(gpsRecordSave.getLng());
        gpsRecordEntity.setLngFixed(gpsRecordSave.getLngFixed());
        gpsRecordEntity.setLatFixed(gpsRecordSave.getLatFixed());
        gpsRecordEntity.setSensor1(gpsRecordSave.getSensor1());
        gpsRecordEntity.setSensor2(gpsRecordSave.getSensor2());
        gpsRecordEntity.setSensor3(gpsRecordSave.getSensor3());
        gpsRecordEntity.setSensor4(gpsRecordSave.getSensor4());
        gpsRecordEntity.setTerminalCode(gpsRecordSave.getTerminalCode());

        WorkerEntity workerEntity = workerService.findByUserName(gpsRecordSave.getUserName());
        Validate.notNull(workerEntity, "The found workerEntity must not be null, create gpsRecord failure.");
        gpsRecordEntity.setWorkerId(workerEntity.getId());

        return gpsRecordService.create(gpsRecordEntity);
    }
}
