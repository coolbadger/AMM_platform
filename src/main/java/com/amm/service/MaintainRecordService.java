package com.amm.service;
import com.amm.entity.MaintainRecordEntity;
import com.amm.entity.client.Maintainrecord;

import java.util.List;

/**
 * Created by 杨思名 on 2016/11/17.
 */
public interface MaintainRecordService {
    MaintainRecordEntity create(MaintainRecordEntity machTerminalEntity);

    List<Maintainrecord> findAll(Boolean isBind);

    MaintainRecordEntity findById(Integer id);

    void delete(Integer id);

    MaintainRecordEntity update(MaintainRecordEntity maintainRecord);



}
