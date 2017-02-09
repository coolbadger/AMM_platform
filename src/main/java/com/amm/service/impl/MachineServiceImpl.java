package com.amm.service.impl;

import com.amm.entity.MachTerminalEntity;
import com.amm.entity.MachineEntity;
import com.amm.entity.RefMachTerminalEntity;
import com.amm.entity.TerminalEntity;
import com.amm.exception.ObjectNotFoundException;
import com.amm.repository.MachTerminalRepository;
import com.amm.repository.MachineRepository;
import com.amm.repository.RefMachTerminalRepository;
import com.amm.repository.TerminalRepository;
import com.amm.service.MachineService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by csw on 2016/8/4 13:11.
 * Explain:
 */
@Component("machineService")
@Scope("prototype")
public class MachineServiceImpl extends BaseService implements MachineService {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachTerminalRepository machTerminalRepository;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private RefMachTerminalRepository refMachTerminalRepository;

    public List<MachineEntity> findByOrgId(Integer orgId){

        return machineRepository.findByOrgId(orgId);
    }


    @Transactional
    public MachineEntity create(MachineEntity machineEntity) {

        Validate.notNull(machineEntity, "The machineEntity must not be null, create failure.");
        Validate.notNull(machineEntity.getMachCode(), "The machCode must not be null, create failure.");
        Validate.notNull(machineEntity.getMachName(), "The machName must not be null, create failure.");

        MachineEntity created = machineRepository.save(machineEntity);

        return created;
    }

    public List<MachineEntity> findAllByActive(Boolean active) {
        return machineRepository.findAllByActive(active);
    }

    @Transactional
    public MachineEntity update(MachineEntity machine) {

        Validate.notNull(machine.getId(), "The id of machine must not be null, update failure.");
        Validate.notNull(machine, "The machine object must not be null, update failure.");

        MachineEntity updated = machineRepository.findOne(machine.getId());
        if (updated == null) {
            throw new ObjectNotFoundException("需要更新的农机不存在");
        }

        if (!updated.equals(machine)) {
            MachTerminalEntity machTerminalEntity = machTerminalRepository.findByMachId(machine.getId());
            if (machTerminalEntity != null) {
                if(machTerminalEntity.getTerminalId() != null) {
                    TerminalEntity terminalEntity = terminalRepository.findOne(machTerminalEntity.getTerminalId());

                    RefMachTerminalEntity refMachTerminalEntity = new RefMachTerminalEntity();
                    refMachTerminalEntity.setMachCode(machine.getMachCode());
                    refMachTerminalEntity.setMachName(machine.getMachName());
                    refMachTerminalEntity.setMachId(machine.getId());
                    refMachTerminalEntity.setWorkingType(machine.getWorkingType());
                    refMachTerminalEntity.setMachState(machine.getState());
                    refMachTerminalEntity.setTerminalCode(terminalEntity.getTerminalCode());
                    refMachTerminalEntity.setTerminalName(terminalEntity.getTerminalName());
                    refMachTerminalEntity.setCallNo(terminalEntity.getCallNo());
                    refMachTerminalEntity.setTerminalState(terminalEntity.getState());

                    refMachTerminalEntity = refMachTerminalRepository.save(refMachTerminalEntity);

                    machTerminalEntity.setRefMachTerminalId(refMachTerminalEntity.getId());
                    machTerminalRepository.save(machTerminalEntity);
                }
            }
        }

        updated = machine.changeUpdateInfoToSave(updated);

        updated = machineRepository.save(updated);


        return updated;
    }

    public MachineEntity findOne(Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return machineRepository.findOne(id);
    }

    @Transactional
    public MachineEntity delete(Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        MachineEntity deleted = machineRepository.findOne(id);
        if (deleted == null) {
            throw new ObjectNotFoundException("需要删除的农机不存在");
        }

        deleted.setActive(false);
        machineRepository.save(deleted);

        return deleted;
    }
}
