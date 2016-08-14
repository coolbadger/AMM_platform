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
import com.amm.service.TerminalService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csw on 2016/8/4 13:28.
 * Explain:
 */
@Component("terminalService")
@Scope("prototype")
public class TerminalServiceImpl extends BaseService implements TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private MachTerminalRepository machTerminalRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private RefMachTerminalRepository refMachTerminalRepository;

    @Transactional
    public TerminalEntity create(TerminalEntity terminalEntity) {

        Validate.notNull(terminalEntity, "The terminalEntity must not be null, create failure.");
        Validate.notNull(terminalEntity.getTerminalCode(), "The terminalCode must not be null, create failure.");
        Validate.notNull(terminalEntity.getTerminalName(), "The terminalName must not be null, create failure.");

        TerminalEntity created = terminalRepository.save(terminalEntity);

        return created;
    }

    public List<TerminalEntity> findAllByActive(Boolean active, Boolean isBind) {

        List<TerminalEntity> terminalEntityListFind = new ArrayList<TerminalEntity>();

        List<TerminalEntity> terminalEntityList = terminalRepository.findAllByActive(active);

        for (TerminalEntity terminalEntity : terminalEntityList) {
            if (isBind) {
                terminalEntityListFind.add(terminalEntity);
            } else {
                MachTerminalEntity machTerminalEntity = machTerminalRepository.findByTerminalId(terminalEntity.getId());
                if(machTerminalEntity == null) {
                    terminalEntityListFind.add(terminalEntity);
                }
            }
        }

        return terminalEntityListFind;
    }

    @Transactional
    public TerminalEntity update(TerminalEntity terminal) {

        Validate.notNull(terminal, "The terminal object must not be null, update failure.");
        Validate.notNull(terminal.getId(), "The id of terminal must not be null, update failure.");

        TerminalEntity updated = terminalRepository.findOne(terminal.getId());
        if (updated == null) {
            throw new ObjectNotFoundException("需要更新的终端不存在");
        }

        if (!updated.equals(terminal)) {
            MachTerminalEntity machTerminalEntity = machTerminalRepository.findByTerminalId(terminal.getId());
            if (machTerminalEntity != null) {
                if(machTerminalEntity.getMachId() != null) {
                    MachineEntity machineEntity = machineRepository.findOne(machTerminalEntity.getMachId());

                    RefMachTerminalEntity refMachTerminalEntity = new RefMachTerminalEntity();
                    refMachTerminalEntity.setMachCode(machineEntity.getMachCode());
                    refMachTerminalEntity.setMachName(machineEntity.getMachName());
                    refMachTerminalEntity.setMachId(machineEntity.getId());
                    refMachTerminalEntity.setWorkingType(machineEntity.getWorkingType());
                    refMachTerminalEntity.setMachState(machineEntity.getState());
                    refMachTerminalEntity.setTerminalCode(terminal.getTerminalCode());
                    refMachTerminalEntity.setTerminalName(terminal.getTerminalName());
                    refMachTerminalEntity.setCallNo(terminal.getCallNo());
                    refMachTerminalEntity.setTerminalState(terminal.getState());

                    refMachTerminalEntity = refMachTerminalRepository.save(refMachTerminalEntity);

                    machTerminalEntity.setRefMachTerminalId(refMachTerminalEntity.getId());
                    machTerminalRepository.save(machTerminalEntity);
                }
            }
        }

        updated = terminal.changeUpdateInfoToSave(updated);

        updated = terminalRepository.save(updated);

        return updated;
    }

    public TerminalEntity findOne(Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return terminalRepository.findOne(id);
    }

    @Transactional
    public TerminalEntity delete(Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        TerminalEntity deleted = terminalRepository.findOne(id);
        if (deleted == null) {
            throw new ObjectNotFoundException("需要删除的终端不存在");
        }

        deleted.setActive(false);
        terminalRepository.save(deleted);

        return deleted;
    }


    public TerminalEntity findTerminalCode(String terminalCode) {

        Validate.notNull(terminalCode, "The terminalCode must not be null, find failure.");

        return terminalRepository.findByTerminalCode(terminalCode);
    }
}
