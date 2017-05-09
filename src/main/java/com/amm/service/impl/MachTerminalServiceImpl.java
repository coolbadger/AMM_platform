package com.amm.service.impl;

import com.amm.entity.MachTerminalEntity;
import com.amm.entity.MachineEntity;
import com.amm.entity.RefMachTerminalEntity;
import com.amm.entity.TerminalEntity;
import com.amm.entity.client.MachTerminal;
import com.amm.exception.ObjectNotFoundException;
import com.amm.repository.*;
import com.amm.service.MachTerminalService;
import com.amm.utils.DateUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/8/7 11:52.
 * Explain:
 */
@Component("machTerminalService")
@Scope("prototype")
public class MachTerminalServiceImpl extends BaseService implements MachTerminalService {

    @Autowired
    private MachTerminalRepository machTerminalRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private RefMachTerminalRepository refMachTerminalRepository;


    @Transactional
    public MachTerminalEntity create(MachTerminalEntity machTerminalEntity) {

        Validate.notNull(machTerminalEntity, "The machTerminalEntity must not be null, create failure.");
        Validate.notNull(machTerminalEntity.getMachId(), "The machId of machTerminal must not be null, create failure.");
        Validate.notNull(machTerminalEntity.getTerminalId(), "The terminalId of machTerminal must not be null, create failure.");

        MachTerminalEntity created = machTerminalRepository.save(machTerminalEntity);

        Validate.notNull(created, "The machine terminal must have been binding, refMachTerminal create failure.");

        MachineEntity machineEntity = machineRepository.findOne(created.getMachId());
        TerminalEntity terminalEntity = terminalRepository.findOne(created.getTerminalId());

        RefMachTerminalEntity refMachTerminalEntity = new RefMachTerminalEntity();
        refMachTerminalEntity.setMachCode(machineEntity.getMachCode());
        refMachTerminalEntity.setMachName(machineEntity.getMachName());
        refMachTerminalEntity.setWorkingType(machineEntity.getWorkingType());
        refMachTerminalEntity.setMachId(machineEntity.getId());
        refMachTerminalEntity.setMachState(machineEntity.getState());
        refMachTerminalEntity.setTerminalCode(terminalEntity.getTerminalCode());
        refMachTerminalEntity.setTerminalState(terminalEntity.getState());
        refMachTerminalEntity.setTerminalName(terminalEntity.getTerminalName());
        refMachTerminalEntity.setCallNo(terminalEntity.getCallNo());
        RefMachTerminalEntity refMachTerminalCreated = refMachTerminalRepository.save(refMachTerminalEntity);
        Validate.notNull(refMachTerminalCreated, "The refMachTerminalEntity are not created.");

        created.setRefMachTerminalId(refMachTerminalCreated.getId());
        created = machTerminalRepository.save(created);

        return created;
    }

    public List<MachTerminal> findAll(Boolean isBind) {

        List<MachineEntity> machineEntityList = (List<MachineEntity>) machineRepository.findAll();

        List<MachTerminal> machTerminalList = new ArrayList<MachTerminal>();

        for (MachineEntity machineEntity : machineEntityList) {

            MachTerminalEntity machTerminalEntity = machTerminalRepository.findByMachId(machineEntity.getId());

            TerminalEntity terminalEntity = null;
            if(machTerminalEntity != null) {
                if (machTerminalEntity.getTerminalId() != null) {
                    terminalEntity = terminalRepository.findOne(machTerminalEntity.getTerminalId());
                }
            }

            MachTerminal machTerminal = new MachTerminal();
            machTerminal.setMachId(machineEntity.getId());
            machTerminal.setMachCode(machineEntity.getMachCode());
            machTerminal.setMachName(machineEntity.getMachName());
            machTerminal.setWorkingType(machineEntity.getWorkingType());
            machTerminal.setTerminalId(terminalEntity != null ? terminalEntity.getId() : null);
            machTerminal.setTerminalCode(terminalEntity != null ? terminalEntity.getTerminalCode() : "");
            machTerminal.setId(machTerminalEntity != null ? machTerminalEntity.getId() : null);
            machTerminal.setRefMachTerminalId(machTerminalEntity != null ? machTerminalEntity.getRefMachTerminalId() : null);
            machTerminal.setStartTime(machTerminalEntity != null ? machTerminalEntity.getStartTime() : null);
            machTerminal.setEndTime(machTerminalEntity != null ? machTerminalEntity.getEndTime() : null);

            if(isBind) {
                machTerminalList.add(machTerminal);
            } else {
                if(terminalEntity == null) {
                    machTerminalList.add(machTerminal);
                }
            }

        }

        return machTerminalList;
    }

    public MachTerminalEntity findByMachId(Integer machId) {

        Validate.notNull(machId, "The machId must not be null, find failure.");

        return machTerminalRepository.findByMachId(machId);
    }

    @Transactional
    public MachTerminalEntity updateToUnBind(Integer id, MachTerminalEntity machTerminalEntity) {

        Validate.notNull(id, "The id of baseOrg must not be null, update failure.");
        Validate.notNull(machTerminalEntity, "The machTerminalEntity object must not be null, update failure.");

        MachTerminalEntity updated = machTerminalRepository.findOne(id);

        if(updated == null) {
            throw new ObjectNotFoundException("需要解除绑定的记录没找到!");
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=sdf.format(new Date());
        updated.setTerminalId(null);
        updated.setRefMachTerminalId(null);
        updated.setStartTime(null);
        updated.setEndTime(DateUtil.parseDate(date));
        updated = machTerminalRepository.save(updated);


        return updated;
    }

    public MachTerminalEntity findByTerminalCode(String terminalCode) {

        Validate.notNull(terminalCode, "The terminalCode must not be null, find failure.");

        TerminalEntity terminalEntity = terminalRepository.findByTerminalCode(terminalCode);
        Validate.notNull(terminalEntity, "The terminalEntity must not be found by terminalCode, find failure.");

        MachTerminalEntity machTerminalEntity = machTerminalRepository.findByTerminalId(terminalEntity.getId());

        return machTerminalEntity;
    }
}
