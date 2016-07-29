package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.service.BaseOrgService;
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
@RequestMapping("api/baseOrgs")
public class BaseOrgController extends BaseController{

    @Autowired
    private BaseOrgService baseOrgService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BaseOrgEntity create(@RequestBody(required = true) BaseOrgEntity baseOrgEntity) {

        Validate.notNull(baseOrgEntity, "The baseOrgEntity must not be null, create failure.");
        Validate.notNull(baseOrgEntity.getOrgCode(), "The orgCode must not be null, create failure.");
        Validate.notNull(baseOrgEntity.getOrgName(), "The orgName must not be null, create failure.");
        Validate.notNull(baseOrgEntity.getOrgContact(), "The orgContact must not be null, create failure.");

        baseOrgEntity.setCreateTime(new Date());
        baseOrgEntity = baseOrgService.createOrg(baseOrgEntity);

        return baseOrgEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BaseOrgEntity> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {

        List<BaseOrgEntity> baseOrgEntityList = baseOrgService.findAllBaseOrgByActive(active);

        return baseOrgEntityList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public BaseOrgEntity update(@PathVariable Integer id,
                                @RequestBody BaseOrgEntity baseOrg) {

        Validate.notNull(id, "The id of baseOrg must not be null, update failure.");
        Validate.notNull(baseOrg, "The baseOrg object must not be null, update failure.");

        baseOrg.setId(id);

        BaseOrgEntity baseOrgEntity = baseOrgService.updateBaseOrg(baseOrg);

        return baseOrgEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseOrgEntity findOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return baseOrgService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public BaseOrgEntity deleteOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        return baseOrgService.deleteBaseOrg(id);
    }
}
