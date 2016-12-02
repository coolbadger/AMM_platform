package com.amm.service.impl;

import com.amm.entity.MachineEntity;
import com.amm.entity.MaintainRecordEntity;
import com.amm.entity.client.Maintainrecord;
import com.amm.exception.ObjectNotFoundException;
import com.amm.repository.MachTerminalRepository;
import com.amm.repository.MachineRepository;
import com.amm.repository.RefMachTerminalRepository;
import com.amm.repository.MaintainRecordRepository;
import com.amm.repository.TerminalRepository;
import com.amm.service.MaintainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    public MaintainRecordEntity create(MaintainRecordEntity machTerminalEntity) {
        return maintainRecordRepository.save(machTerminalEntity);
    }



    public List<Maintainrecord> findAll(Boolean isBind) {
        List<Maintainrecord> tempList=new ArrayList<Maintainrecord>();
        List<MaintainRecordEntity> ListMaintainrecord=(List<MaintainRecordEntity>)maintainRecordRepository.findAll();

        for(int i=0;i<ListMaintainrecord.size();i++){
            MachineEntity machineEntity=machineRepository.findById(ListMaintainrecord.get(i).getMachId());
            Maintainrecord maintainrecord=new Maintainrecord();
            maintainrecord.setId(ListMaintainrecord.get(i).getId());
            maintainrecord.setMachCode(machineEntity.getMachCode());
            maintainrecord.setMachName(machineEntity.getMachName());
            maintainrecord.setMaintainInfo(ListMaintainrecord.get(i).getMaintainInfo());
            maintainrecord.setWorkingType(machineEntity.getWorkingType());
            maintainrecord.setMachId(ListMaintainrecord.get(i).getMachId());
            tempList.add(maintainrecord);
        }

        return tempList;

    }


    public MaintainRecordEntity findById(Integer id) {
        return maintainRecordRepository.findByMachId(id);
    }

    public void delete(Integer id) {
        maintainRecordRepository.delete(id);
    }

    @Transactional
    public MaintainRecordEntity update(MaintainRecordEntity maintainRecord) {

        MaintainRecordEntity saved= maintainRecordRepository.findById(maintainRecord.getId());
        if(saved == null) {
            throw new ObjectNotFoundException("用户不存在");
        }
        saved=maintainRecord.changeUpdateInfoToSave(saved);
        saved=maintainRecordRepository.save(saved);
        return saved;
    }
}
