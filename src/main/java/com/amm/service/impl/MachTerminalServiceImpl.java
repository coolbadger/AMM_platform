package com.amm.service.impl;

import com.amm.entity.MachTerminalEntity;
import com.amm.entity.MachineEntity;
import com.amm.entity.TerminalEntity;
import com.amm.entity.client.MachTerminal;
import com.amm.repository.MachTerminalRepository;
import com.amm.repository.MachineRepository;
import com.amm.repository.TerminalRepository;
import com.amm.service.MachTerminalService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    @Transactional
    public MachTerminalEntity create(MachTerminalEntity machTerminalEntity) {

        Validate.notNull(machTerminalEntity, "The machTerminalEntity must not be null, create failure.");

        MachTerminalEntity created = machTerminalRepository.save(machTerminalEntity);

        return created;
    }

    @Override
    public List<MachTerminal> findAll(Boolean isBind) {

        List<MachineEntity> machineEntityList = (List<MachineEntity>) machineRepository.findAll();

        List<MachTerminal> machTerminalList = new ArrayList<MachTerminal>();

        for (MachineEntity machineEntity : machineEntityList) {

            MachTerminalEntity machTerminalEntity = machTerminalRepository.findByMachId(machineEntity.getId());

            TerminalEntity terminalEntity = null;
            if(machTerminalEntity != null) {
                terminalEntity = terminalRepository.findOne(machTerminalEntity.getTerminalId());
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
                if(machTerminalEntity == null) {
                    machTerminalList.add(machTerminal);
                }
            }

        }

        return machTerminalList;
    }

    @Override
    public MachTerminalEntity findByMachId(Integer machId) {

        Validate.notNull(machId, "The machId must not be null, find failure.");

        return machTerminalRepository.findByMachId(machId);
    }
}
