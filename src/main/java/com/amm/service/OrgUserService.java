package com.amm.service;

import com.amm.entity.OrgUserEntity;

import java.util.List;

/**
 * Created by csw on 2016/7/22 19:54.
 * Explain:
 */
public interface OrgUserService {

    OrgUserEntity findOrgUser(String username, String password);

    OrgUserEntity findByUserName(String userName);

    OrgUserEntity createOrgUser(OrgUserEntity orgUserEntity);

    List<OrgUserEntity> findAllOrgUserByActive(Boolean active);

    OrgUserEntity updateOrgUser(OrgUserEntity orgUser);

    OrgUserEntity findById(Integer id);

    OrgUserEntity deleteOrgUser(Integer id);

    boolean isValidUserName(String userName);
}
