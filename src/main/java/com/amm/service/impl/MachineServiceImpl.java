package com.amm.service.impl;

import com.amm.entity.MachineEntity;
import com.amm.exception.ObjectNotFoundException;
import com.amm.repository.MachineRepository;
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

    @Transactional
    public MachineEntity create(MachineEntity machineEntity) {

        Validate.notNull(machineEntity, "The machineEntity must not be null, create failure.");
        Validate.notNull(machineEntity.getMachCode(), "The machCode must not be null, create failure.");
        Validate.notNull(machineEntity.getMachName(), "The machName must not be null, create failure.");

        MachineEntity created = machineRepository.save(machineEntity);

        return created;
    }

    public List<MachineEntity> findAll() {
        return (List<MachineEntity>) machineRepository.findAll();
    }

    @Transactional
    public MachineEntity update(MachineEntity machine) {

        Validate.notNull(machine.getId(), "The id of machine must not be null, update failure.");
        Validate.notNull(machine, "The machine object must not be null, update failure.");

        MachineEntity updated = machineRepository.findOne(machine.getId());
        if(updated == null) {
            throw new ObjectNotFoundException("需要更新的农机不存在");
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

        machineRepository.delete(id);

        return deleted;
    }
}
