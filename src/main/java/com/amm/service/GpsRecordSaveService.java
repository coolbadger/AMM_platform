package com.amm.service;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.WorkerEntity;
import com.amm.entity.client.GpsRecordSave;

/**
 * Created by csw on 2016/8/16 15:23.
 * Explain:
 */
public interface GpsRecordSaveService {

    WorkerEntity isLogin(String userName, String password);

    GpsRecordSave save(GpsRecordSave gpsRecordSave);

}
