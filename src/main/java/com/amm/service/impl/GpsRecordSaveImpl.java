package com.amm.service.impl;

import com.amm.entity.WorkerEntity;
import com.amm.entity.client.GpsRecordSave;
import com.amm.service.GpsRecordSaveService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by csw on 2016/8/16 15:27.
 * Explain:
 */
@Component("gpsRecordSaveService")
@Scope("prototype")
public class GpsRecordSaveImpl extends BaseService implements GpsRecordSaveService{
    public WorkerEntity isLogin(String userName, String password) {
        return null;
    }

    public GpsRecordSave save(GpsRecordSave gpsRecordSave) {
        return null;
    }
}
