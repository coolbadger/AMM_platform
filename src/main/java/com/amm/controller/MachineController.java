package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.entity.MachineEntity;
import com.amm.entity.MachineEntity;
import com.amm.service.BaseOrgService;
import com.amm.service.MachineService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/7/24 18:30.
 * Explain:组织机构接口
 */

@RestController
@RequestMapping("api/machines")
public class MachineController extends BaseController{

    @Autowired
    private MachineService machineService;

    @Autowired
    private BaseOrgService baseOrgService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MachineEntity create(@RequestBody(required = true) MachineEntity machineEntity) {

        Validate.notNull(machineEntity, "The machineEntity must not be null, create failure.");
        Validate.notNull(machineEntity.getMachCode(), "The machCode must not be null, create failure.");
        Validate.notNull(machineEntity.getMachName(), "The machName must not be null, create failure.");

        //根据当前用户的组织id,查找BaseOrgEntity对象
        BaseOrgEntity baseOrg = baseOrgService.findOne(1);
        machineEntity.setBaseOrgByOrgId(baseOrg);

        MachineEntity created = machineService.create(machineEntity);

        return created;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MachineEntity> getAll() {

        List<MachineEntity> machineEntityList = machineService.findAll();

        return machineEntityList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public MachineEntity update(@PathVariable Integer id,
                                @RequestBody MachineEntity machine) {

        Validate.notNull(id, "The id of machine must not be null, update failure.");
        Validate.notNull(machine, "The machine object must not be null, update failure.");

        machine.setId(id);

        MachineEntity updated = machineService.update(machine);

        return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MachineEntity findOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return machineService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public MachineEntity deleteOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        return machineService.delete(id);
    }
}
