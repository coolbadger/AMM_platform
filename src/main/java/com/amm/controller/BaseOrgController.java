package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.service.BaseOrgService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseOrgEntity create(@RequestParam(value = "orgCode", required = true) String orgCode,
                                @RequestParam(value = "orgName", required = true) String orgName,
                                @RequestParam(value = "orgContact", required = true) String orgContact) {

        Validate.notNull(orgCode, "The orgCode must not be null.");
        Validate.notNull(orgName, "The orgCode must not be null.");
        Validate.notNull(orgContact, "The orgCode must not be null.");

        BaseOrgEntity baseOrgEntity = new BaseOrgEntity(orgCode, orgName, orgContact);

        baseOrgEntity = baseOrgService.createOrg(baseOrgEntity);

        return baseOrgEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BaseOrgEntity> listBaseOrg() {

        List<BaseOrgEntity> baseOrgEntityList = baseOrgService.findAllBaseOrg();

        return baseOrgEntityList;
    }
}
