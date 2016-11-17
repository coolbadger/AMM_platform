package com.amm.service;
import com.amm.entity.MaintainRecordEntity;
import java.util.List;

/**
 * Created by 杨思名 on 2016/11/17.
 */
public interface MaintainRecordService {
    MaintainRecordEntity create(MaintainRecordEntity machTerminalEntity);

    List<MaintainRecordEntity> findAll(Boolean isBind);

    MaintainRecordEntity findByMachId(Integer machId);

    MaintainRecordEntity updateToUnBind(Integer id, MaintainRecordEntity machTerminalEntity);

    MaintainRecordEntity findByTerminalCode(String terminalCode);

}
