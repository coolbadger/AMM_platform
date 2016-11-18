package com.amm.service.impl;

import com.amm.entity.MachineEntity;
import com.amm.entity.MaintainRecordEntity;
import com.amm.repository.MachTerminalRepository;
import com.amm.repository.MachineRepository;
import com.amm.repository.RefMachTerminalRepository;
import com.amm.repository.MaintainRecordRepository;
import com.amm.repository.TerminalRepository;
import com.amm.service.MaintainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 杨思名 on 2016/11/17.
 */
@Component("maintainRecordService")
@Scope("prototype")
public class MaintainRecordServiceImpl implements MaintainRecordService {
    @Autowired
    protected MaintainRecordRepository maintainRecordRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachTerminalRepository machTerminalRepository;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private RefMachTerminalRepository refMachTerminalRepository;


    public MaintainRecordEntity create(MaintainRecordEntity machTerminalEntity) {
        return null;
    }

    public List<MaintainRecordEntity> findAll(Boolean isBind) {
        List<MaintainRecordEntity>  ListMaintainRecordEntity=(List<MaintainRecordEntity>) maintainRecordRepository.findAll();
        return ListMaintainRecordEntity;
    }

    public MaintainRecordEntity findById(Integer id) {
        return maintainRecordRepository.findById(id);
    }


    public MaintainRecordEntity updateToUnBind(Integer id, MaintainRecordEntity machTerminalEntity) {
        return null;
    }

    public MaintainRecordEntity findByTerminalCode(String terminalCode) {
        return null;
    }
}
