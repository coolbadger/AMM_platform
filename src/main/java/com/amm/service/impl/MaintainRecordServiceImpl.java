package com.amm.service.impl;

import com.amm.entity.MachineEntity;
import com.amm.entity.MaintainRecordEntity;
import com.amm.entity.client.Maintainrecord;
import com.amm.repository.MachTerminalRepository;
import com.amm.repository.MachineRepository;
import com.amm.repository.RefMachTerminalRepository;
import com.amm.repository.MaintainRecordRepository;
import com.amm.repository.TerminalRepository;
import com.amm.service.MaintainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

    @Autowired
    private MachTerminalRepository machTerminalRepository;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private RefMachTerminalRepository refMachTerminalRepository;


    public MaintainRecordEntity create(MaintainRecordEntity machTerminalEntity) {
        return null;
    }

    public List<Maintainrecord> findAll(Boolean isBind) {
        List<Maintainrecord> tempList=new ArrayList<Maintainrecord>();

        List<MachineEntity> listMachineEntity=(List<MachineEntity>)machineRepository.findAll();
        for(int i=0;i<listMachineEntity.size();i++){
           MaintainRecordEntity  maintainRecordEntity=maintainRecordRepository.findOne(listMachineEntity.get(i).getId());
            Maintainrecord maintainrecord=new Maintainrecord();
            if(listMachineEntity.size()>0&&maintainRecordEntity!=null&&!maintainRecordEntity.equals("")) {
                maintainrecord.setId(listMachineEntity.get(i).getId());
                maintainrecord.setMachCode(listMachineEntity.get(i).getMachCode());
                maintainrecord.setMachName(listMachineEntity.get(i).getMachName());
                maintainrecord.setMaintainInfo(maintainRecordEntity.getMaintainInfo());
                maintainrecord.setWorkingType(listMachineEntity.get(i).getWorkingType());
                tempList.add(maintainrecord);
            }
        }
        return tempList;
    }


    public MaintainRecordEntity findById(Integer id) {
        return maintainRecordRepository.findByMachId(id);
    }

    public void delete(Integer id) {
        maintainRecordRepository.delete(id);
    }



}
