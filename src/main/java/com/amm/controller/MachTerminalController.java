package com.amm.controller;

import com.amm.entity.MachTerminalEntity;
import com.amm.entity.client.MachTerminal;
import com.amm.service.MachTerminalService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/8/7 11:33.
 * Explain:
 */
@RestController
@RequestMapping("api/machTerminals")
public class MachTerminalController extends BaseController {

    @Autowired
    private MachTerminalService machTerminalService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MachTerminalEntity create(@RequestBody(required = true) MachTerminalEntity machTerminalEntity) {

        Validate.notNull(machTerminalEntity, "The machTerminalEntity must not be null, create failure.");

        machTerminalEntity.setStartTime(new Date());

        MachTerminalEntity created = machTerminalService.create(machTerminalEntity);

        return created;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MachTerminal> getAll(@RequestParam(required = false, defaultValue = "true") Boolean isBind) {

        return machTerminalService.findAll(isBind);
    }

}
