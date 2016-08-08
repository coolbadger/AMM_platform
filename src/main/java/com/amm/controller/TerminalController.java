package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.entity.TerminalEntity;
import com.amm.service.BaseOrgService;
import com.amm.service.MachineService;
import com.amm.service.TerminalService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by csw on 2016/7/24 18:30.
 * Explain:组织机构接口
 */

@RestController
@RequestMapping("api/terminals")
public class TerminalController extends BaseController{

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private BaseOrgService baseOrgService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TerminalEntity create(@RequestBody(required = true) TerminalEntity terminalEntity) {

        Validate.notNull(terminalEntity, "The terminalEntity must not be null, create failure.");
        Validate.notNull(terminalEntity.getTerminalCode(), "The terminalCode must not be null, create failure.");
        Validate.notNull(terminalEntity.getTerminalName(), "The terminalName must not be null, create failure.");

        //根据当前用户的组织id,查找BaseOrgEntity对象
//        BaseOrgEntity baseOrg = baseOrgService.findOne(1);
        terminalEntity.setOrgId(1);

        TerminalEntity created = terminalService.create(terminalEntity);

        return created;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TerminalEntity> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active,
                                               @RequestParam(required = false, defaultValue = "true") Boolean isBind) {

        List<TerminalEntity> terminalEntityList = terminalService.findAllByActive(active, isBind);

        return terminalEntityList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public TerminalEntity update(@PathVariable Integer id,
                                @RequestBody TerminalEntity terminal) {

        Validate.notNull(id, "The id of terminal must not be null, update failure.");
        Validate.notNull(terminal, "The terminal object must not be null, update failure.");

        terminal.setId(id);

        TerminalEntity updated = terminalService.update(terminal);

        return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TerminalEntity findOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return terminalService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public TerminalEntity deleteOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        return terminalService.delete(id);
    }
}
