package com.amm.service.impl;

import com.amm.entity.BaseOrgEntity;
import com.amm.repository.BaseOrgRepository;
import com.amm.service.BaseOrgService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by csw on 2016/7/24 19:35.
 * Explain:
 */
@Component("baseOrgService")
@Scope("prototype")
public class BaseOrgServiceImpl extends BaseService implements BaseOrgService {

    @Autowired
    private BaseOrgRepository baseOrgRepository;

    @Transactional
    public BaseOrgEntity createOrg(BaseOrgEntity baseOrgEntity) {

        Validate.notNull(baseOrgEntity, "The baseOrg object must not be null, create failure.");

        BaseOrgEntity baseOrgEntity1 = baseOrgRepository.save(baseOrgEntity);

        return baseOrgEntity1;
    }

    public List<BaseOrgEntity> findAllBaseOrg() {

        return (List<BaseOrgEntity>) baseOrgRepository.findAll();
    }

    public BaseOrgEntity updateBaseOrg(BaseOrgEntity baseOrg) {

        Validate.notNull(baseOrg.getId(), "The id of baseOrg must not be null, update failure.");
        Validate.notNull(baseOrg, "The baseOrg object must not be null, update failure.");

        BaseOrgEntity saveBaseOrg = this.findOne(baseOrg.getId());
        saveBaseOrg = baseOrg.changeUpdateInfoToSave(saveBaseOrg);

        saveBaseOrg = baseOrgRepository.save(saveBaseOrg);
        return saveBaseOrg;
    }

    public BaseOrgEntity findOne(Integer id) {

        return baseOrgRepository.findOne(id);
    }
}
