package com.amm.service;

import com.amm.entity.BaseOrgEntity;

import java.util.List;

/**
 * Created by csw on 2016/7/24 19:34.
 * Explain:
 */
public interface BaseOrgService {
    BaseOrgEntity createOrg(BaseOrgEntity baseOrgEntity);

    List<BaseOrgEntity> findAllBaseOrg();

    BaseOrgEntity updateBaseOrg(BaseOrgEntity baseOrg);

    BaseOrgEntity findOne(Integer id);
}
